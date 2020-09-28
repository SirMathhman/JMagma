package com.meti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathUtils {
    public static boolean ensure(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
            return true;
        } else {
            return false;
        }
    }
}
