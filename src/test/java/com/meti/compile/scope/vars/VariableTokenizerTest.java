package com.meti.compile.scope.vars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VariableTokenizerTest {

    @Test
    void tokenize() {
        assertEquals("test", new VariableTokenizer("test")
                .tokenize()
                .orElseThrow()
                .render());
    }
}