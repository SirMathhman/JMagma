package com.meti;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.meti.MagmaCompiler.MagmaCompiler_;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class MagmaCompilerTest {
    @Test
    void testMain() {
        String result = MagmaCompiler_.compile("def main() : Int => {return 0;}");
        assertEquals("int main(){return 0;}", result);
    }

    @Test
    void splitSimple() {
        Stream<String> stream = MagmaCompiler.split("{}");
        List<String> actual = stream.collect(Collectors.toList());
        assertIterableEquals(List.of("{}"), actual);
    }

    @Test
    void streamBoth() {
        Stream<String> stream = MagmaCompiler.split("{}{}");
        List<String> actual = stream.collect(Collectors.toList());
        assertIterableEquals(List.of("{}", "{}"), actual);
    }

    @Test
    void splitDelimiterIn(){
        Stream<String> stream = MagmaCompiler.split("{10;20}");
        List<String> actual = stream.collect(Collectors.toList());
        assertIterableEquals(List.of("{10;20}"), actual);
    }

    @Test
    void splitDelimiterOut(){
        Stream<String> stream = MagmaCompiler.split("10;20");
        List<String> actual = stream.collect(Collectors.toList());
        assertIterableEquals(List.of("10", "20"), actual);
    }
}