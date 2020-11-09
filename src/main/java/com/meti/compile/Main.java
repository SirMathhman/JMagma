package com.meti.compile;

import com.meti.compile.path.ScriptPath;
import com.meti.compile.path.JavaScriptPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            String format = "Build file did not exist at '%s' and will be created.";
            String message = format.formatted(Build);
            logger.log(Level.WARNING, message);

            try {
                Files.createFile(Build);
            } catch (IOException e) {
                String format0 = "Failed to create build file at '%s'.";
                String message0 = format0.formatted(Build);
                logger.log(Level.SEVERE, message0, e);
            }
        }
    }

    private static void ensureSourceDirectory() {
        if (!Files.exists(SourceDirectory)) {
            String format = "Source directory did not exist at '%s' and will be created.";
            String message = format.formatted(SourceDirectory);
            logger.log(Level.WARNING, message);

            try {
                Files.createDirectory(SourceDirectory);
            } catch (IOException e) {
                String format0 = "Failed to create source directory at '%s'.";
                String message0 = format0.formatted(SourceDirectory);
                logger.log(Level.SEVERE, message0, e);
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
            ScriptPath scriptPath = new JavaScriptPath(SourceDirectory);
            return MagmaCompiler.MagmaCompiler(scriptPath).compile(value);
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

    private static void deletePreviousTarget() {
        if (Files.exists(Target)) {
            String format = "Previous target file will be overriden at '%s'.";
            String message = format.formatted(Target);
            logger.log(Level.WARNING, message);

            try {
                Files.delete(Target);
            } catch (IOException e) {
                String format0 = "Failed to delete target file at '%s'.";
                String message0 = format0.formatted(Target);
                logger.log(Level.WARNING, message0);
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
