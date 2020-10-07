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

public class Main {
    private static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {
        var root = Paths.get(".");
        var files = walkRoot(root);

    }

    private static List<Path> walkRoot(Path root) {
        try {
            return Files.walk(root)
                    .filter(s -> s.toString().endsWith(".mg"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to walk root: " + root.toAbsolutePath(), e);
            return Collections.emptyList();
        }
    }
}
