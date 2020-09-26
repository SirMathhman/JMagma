package com.meti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {
        Path build = Paths.get(".", ".build");
        try {
            if (!Files.exists(build)) {
                String message = "Configuration file did not exist and is being created at: %s";
                logger.log(Level.SEVERE, String.format(message, build.toAbsolutePath()));
                Files.createFile(build);
            }
        } catch (IOException e) {
            String format = "Failed to create configuration file at: %s";
            logger.log(Level.SEVERE, String.format(format, build.toAbsolutePath()), e);
        }
    }
}
