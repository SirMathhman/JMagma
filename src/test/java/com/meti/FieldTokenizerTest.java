package com.meti;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.meti.Field.Field;
import static com.meti.Field.Flag.CONST;
import static org.junit.jupiter.api.Assertions.*;

class FieldTokenizerTest {

    @Test
    void tokenize() {
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