package com.meti.compile.render.process;

import com.meti.compile.render.primitive.Primitive;
import org.junit.jupiter.api.Test;

import static com.meti.compile.render.field.InlineField.Field;
import static com.meti.compile.render.process.IdentifiedFrame.IdentifiedFrame;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IdentifiedFrameTest {
    @Test
    void testToString() {
        var identity = Field("test", Primitive.I16);
        var frame = IdentifiedFrame(identity);
        assertEquals("test=()", frame.toString());
    }
}