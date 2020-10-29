package com.meti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.meti.BracketStrategy.BracketStrategy_;
import static com.meti.ImmutableStrategyBuffer.ImmutableStrategyBuffer_;

public class Main {
    private static final Path Root = Paths.get(".");
    private static final Path Target = Root.resolve("target.c");
    private static final Path SourceDirectory = Root.resolve("source");
    private static final Path Build = Root.resolve(".build");
    private static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {
        ensureBuildFile();
        ensureSourceDirectory();
        process();
    }

    private static void process() {
        List<String> lines = readBuild();
        if (lines.isEmpty()) {
            logger.log(Level.SEVERE, "Failed to find entry point.");
        } else {
            processBuildContent(lines);
        }
    }

    private static void processBuildContent(List<String> lines) {
        List<String> stream = extractMainPackage(lines);
        Path mainFile = resolvePackage(stream);
        String output = compile(mainFile);
        deletePreviousTarget();
        writeToTarget(output);
    }

    private static void ensureBuildFile() {
        if (!Files.exists(Build)) {
            logger.log(Level.WARNING, "Build file did not exist at '" + Build + "' and will be created.");

            try {
                Files.createFile(Build);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to create build file at '" + Build + "'.", e);
            }
        }
    }

    private static void ensureSourceDirectory() {
        if (!Files.exists(SourceDirectory)) {
            logger.log(Level.WARNING, "Source directory did not exist at '" + SourceDirectory + "' and will be created.");
            try {
                Files.createDirectory(SourceDirectory);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to create source directory at '" + SourceDirectory + "'.", e);
            }
        }
    }

    private static List<String> readBuild() {
        try {
            return Files.readAllLines(Main.Build);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to read build file.", e);
            return Collections.emptyList();
        }
    }

    private static List<String> extractMainPackage(List<String> lines) {
        String first = lines.get(0);
        String trimmed = first.trim();
        String[] args = trimmed.split("\\.");
        return List.of(args);
    }

    private static Path resolvePackage(List<String> name) {
        int lastIndex = name.size() - 1;
        String format = "%s.mg";
        String scriptName = name.get(lastIndex);
        String formatted = format.formatted(scriptName);
        List<String> package_ = name.subList(0, lastIndex);
        return foldPackage(package_, formatted);
    }

    private static Path foldPackage(Collection<String> package_, String name) {
        return package_.stream()
                .reduce(Main.SourceDirectory, Path::resolve, (path, path2) -> path2)
                .resolve(name);
    }

    private static String compile(Path mainFile) {
        if (Files.exists(mainFile)) {
            String value = readContent(mainFile);
            Stream<String> itemStream = streamItems(value);
            return tokenize(itemStream);
        } else {
            logger.log(Level.SEVERE, "Entry point at '" + mainFile + "' did not exist.");
            return "";
        }
    }

    private static String readContent(Path mainFile) {
        try {
            return Files.readString(mainFile);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to read main file at '" + mainFile + "'.", e);
            return "";
        }
    }

    private static Stream<String> streamItems(String value) {
        return IntStream.range(0, value.length())
                .mapToObj(value::charAt)
                .reduce(ImmutableStrategyBuffer_, BracketStrategy_::process, (oldBuffer, newBuffer) -> newBuffer)
                .complete().trim();
    }

    private static String tokenize(Stream<String> stream) {
        return stream.map(Main::tokenize)
                .collect(Collectors.joining());
    }

    private static String tokenize(String s) {
        String str;
        if (s.equals("def main() : Int => {return 0;}")) {
            str = "int main(){return 0;}";
        } else {
            throw new IllegalArgumentException("Cannot tokenize '" + s + "'.");
        }
        return str;
    }

    private static void deletePreviousTarget() {
        if (Files.exists(Target)) {
            logger.log(Level.WARNING, "Previous target file will be overriden at '" + Target + "'.");
            try {
                Files.delete(Target);
            } catch (IOException e) {
                logger.log(Level.WARNING, "Failed to delete target file at '" + Target + "'.");
            }
        }
    }

    private static void writeToTarget(CharSequence output) {
        int length = output.length();
        try {
            writeToTargetExceptionally(output, length);
        } catch (IOException e) {
            logTargetWritingFailure(length);
        }
    }

    private static void logTargetWritingFailure(int length) {
        String format = "Failed to write output to '%s' of size %d.";
        String message = format.formatted(Main.Target, length);
        logger.log(Level.SEVERE, message);
    }

    private static void writeToTargetExceptionally(CharSequence output, int length) throws IOException {
        Files.writeString(Main.Target, output);
        String format = "Wrote %d characters to target at '%s'.";
        String message = format.formatted(length, Main.Target);
        logger.log(Level.INFO, message);
    }
}
