package com.meti;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

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