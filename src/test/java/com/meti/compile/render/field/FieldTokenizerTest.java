package com.meti.compile.render.field;

import com.meti.compile.render.UnrenderableException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldTokenizerTest {
    @Test
    void invalid() {
        var evaluator = new FieldTokenizer("dummy");
        var optional = evaluator.evaluate();
        assertTrue(optional.isEmpty());
    }

    @Test
    void evaluateImplicitly() {
        var evaluator = new FieldTokenizer("const x");
        var field = evaluator.evaluate().orElseThrow();
        assertThrows(UnrenderableException.class, field::render);
    }

    @Test
    void evaluateExplicitly() {
        var evaluator = new FieldTokenizer("let x : I16");
        var field = evaluator.evaluate().orElseThrow();
        assertThrows(UnrenderableException.class, field::render);
    }
}