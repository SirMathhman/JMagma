package com.meti.compile.render.block.structure;

import org.junit.jupiter.api.Test;

import static com.meti.compile.render.block.structure.ObjectType.ObjectType;
import static com.meti.compile.render.field.SimpleField.Field;
import static com.meti.compile.render.process.InlineState.State;
import static com.meti.compile.render.process.MappedStack.Stack_;
import static com.meti.compile.render.scope.Variable.this_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ThisResolverTest {

    @Test
    void resolve() {
        var stack_ = Stack_.define(Field("test", null));
        var state = State(this_, stack_);
        var type = new ThisResolver(state)
                .resolve()
                .orElseThrow();
        assertEquals(ObjectType("test"), type);
    }
}