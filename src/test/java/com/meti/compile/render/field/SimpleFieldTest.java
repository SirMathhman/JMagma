package com.meti.compile.render.field;

import com.meti.compile.render.primitive.Primitive;
import org.junit.jupiter.api.Test;

import static com.meti.compile.render.field.SimpleField.Field;
import static org.junit.jupiter.api.Assertions.*;

class SimpleFieldTest {

    @Test
    void testEquals() {
        var actual = Field("test", Primitive.I16);
        var expected = Field("test", Primitive.I16);
        assertEquals(expected, actual);
    }
}