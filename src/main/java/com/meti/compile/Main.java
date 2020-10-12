package com.meti.compile;

import com.meti.compile.render.CompileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.logging.Logger.getAnonymousLogger;

public class Main {
    private static final Logger Logger = getAnonymousLogger();
    private static final Path Root = Paths.get(".");
    private static final Path SourceDirectory = Root.resolve("source");
    private static final Path TargetDirectory = Root.resolve("target");

    public static void main(String[] args) {
        ensureDirectory(SourceDirectory);
        ensureDirectory(TargetDirectory);

        var files = walkSource();
        for (Path sourcePath : files) {
            var targetPath = formatTarget(sourcePath);
            try {
                var content = readSource(sourcePath);
                if (!content.isBlank()) {
                    var output = compile(sourcePath, content);
                    writeTarget(targetPath, output);
                }
            } catch (IOException e) {
                var format = "Failed to process source file: %s";
                var message = format.formatted(sourcePath.toAbsolutePath());
                Logger.log(Level.SEVERE, message, e);
            }
        }
    }

    private static String compile(Path sourcePath, String content) {
        try {
            return !content.isBlank() ? MagmaCompiler.Compiler_.compile(content) : "";
        } catch (CompileException e) {
            var format = "Failed to compile '%s'. An empty string has been written.";
            var formatted = format.formatted(sourcePath.toAbsolutePath());
            Logger.log(Level.SEVERE, formatted, e);
            return "";
        }
    }

    private static void writeTarget(Path targetPath, String output) throws IOException {
        try {
            ensureDirectory(targetPath.getParent());
            ensureFile(targetPath);
            Files.writeString(targetPath, output);
        } catch (IOException e) {
            var format = "Failed to write output of length '%d' to '%s'.";
            var message = format.formatted(output.length(), targetPath.toAbsolutePath());
            throw new IOException(message, e);
        }
    }

    private static Path formatTarget(Path sourcePath) {
        var fileName = sourcePath.getName(sourcePath.getNameCount() - 1).toString();
        var separator = fileName.indexOf('.');
        var name = fileName.substring(0, separator);
        var child = SourceDirectory.relativize(sourcePath);
        var format = "%s.c";
        var formatted = format.formatted(name);
        return TargetDirectory.resolve(child).resolveSibling(formatted);
    }

    private static String readSource(Path sourcePath) throws IOException {
        try {
            return Files.readString(sourcePath);
        } catch (IOException e) {
            var format = "Failed to read source: %s";
            var message = format.formatted(sourcePath);
            throw new IOException(message, e);
        }
    }

    private static List<Path> walkSource() {
        try {
            return Files.walk(SourceDirectory)
                    .filter(s -> s.toString().endsWith(".mg"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            var format = "Failed to walk source directory: %s";
            var message = format.formatted(SourceDirectory.toAbsolutePath());
            Logger.log(Level.SEVERE, message, e);
            return Collections.emptyList();
        }
    }

    private static void ensureDirectory(Path directory) {
        if (!Files.exists(directory)) {
            {
                var format = "Directory at '%s' did not exist and will be created.";
                var message = format.formatted(directory.toAbsolutePath());
                Logger.log(Level.WARNING, message);
            }

            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                var format = "Failed to create directory at '%s'.";
                var message = format.formatted(directory);
                Logger.log(Level.SEVERE, message, e);
            }
        }
    }
    private static void ensureFile(Path file) {
        if (!Files.exists(file)) {
            {
                var format = "File at '%s' did not exist and will be created.";
                var message = format.formatted(file.toAbsolutePath());
                Logger.log(Level.WARNING, message);
            }

            try {
                Files.createFile(file);
            } catch (IOException e) {
                var format = "Failed to create file at '%s'.";
                var message = format.formatted(file);
                Logger.log(Level.SEVERE, message, e);
            }
        }
    }
}
