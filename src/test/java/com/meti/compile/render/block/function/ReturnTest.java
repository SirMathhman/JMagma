package com.meti.compile.render.block.function;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.meti.compile.render.primitive.IntNumber.Int;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class ReturnTest {
    @Test
    void streamChildren() {
        var value = Int(10);
        var root = Return.Return(value);
        var expected = List.of(value);
        var actual = root.streamChildren().collect(Collectors.toList());
        assertIterableEquals(expected, actual);
    }
}
