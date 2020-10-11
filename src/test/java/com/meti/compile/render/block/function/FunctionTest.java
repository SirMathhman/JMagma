package com.meti.compile.render.block.function;

import com.meti.compile.render.primitive.IntNumber;
import org.junit.jupiter.api.Test;

import static com.meti.compile.render.block.Block.Block;
import static com.meti.compile.render.block.Block.Block_;
import static com.meti.compile.render.block.function.Function.Function;
import static com.meti.compile.render.block.function.Return.Return;
import static com.meti.compile.render.primitive.IntNumber.Int;
import static com.meti.compile.render.primitive.Primitive.I16;
import static com.meti.compile.render.primitive.Primitive.I8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class FunctionTest {
    @Test
    void streamChildren() {
        var expected = Block_;
        var node = Function("test", I16, expected);
        var actual = node.streamChildren().findAny().orElseThrow();
        assertSame(expected, actual);
    }

    @Test
    void function() {
    }

    @Test
    void testFunction() {
    }

    @Test
    void testToString() {
    }

    @Test
    void mapByIdentity() {
    }

    @Test
    void mapByChildren() {
    }

    @Test
    void mapByFields() {
    }

    @Test
    void streamFields() {
    }

    @Test
    void reduce() {
    }

    @Test
    void is() {
    }

    @Test
    void identity() {
    }

    @Test
    void value() {
    }

    @Test
    void withIdentity() {
    }

    @Test
    void withValue() {
    }

    @Test
    void render() {
    }

    @Test
    void testEquals() {
        var expected = Function("supplier", I8, Block(Return(Int(10))));
        var actual = Function("supplier", I8, Block(Return(Int(10))));
        assertEquals(expected, actual);
    }

    @Test
    void testHashCode() {
    }
}
