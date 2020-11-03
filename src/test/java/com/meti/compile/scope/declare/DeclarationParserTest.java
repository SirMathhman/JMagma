package com.meti.compile.scope.declare;

import com.meti.compile.Node;
import com.meti.compile.primitive.Primitive;
import com.meti.compile.primitive.ints.Int;
import com.meti.compile.scope.field.Field;
import com.meti.compile.state.Stack;
import com.meti.compile.state.State;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.function.Function;

import static com.meti.compile.scope.field.Field.Field;
import static org.junit.jupiter.api.Assertions.*;

class DeclarationParserTest {
    @Test
    void processInvalid() {
        State state = new State(new Int(BigInteger.ZERO));
        assertTrue(new DeclarationParser(state)
                .process()
                .isEmpty());
    }

    @Test
    void processValid() {
        Field identity = Field()
                .withName("value")
                .withType(Primitive.I16)
                .complete();
        State state = new State(new Declaration(identity));
        State actual = new DeclarationParser(state)
                .process()
                .orElseThrow();
        assertTrue(actual.transformStack((Function<Stack, Boolean>) stack -> stack.isDefined("value")));
        assertEquals("int value=0;", actual.transformCurrent(Node::render));
    }

    @Test
    void processDefined() {
        Field identity = Field()
                .withName("value")
                .withType(Primitive.I16)
                .complete();
        State state = new State(new Declaration(identity), new Stack().define(identity));
        assertThrows(IllegalStateException.class, () -> new DeclarationParser(state).process());
    }
}