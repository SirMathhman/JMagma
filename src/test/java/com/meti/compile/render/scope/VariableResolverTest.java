package com.meti.compile.render.scope;

import com.meti.compile.render.primitive.Primitive;
import com.meti.compile.render.process.MappedCallStack;
import org.junit.jupiter.api.Test;

import static com.meti.compile.render.field.SimpleField.Field;
import static com.meti.compile.render.process.InlineState.State;
import static com.meti.compile.render.scope.Variable.Variable;
import static org.junit.jupiter.api.Assertions.*;

class VariableResolverTest {

    @Test
    void resolve() {
        var expected = Primitive.I8;
        var definition = Field("test", expected);
        var stack = new MappedCallStack().define(definition);
        var state = State(Variable("test"), stack);
        var actual = new VariableResolver(state)
                .resolve()
                .orElseThrow();
        assertSame(expected, actual);
    }
}