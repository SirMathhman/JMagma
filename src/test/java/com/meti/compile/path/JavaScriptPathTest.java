package com.meti.compile.path;

import com.meti.compile.Node;
import com.meti.compile.content.ContentNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.meti.compile.extern.Import.Import;
import static org.junit.jupiter.api.Assertions.*;

class JavaScriptPathTest {
    private static final Path SOURCES = Paths.get(".", "sources");
    private static final Path PACKAGE_0 = SOURCES.resolve("com");
    private static final Path PACKAGE_1 = PACKAGE_0.resolve("meti");
    private static final Path SOURCE = PACKAGE_1.resolve("Main.mgs");

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(PACKAGE_1);
        Files.createFile(SOURCE);
        Files.writeString(SOURCE, "def main() : Int => {return 0;}");
    }

    @Test
    void read() {
        Node expected = Import()
                .with(new ContentNode("def main() : Int => {return 0;}"))
                .complete();
        Node actual = new JavaScriptPath(SOURCES)
                .read(List.of("com", "meti"), "Main");
        assertEquals(expected, actual);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.delete(SOURCE);
        Files.delete(PACKAGE_1);
        Files.delete(PACKAGE_0);
        Files.delete(SOURCES);
    }
}