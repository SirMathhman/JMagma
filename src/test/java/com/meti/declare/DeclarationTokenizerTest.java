package com.meti.declare;

import com.meti.Field;
import com.meti.Node;
import com.meti.content.ContentType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeclarationTokenizerTest {

    @Test
    void tokenize() {
        Node expected = new Declaration(Field.Field()
                .withName("x")
                .withType(new ContentType("I16"))
                .withFlag(Field.Flag.CONST)
                .complete());
        Node actual = new DeclarationTokenizer("const x : I16")
                .tokenize()
                .orElseThrow();
        assertEquals(expected, actual);
    }
}