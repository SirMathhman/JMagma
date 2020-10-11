package com.meti.compile.render.process;

import com.meti.compile.render.primitive.Primitive;
import org.junit.jupiter.api.Test;

import static com.meti.compile.render.field.InlineField.Field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class MappedStackTest {
    @Test
    void define(){
        var message = new MappedStack()
                .define(Field("supplier", Primitive.I8))
                .toString();
        assertEquals("[(supplier)]", message);
    }

    @Test
    void getDefinition() {
        var expected = Field("supplier", Primitive.I8);
        var actual = new MappedStack()
                .define(expected)
                .getDefinition("supplier");
        assertSame(actual, expected);
    }
}