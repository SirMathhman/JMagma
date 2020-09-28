package com.meti;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PathUtilsTest {
    private static final Path Temp = Paths.get(".", "temp");

    @BeforeEach
    void before() throws IOException {
        Files.deleteIfExists(Temp);
    }

    @AfterEach
    void after() throws IOException {
        Files.deleteIfExists(Temp);
    }

    @Test
    void ensureExtinct() throws IOException {
        assertTrue(PathUtils.ensure(Temp));
    }

    @Test
    void ensureExtant() throws IOException {
        Files.createFile(Temp);
        assertFalse(PathUtils.ensure(Temp));
    }
}