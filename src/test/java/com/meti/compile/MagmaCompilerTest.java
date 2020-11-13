package com.meti.compile;

import com.meti.compile.path.JavaScriptPath;
import com.meti.compile.tokenize.slice.BracketSplitter;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.meti.compile.MagmaCompiler.MagmaCompiler;
import static com.meti.compile.MagmaCompiler.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class MagmaCompilerTest {
    @Test
    void formatMultiple(){
        assertEquals("""
                int main(){
                    int value = 0;
                    return value;
                }""".replace("    ", "\t"), format("int main(){int value = 0;return value;}"));
    }

    @Test
    void testBrackets(){
        assertEquals("{\n};", MagmaCompiler.format("{};"));
    }

    @Test
    void simpleFormat() {
        assertEquals("""
                if(){
                }""", format("if(){}"));
    }

    @Test
    void contentFormat(){
        assertEquals("""
                int main(){
                    return 0;
                }""".replace("    ", "\t"), format("int main(){return 0;}"));
    }

    @Test
    void testMain() {
        String result = MagmaCompiler(new JavaScriptPath(Paths.get(".").resolve("source"))).compile("def main() : I16 => {return 0;}");
        assertEquals("""
                int main(){
                    return 0;
                }""".replace("    ", "\t"), result);
    }

    @Test
    void splitSimple() {
        Stream<String> stream = new BracketSplitter("{}").split();
        List<String> actual = stream.collect(Collectors.toList());
        assertIterableEquals(List.of("{}"), actual);
    }

    @Test
    void streamBoth() {
        Stream<String> stream = new BracketSplitter("{}{}").split();
        List<String> actual = stream.collect(Collectors.toList());
        assertIterableEquals(List.of("{}", "{}"), actual);
    }

    @Test
    void splitDelimiterIn() {
        Stream<String> stream = new BracketSplitter("{10;20}").split();
        List<String> actual = stream.collect(Collectors.toList());
        assertIterableEquals(List.of("{10;20}"), actual);
    }

    @Test
    void splitDelimiterOut() {
        Stream<String> stream = new BracketSplitter("10;20").split();
        List<String> actual = stream.collect(Collectors.toList());
        assertIterableEquals(List.of("10", "20"), actual);
    }
}