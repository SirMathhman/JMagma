package com.meti.compile.render.scope;

import com.meti.compile.render.field.InlineField;
import com.meti.compile.render.primitive.Primitive;
import com.meti.compile.render.process.InlineState;
import com.meti.compile.render.process.MappedStack;
import com.meti.compile.render.resolve.Resolver;
import com.meti.compile.render.type.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.meti.compile.render.field.InlineField.Field;
import static com.meti.compile.render.process.InlineState.State;
import static com.meti.compile.render.scope.Variable.Variable;
import static org.junit.jupiter.api.Assertions.*;

class VariableResolverTest {

    @Test
    void resolve() {
        var expected = Primitive.I8;
        var definition = Field("test", expected);
        var stack = new MappedStack().define(definition);
        var state = State(Variable("test"), stack);
        var actual = new VariableResolver(state)
                .resolve()
                .orElseThrow();
        assertSame(expected, actual);
    }
}