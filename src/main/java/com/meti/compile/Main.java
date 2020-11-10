package com.meti.compile;

import com.meti.api.io.Directory;
import com.meti.api.io.File;
import com.meti.api.io.Path;
import com.meti.compile.path.NIOScriptPath;
import com.meti.compile.path.ScriptPath;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.meti.compile.MagmaCompiler.MagmaCompiler;
import static com.meti.api.io.NIOFileSystem.FileSystem_;

public class Main {
    private static final String SourceFormat = "%s.mg";
    private static final Logger logger = Logger.getAnonymousLogger();

    private static final Directory Root = FileSystem_.Root().asDirectory();
    private static final Path SourceDirectory = Root.resolve("source");
    private static final Path Build = Root.resolve(".build");
    private static final Path Target = Root.resolve("target.c");

    public static void main(String[] args) {
        int written = process();
        String format = "Wrote %d characters to target at '%s'.";
        String message = format.formatted(written, Target);
        logger.log(Level.INFO, message);
    }

    private static int process() {
        try {
            return processWithSourceDirectory(ensureBuild());
        } catch (IOException e) {
            String format0 = "Failed to create build file at '%s'.";
            String message0 = format0.formatted(Build);
            logger.log(Level.SEVERE, message0, e);
            return 0;
        }
    }

    private static File ensureBuild() throws IOException {
        if (Build.isExtinct()) {
            String format = "Build file did not exist at '%s' and will be created.";
            String message = format.formatted(Build);
            logger.log(Level.WARNING, message);
            return Build.createFile();
        } else {
            return Build.asFile();
        }
    }

    private static int processWithSourceDirectory(File file) {
        try {
            return process(file, ensureSourceDirectory());
        } catch (IOException e) {
            String format0 = "Failed to create source directory at '%s'.";
            String message0 = format0.formatted(SourceDirectory);
            logger.log(Level.SEVERE, message0, e);
            return 0;
        }
    }

    private static Directory ensureSourceDirectory() throws IOException {
        if (SourceDirectory.isExtinct()) {
            String format = "Source directory did not exist at '%s' and will be created.";
            String message = format.formatted(SourceDirectory);
            logger.log(Level.WARNING, message);
            return SourceDirectory.createDirectory();
        } else {
            return SourceDirectory.asDirectory();
        }
    }

    private static int process(File file, Directory directory) {
        try {
            return processExceptionally(file, directory);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to read build file.", e);
            return 0;
        }
    }

    private static int processExceptionally(File file, Directory directory) throws IOException {
        String content = file.readAsString();
        if (!content.isBlank()) return processBuildContent(content, directory);
        logger.log(Level.SEVERE, "No entry point was found.");
        return 0;
    }

    private static int processBuildContent(String content, Directory directory) {
        Path mainFile = formatEntry(content, directory);
        String output = compile(mainFile, directory);
        deletePreviousTarget();
        return writeToTarget(output);
    }

    private static Path formatEntry(String content, Directory directory) {
        String trimmed = content.trim();
        return trimmed.contains(".") ?
                formatEntryWithPackage(trimmed, directory) :
                formatEntrySimply(trimmed, directory);
    }

    private static Path formatEntryWithPackage(String trimmed, Directory directory) {
        int separator = trimmed.lastIndexOf('.');
        String packageSlice = trimmed.substring(0, separator);
        String packageTrim = packageSlice.trim();

        String nameSlice = trimmed.substring(separator + 1);
        String name = nameSlice.trim();

        String[] packageArray = packageTrim.split("\\.");
        String formatted = SourceFormat.formatted(name);
        return Arrays.stream(packageArray)
                .reduce(directory, Directory::resolveDirectory, (path, path2) -> path2)
                .resolve(formatted);
    }

    private static Path formatEntrySimply(String trimmed, Directory directory) {
        String format = "%s.mg";
        String formatted = format.formatted(trimmed);
        return directory.resolve(formatted);
    }

    private static String compile(Path mainFile, Directory directory) {
        if (mainFile.isExtant()) {
            String value = readContent(mainFile.asFile());
            ScriptPath scriptPath = new NIOScriptPath(directory);
            return MagmaCompiler(scriptPath).compile(value);
        } else {
            String format = "Entry point at '%s' did not exist.";
            String message = format.formatted(mainFile);
            logger.log(Level.SEVERE, message);
            return "";
        }
    }

    private static String readContent(File mainFile) {
        try {
            return mainFile.readAsString();
        } catch (IOException e) {
            String format = "Failed to read main file at '%s'.";
            String message = format.formatted(mainFile);
            logger.log(Level.SEVERE, message, e);
            return "";
        }
    }

    private static void deletePreviousTarget() {
        if (Target.isExtant()) {
            String format = "Previous target file will be overriden at '%s'.";
            String message = format.formatted(Target);
            logger.log(Level.WARNING, message);

            try {
                Target.delete();
            } catch (IOException e) {
                String format0 = "Failed to delete target file at '%s'.";
                String message0 = format0.formatted(Target.getRoot());
                logger.log(Level.WARNING, message0);
            }
        }
    }

    private static int writeToTarget(CharSequence output) {
        try {
            return writeToTargetExceptionally(output);
        } catch (IOException e) {
            logTargetWritingFailure(output.length());
            return 0;
        }
    }

    private static void logTargetWritingFailure(int length) {
        String format = "Failed to write output to '%s' of size %d.";
        String message = format.formatted(Target, length);
        logger.log(Level.SEVERE, message);
    }

    private static int writeToTargetExceptionally(CharSequence output) throws IOException {
        Target.write(output);
        return output.length();
    }
}
