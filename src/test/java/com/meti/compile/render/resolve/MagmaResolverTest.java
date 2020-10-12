package com.meti.compile.render.resolve;

import com.meti.compile.render.primitive.Primitive;
import com.meti.compile.render.process.MappedStack;
import org.junit.jupiter.api.Test;

import static com.meti.compile.render.block.function.FunctionType.FunctionType;
import static com.meti.compile.render.block.invoke.Mapping.Mapping;
import static com.meti.compile.render.field.SimpleField.Field;
import static com.meti.compile.render.process.InlineState.State;
import static com.meti.compile.render.resolve.MagmaResolver.Resolver;
import static com.meti.compile.render.scope.Variable.Variable;
import static org.junit.jupiter.api.Assertions.assertSame;

class MagmaResolverTest {
    @Test
    void invocations(){
        var caller = Variable("test");
        var value = Mapping(caller);
        var expected = Primitive.I16;
        var type = FunctionType(expected);
        var declaration = Field("test", type);
        var stack = new MappedStack()
                .define(declaration);
        var state = State(value, stack);
        var actual = Resolver(state)
                .resolve()
                .orElseThrow();
        assertSame(expected, actual);
    }

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