package com.meti.compile.scope.vars;

import com.meti.compile.Node;
import com.meti.compile.Type;
import com.meti.compile.primitive.Primitive;
import com.meti.compile.scope.field.Field;
import com.meti.compile.state.Stack;
import com.meti.compile.state.State;
import org.junit.jupiter.api.Test;

import static com.meti.compile.scope.field.Field.Field;
import static org.junit.jupiter.api.Assertions.*;

class VariableResolverTest {

    @Test
    void resolve() {
        Field identity = Field()
                .withName("value")
                .withType(Primitive.I16)
                .complete();
        Node root = new Variable("value");
        Stack stack = new Stack().define(identity);
        State state = new State(root, stack);
        assertEquals(Primitive.I16, new VariableResolver(state)
                .resolve()
                .orElseThrow());
    }
}