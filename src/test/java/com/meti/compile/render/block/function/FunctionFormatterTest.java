package com.meti.compile.render.block.function;

import org.junit.jupiter.api.Test;

import static com.meti.compile.render.block.Block.Block;
import static com.meti.compile.render.block.function.Function.Function;
import static com.meti.compile.render.block.function.Return.Return;
import static com.meti.compile.render.primitive.ImplicitType.ImplicitType_;
import static com.meti.compile.render.primitive.IntNumber.Int;
import static com.meti.compile.render.process.InlineState.State;
import static com.meti.compile.render.process.MappedCallStack.Stack_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionFormatterTest {
    @Test
    void normal(){
        var value = Block(Return(Int(10)));
        var root = Function("supplier", ImplicitType_, value);
        var expected = State(root, Stack_);
        var state = new FunctionFormatter(expected).process().orElseThrow();
        assertEquals(root, state.getValue());
        assertEquals(Stack_, state.getScope());
    }
}