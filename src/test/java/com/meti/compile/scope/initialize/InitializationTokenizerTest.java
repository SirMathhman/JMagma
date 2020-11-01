package com.meti.compile.scope.initialize;

import com.meti.compile.Node;
import com.meti.compile.content.ContentNode;
import com.meti.compile.content.ContentType;
import com.meti.compile.scope.field.Field;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InitializationTokenizerTest {

    @Test
    void tokenize() {
        Field identity = Field.Field()
                .withName("x")
                .withType(new ContentType("I16"))
                .withFlag(Field.Flag.CONST)
                .complete();
        Node expected = new Initialization(identity, new ContentNode("0"));
        Node actual = new InitializationTokenizer("const x : I16 = 0")
                .tokenize()
                .orElseThrow();
        assertEquals(expected, actual);
    }
}