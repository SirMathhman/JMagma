package com.meti.compile.scope.declare;

import com.meti.compile.scope.field.Field;
import com.meti.compile.Node;
import com.meti.compile.content.ContentType;
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