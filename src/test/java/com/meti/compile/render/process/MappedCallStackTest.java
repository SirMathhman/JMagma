package com.meti.compile.render.process;

import org.junit.jupiter.api.Test;

import static com.meti.compile.render.field.SimpleField.Field;
import static com.meti.compile.render.primitive.Primitive.*;
import static com.meti.compile.render.process.MappedCallStack.Stack_;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class MappedCallStackTest {
    @Test
    void define(){
        var message = new MappedCallStack()
                .define(Field("supplier", I8))
                .toString();
        assertEquals("[(supplier)]", message);
    }

    @Test
    void getDefinition() {
        var expected = Field("supplier", I8);
        var actual = new MappedCallStack()
                .define(expected)
                .getDefinition("supplier");
        assertSame(actual, expected);
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }

    @Test
    void isDefined() {
    }

    @Test
    void enter() {
    }

    @Test
    void defineAll() {
    }

    @Test
    void exit() {
    }

    @Test
    void enterWithIdentity() {
        var identity = Field("test", I16);
        var stack = Stack_.enterWithIdentity(identity);
        assertEquals("[(),test=()]", stack.toString());
    }
}