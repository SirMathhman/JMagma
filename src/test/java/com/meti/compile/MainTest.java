package com.meti.compile;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    private static final Path Input = Paths.get(".", "test.mg");
    public static final Path Output = Input.resolveSibling("test.c");

    @BeforeEach
    void setUp() throws IOException {
        Files.writeString(Input, "10");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Input);
        Files.deleteIfExists(Output);
    }

    @Test
    void main() {
        Main.main(new String[0]);
        assertTrue(Files.exists(Output));
    }
}