package com.meti.compile.render.resolve;

import com.meti.compile.render.primitive.Primitive;
import com.meti.compile.render.process.MappedStack;
import org.junit.jupiter.api.Test;

import static com.meti.compile.render.field.InlineField.Field;
import static com.meti.compile.render.process.InlineState.State;
import static com.meti.compile.render.resolve.MagmaResolver.Resolver;
import static com.meti.compile.render.scope.Variable.Variable;
import static org.junit.jupiter.api.Assertions.assertSame;

class MagmaResolverTest {
    @Test
    void variables() {
        var expected = Primitive.I8;
        var definition = Field("test", expected);
        var stack = new MappedStack().define(definition);
        var state = State(Variable("test"), stack);
        var actual = Resolver(state)
                .resolve()
                .orElseThrow();
        assertSame(expected, actual);
    }
}