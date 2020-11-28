package com.meti;

import com.meti.CCache.Group;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.meti.Main.Level.Info;
import static com.meti.Main.Level.Warning;

public class Main {

	public static final Path ROOT = Paths.get(".");

	public static void main(String[] args) {
		InputStream stream = System.in;
		try {
			StringBuilder buffer = new StringBuilder();
			int c;
			boolean exiting = false;
			Collection<Path> scriptPath = new HashSet<>();
			do {
				c = stream.read();
				if (c == '\n') {
					String line = buffer.toString();
					buffer = new StringBuilder();
					if (line.equals("exit")) {
						exiting = true;
					} else if (line.startsWith("add ")) {
						String slice = line.substring(4);
						String trim = slice.trim();
						Path path = ROOT.resolve(trim);

						Path absolute = path.toAbsolutePath();
						if (Files.exists(path)) {
							if (Files.isRegularFile(path)) {
								boolean b = isMagmaFile(path);
								if (b) {
									scriptPath.add(path.toAbsolutePath());
									logFormatted(Info, "'%s' has been loaded in.", absolute);
								} else {
									logFormatted(Warning, "'%s' isn't a Magma file.", absolute);
								}
							} else if (Files.isDirectory(path)) {
								List<Path> files = Files.walk(path)
										.filter(Main::isMagmaFile)
										.map(Path::toAbsolutePath)
										.collect(Collectors.toList());
								scriptPath.addAll(files);
								String statement = files.stream()
										.map(Path::toString)
										.map("\n\t%s"::formatted)
										.collect(Collectors.joining());
								logFormatted(Info, "Loaded in %d Magma files from '%s':%s", files.size(), absolute, statement);
							} else {
								logFormatted(Warning, "Cannot read '%s', invalid file format.", absolute);
							}
						} else {
							String format = "'%s' doesn't exist.";
							String message = format.formatted(absolute);
							log(Level.Warning, message);
						}
					} else if (line.equals("list")) {
						String statement = scriptPath.stream()
								.map(Path::toString)
								.map("\n\t%s"::formatted)
								.collect(Collectors.joining());
						logFormatted(Info, "%d files were loaded in:%s", scriptPath.size(), statement);
					} else if (line.startsWith("compile ")) {
						compile(scriptPath, line);
					} else if (line.startsWith("run ")) {
						run(line);
					}
				} else {
					buffer.append((char) c);
				}
			} while (c != -1 && !exiting);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void compile(Collection<Path> scriptPath, String line) {
		String slice = line.substring(8);
		String trim = slice.trim();

		String[] package_ = trim.split("\\.");
		Path mainPath = getResolve(package_);
		if (scriptPath.contains(mainPath.toAbsolutePath())) {
			try {
				String pathString = mainPath.getName(mainPath.getNameCount() - 1).toString();
				String name = pathString.split("\\.")[0];
				String content = Files.readString(mainPath);
				if (content.equals("def main() : I16 => {return 0;}")) {
					Cache<Group> cache = compile(name);
					Map<Group, Path> map = cache.write(mainPath, name);
					if (map.containsKey(Group.NativeSource)) {
						compileSource(map.get(Group.NativeSource));
					}
				} else {
					logFormatted(Warning, "Unable to compile content of '%s'.", content);
				}
			} catch (IOException e) {
				logExceptionally(Level.Error, "Failed to read main content.", e);
			}
		} else {
			logFormatted(Warning, "Main script of package '%s' at '%s' wasn't loaded in yet.", trim, mainPath.toString());
		}
	}

	private static Cache<Group> compile(String name) {
		String sourceString = formatC("#include \"%s\"\nint main(){return 0;}".formatted(name + ".h"));
		String headerString = formatC("#ifndef Main\n#define Main\nint main();#endif");
		String reflectionString = "def main() : I16;";
		return new InlineCache(sourceString, headerString, reflectionString);
	}

	private static void compileSource(Path source) {
		try {
			compileSourceExceptionally(source);
		} catch (IOException e) {
			logExceptionally(Warning, "Failed to compile source.", e);
		}
	}

	private static void compileSourceExceptionally(Path source) throws IOException {
		ProcessBuilder builder = new ProcessBuilder("gcc", source.toString());
		Process process = builder.start();
		ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
		process.getErrorStream().transferTo(errorStream);
		String errorString = errorStream.toString();
		if (!errorString.isBlank()) {
			logFormatted(Warning, "Failed to compile native source with command '%s':\n%s", String.join(" ", builder.command()), errorString);
		}
	}

	private static Path getResolve(String[] package_) {
		return Arrays.stream(package_)
				.limit(package_.length - 1)
				.reduce(ROOT, Path::resolve, (value0, value1) -> value1)
				.resolve("%s.mgs".formatted(package_[package_.length - 1]));
	}

	private static void run(String line) {
		String slice = line.substring(4);
		String trim = slice.trim();
		runCommand(trim);
	}

	private static void runCommand(String trim) {
		try {
			runExceptionally(trim);
		} catch (IOException e) {
			logRunFailure(trim, e);
		}
	}

	private static void runExceptionally(String command) throws IOException {
		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();
		System.out.println("START PROCESS");
		process.getInputStream().transferTo(System.out);
		process.getErrorStream().transferTo(System.out);
		System.out.println("END PROCESS");
	}

	private static void logRunFailure(String command, IOException e) {
		String format = "Failed to run process: %s";
		String message = format.formatted(command);
		logExceptionally(Warning, message, e);
	}

	private static void runExceptionally(ProcessBuilder builder) throws IOException {
		Process process = builder.start();
		System.out.println("START PROCESS");
		process.getInputStream().transferTo(System.out);
		process.getErrorStream().transferTo(System.out);
		System.out.println("END PROCESS");
	}

	private static String formatC(String message) {
		return message.replace(";", ";\n");
	}

	private static boolean isMagmaFile(Path path) {
		Path lastName = path.getName(path.getNameCount() - 1);
		String lastNameString = lastName.toString();
		boolean b = lastNameString.endsWith(".mgs");
		return b;
	}

	private static void logFormatted(Level level, String format, Object... args) {
		log(level, format.formatted(args));
	}

	private static void logExceptionally(Level level, String message, Exception e) {
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		logFormatted(level, "%s\n%s", message, writer);
	}

	private static void log(Level level, String message) {
		System.out.printf("%s: %s%n", level.name(), message);
	}

	enum Level {
		Info,
		Warning,
		Error
	}
}
