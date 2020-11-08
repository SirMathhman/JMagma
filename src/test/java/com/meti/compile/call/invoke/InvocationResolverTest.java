package com.meti.compile.call.invoke;

import com.meti.compile.Node;
import com.meti.compile.Type;
import com.meti.compile.call.function.FunctionType;
import com.meti.compile.primitive.Primitive;
import com.meti.compile.scope.field.Field;
import com.meti.compile.scope.vars.Variable;
import com.meti.compile.scope.vars.VariableResolver;
import com.meti.compile.state.Stack;
import com.meti.compile.state.State;
import org.junit.jupiter.api.Test;

import static com.meti.compile.call.function.FunctionType.FunctionType;
import static com.meti.compile.call.invoke.Mapping.Mapping;
import static com.meti.compile.scope.field.Field.Field;
import static org.junit.jupiter.api.Assertions.*;

class InvocationResolverTest {

    @Test
    void resolve() {
        Stack stack = new Stack().define(createIdentity());
        Node node = createMapping();
        State state = new State(node, stack);
        assertEquals(Primitive.I16, new InvocationResolver(state)
                .resolve(VariableResolver::new)
                .orElseThrow());
    }

    private Node createMapping() {
        return Mapping()
                .withCaller(new Variable("function"))
                .complete();
    }

    private Field createIdentity() {
        return Field()
                .withName("function")
                .withType(createFunctionType())
                .complete();
    }

    private Type createFunctionType() {
        return FunctionType().withReturnType(Primitive.I16).complete();
    }
}