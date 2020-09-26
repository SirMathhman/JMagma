package com.meti;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getAnonymousLogger();

    /*
    Even though, by convention, Java constants have fully capitalized names,
    we're trying to write this Java code such that it better resembles the language
    at the end, because we're eventually going to translate this Java code into
    the final language as a bootstrap compiler. So, despite breaking convention here,
    this difference makes it easier to translate the Java source code into Magma source code.

    - SirMathhman, 9/26/2020
    */
    private static final Path Root = Paths.get(".");
    private static final Path Source = Root.resolve("src");
    private static final Path Build = Root.resolve(".build");

    public static void main(String[] args) {
        ensureBuild();
        ensureSource();
        Properties properties = new Properties();
        load(properties);
        String mainContent = loadMain(properties);

        store(properties);
    }

    private static String loadMain(Properties properties) {
        try {
            if (!properties.containsKey("Main")) {
                properties.setProperty("Main", "Main");
            }
            String mainScript = properties.getProperty("Main");
            List<String> mainPackage = List.of(mainScript.split("\\."));
            return new PathScriptLoader(Source).load(mainPackage);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load main class.", e);
            System.exit(-1);
            return null;
        }
    }

    private static void ensureSource() {
        try {
            if (!Files.exists(Source)) {
                Files.createDirectories(Source);
            }
        } catch (IOException e) {
            String format = "Failed to create source directory at: %s";
            String message = String.format(format, Source.toAbsolutePath());
            logger.log(Level.SEVERE, message, e);
        }
    }

    private static void ensureBuild() {
    /*
    Unfortunately, working with Paths/Files with the JDK is much more robust compared
    to working with files in C/C++, primarily because those languages are much further
    located towards the processor. Moreover, each implementation of the language may
    have different systems towards file systems, so ideally we'd want to not refer back
    to the JDK as much as possible, and this is only here for convenience purposes.

    -  SirMathhman, 9/26/2020
     */
        try {
            if (!Files.exists(Build)) {
                String format = "Configuration file did not exist and is being created at: %s";
                String message = String.format(format, Build.toAbsolutePath());
                logger.log(Level.SEVERE, message);
                Files.createFile(Build);
            }
        } catch (IOException e) {
            String format = "Failed to create configuration file at: %s";
            String message = String.format(format, Build.toAbsolutePath());
            logger.log(Level.SEVERE, message, e);
        }
    }

    private static void store(Properties properties) {
        try (OutputStream outputStream = Files.newOutputStream(Main.Build)) {
            /*
            I really don't know what kind of comment might be passed to Properties.store(OutputStream, String).
            In reality, we're really just using the Properties system that comes right out of the box of the JDK
            and avoiding our own implementation of Properties (which we will have to write at some point),
            which may or may not have the "comments" parameter.

            -  SirMathhman, 9/26/2020
             */
            properties.store(outputStream, "");
        } catch (IOException e) {
            String format = "Failed to store build properties to: %s";
            String message = String.format(format, Main.Build.toAbsolutePath());
            logger.log(Level.SEVERE, message, e);
        }
    }

    private static void load(Properties properties) {
    /*
    We read in build properties using the Java native system, however
    this isn't ideal and we're going to have to provide a system to
    do this better once the language is done.

    -  SirMathhman, 9/26/2020
    */
        try (InputStream inputStream = Files.newInputStream(Main.Build)) {
            properties.load(inputStream);
        } catch (IOException e) {
            String format = "Failed to load build properties from: %s";
            String message = String.format(format, Main.Build.toAbsolutePath());
            logger.log(Level.SEVERE, message);
        }
    }
}
