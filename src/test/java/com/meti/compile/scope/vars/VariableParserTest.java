package com.meti.compile.scope.vars;

import com.meti.compile.primitive.ints.Int;
import com.meti.compile.scope.field.Field;
import com.meti.compile.state.Stack;
import com.meti.compile.state.State;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static com.meti.compile.primitive.Primitive.I16;
import static com.meti.compile.scope.field.Field.Field;
import static org.junit.jupiter.api.Assertions.*;

class VariableParserTest {
    @Test
    void processUndefined() {
        Stack stack = new Stack();
        State expected = new State(new Variable("value"), stack);
        assertThrows(IllegalStateException.class, () -> new VariableParser(expected).process());
    }

    @Test
    void processInvalid() {
        Stack stack = new Stack();
        State expected = new State(new Int(BigInteger.ZERO), stack);
        assertTrue(new VariableParser(expected).process().isEmpty());
    }

    @Test
    void processDefined() {
        Field field = Field().withName("value").withType(I16).complete();
        Stack stack = new Stack().define(field);
        State expected = new State(new Variable("value"), stack);
        assertEquals(expected, new VariableParser(expected)
                .process()
                .orElseThrow());
    }
}