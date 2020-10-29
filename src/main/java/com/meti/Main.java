package com.meti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {
    public static final Path Root = Paths.get(".");
    private static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {
        Path build = Root.resolve(".build");
        if (!Files.exists(build)) {
            logger.log(Level.WARNING, "Build file did not exist at '" + build + "' and will be created.");

            try {
                Files.createFile(build);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to create build file at '" + build + "'.", e);
            }
        }

        Path sourceDirectory = Root.resolve("source");
        if (!Files.exists(sourceDirectory)) {
            logger.log(Level.WARNING, "Source directory did not exist at '" + sourceDirectory + "' and will be created.");

            try {
                Files.createDirectory(sourceDirectory);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to create source directory at '" + sourceDirectory + "'.", e);
            }
        }

        List<String> lines;
        try {
            lines = Files.readAllLines(build);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to read build file.", e);
            lines = Collections.emptyList();
        }
        if (lines.isEmpty()) {
            logger.log(Level.SEVERE, "Failed to find entry point.");
        } else {
            String entry = lines.get(0);
            List<String> nameList = Arrays.stream(entry.trim().split("\\."))
                    .collect(Collectors.toList());
            List<String> packageList = nameList.subList(0, nameList.size() - 1);
            Path mainDirectory = packageList.stream().reduce(sourceDirectory, new BiFunction<Path, String, Path>() {
                @Override
                public Path apply(Path path, String s) {
                    return path.resolve(s);
                }
            }, new BinaryOperator<Path>() {
                @Override
                public Path apply(Path path, Path path2) {
                    return path2;
                }
            });
            Path mainFile = mainDirectory.resolve(nameList.get(nameList.size() - 1) + ".mg");
            StringBuilder output = new StringBuilder();
            if (!Files.exists(mainFile)) {
                logger.log(Level.SEVERE, "Entry point at '" + mainFile + "' did not exist.");
                output = new StringBuilder();
            } else {
                String value;
                try {
                    value = Files.readString(mainFile);
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Failed to read main file at '" + mainFile + "'.", e);
                    value = "";
                }
                List<String> list = new ArrayList<>();
                StringBuilder builder = new StringBuilder();
                int depth = 0;
                int length = value.length();
                for (int i = 0; i < length; i++) {
                    char c = value.charAt(i);
                    if (c == '}' && depth == 1) {
                        depth = 0;
                        builder.append('}');
                        list.add(builder.toString());
                        builder = new StringBuilder();
                    } else if (c == ';' && depth == 0) {
                        list.add(builder.toString());
                        builder = new StringBuilder();
                    } else {
                        if (c == '{') depth++;
                        if (c == '}') depth--;
                        builder.append(c);
                    }
                }
                list.add(builder.toString());
                list.removeIf(String::isBlank);

                for (String s : list) {
                    if (s.equals("def main() : Int => {return 0;}")) {
                        output.append("int main(){return 0;}");
                    } else {
                        throw new IllegalArgumentException("Cannot tokenize '" + s + "'.");
                    }
                }
            }
            Path target = Root.resolve("target.c");
            if (Files.exists(target)) {
                logger.log(Level.WARNING, "Previous target file will be overriden at '" + target + "'.");
                try {
                    Files.delete(target);
                } catch (IOException e) {
                    logger.log(Level.WARNING, "Failed to delete target file at '" + target + "'.");
                }
            }
            try {
                Files.writeString(target, output);
                logger.log(Level.INFO, "Wrote " + output.length() + " characters to target at '" + target + "'.");
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to write output to '" + target + "' of size " + output.length() + ".");
            }
        }
    }
}
