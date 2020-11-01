package com.meti.scope.field;

import com.meti.content.ContentType;
import org.junit.jupiter.api.Test;

import static com.meti.scope.field.Field.Field;
import static com.meti.scope.field.Field.Flag.CONST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldTokenizerTest {
    @Test
    void tokenizeInvalid() {
        assertTrue(new FieldTokenizer("test")
                .tokenize()
                .isEmpty());
    }

    @Test
    void tokenizeValid() {
        Field expected = Field()
                .withName("x")
                .withType(new ContentType("I16"))
                .withFlag(CONST)
                .complete();
        Field actual = new FieldTokenizer("const x : I16")
                .tokenize()
                .orElseThrow();
        assertEquals(expected, actual);
    }
}