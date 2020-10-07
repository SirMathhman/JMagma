package com.meti.compile.render.field;

import com.meti.compile.render.UnrenderableException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldEvaluatorTest {
    @Test
    void invalid() {
        var evaluator = new FieldEvaluator("dummy");
        var optional = evaluator.evaluate();
        assertTrue(optional.isEmpty());
    }

    @Test
    void evaluateImplicitly() {
        var evaluator = new FieldEvaluator("const x");
        var field = evaluator.evaluate().orElseThrow();
        assertThrows(UnrenderableException.class, field::render);
    }

    @Test
    void evaluateExplicitly() {
        var evaluator = new FieldEvaluator("let x : I16");
        var field = evaluator.evaluate().orElseThrow();
        assertThrows(UnrenderableException.class, field::render);
    }
}