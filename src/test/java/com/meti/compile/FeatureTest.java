package com.meti.compile;

import com.meti.compile.path.JavaScriptPath;
import org.junit.jupiter.api.Assertions;

import java.nio.file.Paths;

public class FeatureTest {
    protected void assertCompile(String expected, String source) {
        String actual = MagmaCompiler.MagmaCompiler(new JavaScriptPath(Paths.get(".").resolve("source"))).compile(source);
        Assertions.assertEquals(expected.replace("    ", "\t"), actual);
    }
}
