package com.meti.compile.render.scope;

import org.junit.jupiter.api.Test;

import static com.meti.compile.render.field.InlineField.Field;
import static com.meti.compile.render.primitive.IntNumber.Int;
import static com.meti.compile.render.primitive.Primitive.I16;
import static com.meti.compile.render.scope.Initialization.Initialize;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InitializationTest {
    @Test
    void testEquals() {
        var expected = Initialize(Field("x", I16), Int(10));
        var actual = Initialize(Field("x", I16), Int(10));
        assertEquals(expected, actual);
    }
}