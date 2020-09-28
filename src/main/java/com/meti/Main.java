package com.meti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Path Root = Paths.get(".");
    private static final Path Source = Root.resolve(".magma");
    private static final Path Target = Root.resolve(".c");
    private static final Logger logger = Logger.getAnonymousLogger();
    public static final Compiler Compiler = new Compiler();

    public static void main(String[] args) {
        ensureLogged(Source);
        ensureLogged(Target);
        run();
    }

    private static void run() {
        readSource().map(Compiler::compileToString)
                .ifPresentOrElse(Main::writeTarget, Main::logNoSource);
    }

    private static void logNoSource() {
        logger.log(Level.WARNING, "Could not load source text.");
    }

    private static void writeTarget(String output) {
        try {
            Files.writeString(Target, output);
        } catch (IOException e) {
            String format = "Failed to write output to: %s";
            String message = String.format(format, Target.toAbsolutePath());
            logger.log(Level.SEVERE, message, e);
        }
    }

    private static Optional<String> readSource() {
        try {
            String value = Files.readString(Source);
            return Optional.of(value);
        } catch (IOException e) {
            String format = "Failed to read source file at: %s";
            String message = String.format(format, Source.toAbsolutePath());
            logger.log(Level.SEVERE, message, e);
            return Optional.empty();
        }
    }

    private static void ensureLogged(Path path) {
        try {
            if (PathUtils.ensure(path)) {
                String format = "File did not exist at '%s' and has been created.";
                String message = String.format(format, path.toAbsolutePath());
                logger.log(Level.WARNING, message);
            }
        } catch (IOException e) {
            String format = "Failed to create file at '%s'";
            String message = String.format(format, path.toAbsolutePath());
            logger.log(Level.SEVERE, message, e);
        }
    }
}
