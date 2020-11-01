package com.meti.primitive;

import com.meti.primitive.PrimitiveTokenizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimitiveTokenizerTest {

    @Test
    void tokenizeValid() {
        Assertions.assertEquals("int x", new PrimitiveTokenizer("I16")
                .tokenize()
                .orElseThrow()
                .render("x"));
    }

    @Test
    void tokenizeInvalid(){
        assertTrue(new PrimitiveTokenizer("test").tokenize().isEmpty());
    }
}