package com.meti.compile;

import com.meti.compile.path.JavaScriptPath;
import com.meti.compile.path.ScriptPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.meti.compile.NIOFileSystem.FileSystem_;

public class Main {
    private static final String SourceFormat = "%s.mg";
    private static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {
        ensureBuildFile();
        ensureSourceDirectory();
        process();
    }

    private static void process() {
        try {
            processExceptionally();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to read build file.", e);
        }
    }

    private static void processExceptionally() throws IOException {
        String content = Files.readString(FileSystem_.Root().resolve(".build").getRoot());
        if (content.isBlank()) logger.log(Level.SEVERE, "No entry point was found.");
        else processBuildContent(content);
    }

    private static void processBuildContent(String content) {
        Path mainFile = formatEntry(content);
        String output = compile(mainFile);
        deletePreviousTarget();
        writeToTarget(output);
    }

    private static Path formatEntry(String content) {
        String trimmed = content.trim();
        return trimmed.contains(".") ?
                formatEntryWithPackage(trimmed) :
                formatEntrySimply(trimmed);
    }

    private static Path formatEntryWithPackage(String trimmed) {
        int separator = trimmed.lastIndexOf('.');
        String packageSlice = trimmed.substring(0, separator);
        String packageTrim = packageSlice.trim();

        String nameSlice = trimmed.substring(separator + 1);
        String name = nameSlice.trim();

        String[] packageArray = packageTrim.split("\\.");
        String formatted = SourceFormat.formatted(name);
        return Arrays.stream(packageArray)
                .reduce(FileSystem_.Root().resolve("source").getRoot(), Path::resolve, (path, path2) -> path2)
                .resolve(formatted);
    }

    private static Path formatEntrySimply(String trimmed) {
        String format = "%s.mg";
        String formatted = format.formatted(trimmed);
        return new Source(Paths.get(".").resolve("source")).resolve(formatted).getRoot();
    }

    private static void ensureBuildFile() {
        if (!Files.exists(FileSystem_.Root().resolve(".build").getRoot())) {
            String format = "Build file did not exist at '%s' and will be created.";
            String message = format.formatted(FileSystem_.Root().resolve(".build").getRoot());
            logger.log(Level.WARNING, message);

            try {
                Files.createFile(FileSystem_.Root().resolve(".build").getRoot());
            } catch (IOException e) {
                String format0 = "Failed to create build file at '%s'.";
                String message0 = format0.formatted(FileSystem_.Root().resolve(".build").getRoot());
                logger.log(Level.SEVERE, message0, e);
            }
        }
    }

    private static void ensureSourceDirectory() {
        if (!Files.exists(FileSystem_.Root().resolve("source").getRoot())) {
            String format = "Source directory did not exist at '%s' and will be created.";
            String message = format.formatted(FileSystem_.Root().resolve("source").getRoot());
            logger.log(Level.WARNING, message);

            try {
                Files.createDirectory(FileSystem_.Root().resolve("source").getRoot());
            } catch (IOException e) {
                String format0 = "Failed to create source directory at '%s'.";
                String message0 = format0.formatted(FileSystem_.Root().resolve("source").getRoot());
                logger.log(Level.SEVERE, message0, e);
            }
        }
    }

    private static String compile(Path mainFile) {
        if (Files.exists(mainFile)) {
            String value = readContent(mainFile);
            ScriptPath scriptPath = new JavaScriptPath(FileSystem_.Root().resolve("source").getRoot());
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
        if (Files.exists(FileSystem_.Root().resolve("target.c").getRoot())) {
            String format = "Previous target file will be overriden at '%s'.";
            String message = format.formatted(FileSystem_.Root().resolve("target.c").getRoot());
            logger.log(Level.WARNING, message);

            try {
                Files.delete(FileSystem_.Root().resolve("target.c").getRoot());
            } catch (IOException e) {
                String format0 = "Failed to delete target file at '%s'.";
                String message0 = format0.formatted(FileSystem_.Root().resolve("target.c").getRoot());
                logger.log(Level.WARNING, message0);
            }
        }
    }

    private static void writeToTarget(CharSequence output) {
        try {
            writeToTargetExceptionally(output);
        } catch (IOException e) {
            logTargetWritingFailure(output.length());
        }
    }

    private static void logTargetWritingFailure(int length) {
        String format = "Failed to write output to '%s' of size %d.";
        String message = format.formatted(FileSystem_.Root().resolve("target.c").getRoot(), length);
        logger.log(Level.SEVERE, message);
    }

    private static void writeToTargetExceptionally(CharSequence output) throws IOException {
        Files.writeString(FileSystem_.Root().resolve("target.c").getRoot(), output);
        String format = "Wrote %d characters to target at '%s'.";
        String message = format.formatted(output.length(), FileSystem_.Root().resolve("target.c").getRoot());
        logger.log(Level.INFO, message);
    }
}
