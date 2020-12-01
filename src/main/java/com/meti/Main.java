package com.meti;

import com.meti.CCache.Group;
import com.meti.api.collect.StreamException;
import com.meti.api.extern.Internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static com.meti.Logger.Level.Info;
import static com.meti.Logger.Level.Warning;
import static com.meti.api.io.DelegateInStream.DelegateInStream;

public class Main {
	public static final Logger LOGGER = new Logger();
	private static final Path ROOT = Paths.get(".");

	public static void main(String[] args) {
		execute(new CollectiveScriptPath<>(new HashSet<>()));
	}

	private static void execute(ScriptPath<Path> scriptPath) {
		try {
			executeExceptionally(scriptPath);
		} catch (IOException e) {
			LOGGER.logExceptionally(Logger.Level.Error, "Failed to execute.", e);
		}
	}

	private static void executeExceptionally(ScriptPath<Path> scriptPath) throws IOException {
		var inStream = DelegateInStream(Internal.In);
		var lineOptional = inStream.readLine();
		var shouldContinue = true;
		while (shouldContinue) {
			shouldContinue = lineOptional
					.map(line -> process(scriptPath, line))
					.orElse(true);
			lineOptional = inStream.readLine();
		}
	}

	private static boolean process(ScriptPath<Path> scriptPath, String line) {
		if (line.equals("exit")) {
			return true;
		} else {
			processImpl(scriptPath, line);
			return false;
		}
	}

	private static void processImpl(ScriptPath<Path> scriptPath, String command) {
		try {
			processCommand(scriptPath, command);
		} catch (IOException e) {
			var format = "Failed to process command '%s'.";
			var message = format.formatted(command);
			LOGGER.logExceptionally(Warning, message, e);
		}
	}

	private static void processCommand(ScriptPath<Path> scriptPath, String command) throws IOException {
		if (command.startsWith("add ")) {
			add(scriptPath, command);
		} else if (command.equals("list")) {
			list(scriptPath);
		} else if (command.startsWith("compile ")) {
			compile(scriptPath.asCollection(), command);
		} else if (command.startsWith("run ")) {
			run(command);
		}
	}

	private static void list(ScriptPath<Path> scriptPath) {
		var count = scriptPath.count();
		var collect = render(scriptPath);
		LOGGER.logFormatted(Info, "%d files were loaded in:%s", count, collect);
	}

	private static void add(ScriptPath<Path> scriptPath1, String line) throws IOException {
		String slice = line.substring(4);
		String trim = slice.trim();
		Path path = ROOT.resolve(trim);
		Path absolute = path.toAbsolutePath();
		if (Files.exists(path)) {
			loadExtant(scriptPath1, path, absolute);
		} else {
			String format = "'%s' doesn't exist.";
			String message = format.formatted(absolute);
			LOGGER.log(Warning, message);
		}
	}

	private static void loadExtant(ScriptPath<Path> scriptPath1, Path path, Path absolute) throws IOException {
		if (Files.isRegularFile(path)) {
			loadPath(scriptPath1, absolute);
		} else if (Files.isDirectory(path)) {
			loadDirectory(scriptPath1, absolute);
		} else {
			LOGGER.logFormatted(Warning, "Cannot read '%s', invalid file format.", absolute);
		}
	}

	private static void loadDirectory(ScriptPath<Path> scriptPath1, Path absolute) throws IOException {
		List<Path> files = walkPath(absolute);
		scriptPath1.asCollection().addAll(files);
		String statement = formatPaths(files);
		LOGGER.logFormatted(Info, "Loaded in %d Magma files from '%s':%s", files.size(), absolute, statement);
	}

	private static String formatPaths(Collection<Path> files) {
		return files.stream()
				.map(Path::toString)
				.map("\n\t%s"::formatted)
				.collect(Collectors.joining());
	}

	private static List<Path> walkPath(Path path) throws IOException {
		return Files.walk(path)
				.filter(Main::isMagmaFile)
				.map(Path::toAbsolutePath)
				.collect(Collectors.toList());
	}

	private static String render(ScriptPath<Path> scriptPath) {
		try {
			return scriptPath.stream()
					.map(Object::toString)
					.map(args -> "\n\t" + args)
					.fold("", (value0, value1) -> value0 + value1);
		} catch (StreamException e) {
			return "";
		}
	}

	private static void loadPath(ScriptPath<Path> scriptPath1, Path path) {
		if (isMagmaFile(path)) {
			scriptPath1.load(path);
			LOGGER.logFormatted(Info, "'%s' indexOf been loaded in.", path);
		} else {
			LOGGER.logFormatted(Warning, "'%s' isn't a Magma file.", path);
		}
	}

	private static void compile(Collection<Path> scriptPath, String line) {
		String slice = line.substring(8);
		String trim = slice.trim();

		String[] package_ = trim.split("\\.");
		Path mainPath = getResolve(package_);
		if (scriptPath.contains(mainPath.toAbsolutePath())) {
			sovhrt(mainPath);
		} else {
			LOGGER.logFormatted(Warning, "Main script of package '%s' at '%s' wasn't loaded in yet.", trim, mainPath.toString());
		}
	}

	private static void sovhrt(Path mainPath) {
		try {
			String pathString = mainPath.getName(mainPath.getNameCount() - 1).toString();
			String name = pathString.split("\\.")[0];
			sdiughsieu(mainPath, name);
		} catch (IOException e) {
			LOGGER.logExceptionally(Logger.Level.Error, "Failed to read main content.", e);
		}
	}

	private static void sdiughsieu(Path mainPath, String name) throws IOException {
		String content = Files.readString(mainPath);
		if (content.equals("def main() : I16 => {return 0;}")) {
			adasd(mainPath, name);
		} else {
			LOGGER.logFormatted(Warning, "Unable to compile content of '%s'.", content);
		}
	}

	private static void adasd(Path mainPath, String name) throws IOException {
		Cache<Group> cache = compile(name);
		Map<Group, Path> map = cache.write(mainPath, name);
		if (map.containsKey(Group.NativeSource)) {
			compileSource(map.get(Group.NativeSource));
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
			LOGGER.logExceptionally(Warning, "Failed to compile source.", e);
		}
	}

	private static void compileSourceExceptionally(Path source) throws IOException {
		ProcessBuilder builder = new ProcessBuilder("gcc", source.toString());
		Process process = builder.start();
		ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
		process.getErrorStream().transferTo(errorStream);
		String errorString = errorStream.toString();
		if (!errorString.isBlank()) {
			LOGGER.logFormatted(Warning, "Failed to compile native source with command '%s':\n%s", String.join(" ", builder.command()), errorString);
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
		LOGGER.logExceptionally(Warning, message, e);
	}

	private static String formatC(String message) {
		return message.replace(";", ";\n");
	}

	private static boolean isMagmaFile(Path path) {
		var count = path.getNameCount();
		var namePath = path.getName(count - 1);
		var nameString = namePath.toString();
		return nameString.endsWith(".mgs");
	}
}
