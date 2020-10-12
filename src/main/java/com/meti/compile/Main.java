package com.meti.compile;

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
    private static final Compiler Compiler = new MagmaCompiler();
    public static final Path Root = Paths.get(".");
    public static final Path Source = Root.resolve("source");

    public static void main(String[] args) {
        var files = walkSource(Source);
        for (Path file : files) {
            try {
                var content = Files.readString(file);
                var output = Compiler.compile(content);
                var fileName = file.getName(file.getNameCount() - 1).toString();
                var separator = fileName.indexOf('.');
                var name = fileName.substring(0, separator);
                var path = file.resolveSibling(name + ".c");
                Files.writeString(path, output);
            } catch (IOException e) {
                Logger.log(Level.SEVERE, "Failed to read content from: " + file.toAbsolutePath(), e);
            }
        }
    }

    private static List<Path> walkSource(Path root) {
        try {
            return Files.walk(root)
                    .filter(s -> s.toString().endsWith(".mg"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            Logger.log(Level.SEVERE, "Failed to walk root: " + root.toAbsolutePath(), e);
            return Collections.emptyList();
        }
    }
}
