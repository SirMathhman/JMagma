package com.meti.compile.render.block.invoke;

import com.meti.compile.render.primitive.Primitive;
import com.meti.compile.render.process.MappedStack;
import com.meti.compile.render.type.TypeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static com.meti.compile.render.block.function.FunctionType.FunctionType;
import static com.meti.compile.render.block.invoke.Mapping.Mapping;
import static com.meti.compile.render.field.InlineField.Field;
import static com.meti.compile.render.process.InlineState.State;
import static com.meti.compile.render.scope.Variable.Variable;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InvocationResolverTest {

    @Test
    void notAFunction(){
        var caller = Variable("test");
        var value = Mapping(caller);
        var declaration = Field("test", Primitive.I16);
        var stack = new MappedStack()
                .define(declaration);
        var state = State(value, stack);
        assertThrows(TypeException.class, () -> new InvocationResolver(state).resolve());
    }

    @Test
    void valid() {
        var caller = Variable("test");
        var value = Mapping(caller);
        var expected = Primitive.I16;
        var type = FunctionType(expected);
        var declaration = Field("test", type);
        var stack = new MappedStack()
                .define(declaration);
        var state = State(value, stack);
        var actual = new InvocationResolver(state)
                .resolve()
                .orElseThrow();
        assertSame(expected, actual);
    }
}