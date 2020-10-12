package com.meti.compile.render.process;

import com.meti.compile.render.primitive.Primitive;
import org.junit.jupiter.api.Test;

import static com.meti.compile.render.field.InlineField.Field;
import static com.meti.compile.render.primitive.Primitive.*;
import static com.meti.compile.render.process.MappedStack.Stack_;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class MappedStackTest {
    @Test
    void define(){
        var message = new MappedStack()
                .define(Field("supplier", I8))
                .toString();
        assertEquals("[(supplier)]", message);
    }

    @Test
    void getDefinition() {
        var expected = Field("supplier", I8);
        var actual = new MappedStack()
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