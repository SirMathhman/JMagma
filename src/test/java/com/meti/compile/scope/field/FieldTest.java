package com.meti.compile.scope.field;

import com.meti.compile.scope.field.Field.FieldImpl;
import org.junit.jupiter.api.Test;

import static com.meti.compile.scope.field.Field.Field;
import static com.meti.compile.primitive.Primitive.I8;
import static java.util.Collections.singleton;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldTest {
    @Test
    void testEquals() {
        Field expected = new FieldImpl(singleton(Field.Flag.CONST), "x", I8);
        Field actual = new FieldImpl(singleton(Field.Flag.CONST), "x", I8);
        assertEquals(expected, actual);
    }

    @Test
    void field() {
        Field expected = new FieldImpl(singleton(Field.Flag.CONST), "x", I8);
        Field actual = Field()
                .withName("x")
                .withType(I8)
                .withFlag(Field.Flag.CONST)
                .complete();
        assertEquals(expected, actual);
    }

    @Test
    void is() {
        assertTrue(Field()
                .withName("x")
                .withType(I8)
                .withFlag(Field.Flag.CONST)
                .complete()
                .is(Field.Flag.CONST));
    }

    @Test
    void render() {
        assertEquals("char x", Field()
                .withName("x")
                .withType(I8)
                .withFlag(Field.Flag.CONST)
                .complete()
                .render());
    }
}