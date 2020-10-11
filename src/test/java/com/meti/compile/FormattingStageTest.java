package com.meti.compile;

import org.junit.jupiter.api.Test;

import static com.meti.compile.FormattingStage.FormattingStage_;
import static com.meti.compile.render.block.Block.Block;
import static com.meti.compile.render.block.function.Function.Function;
import static com.meti.compile.render.block.function.Return.Return;
import static com.meti.compile.render.primitive.ImplicitType.ImplicitType_;
import static com.meti.compile.render.primitive.IntNumber.Int;
import static com.meti.compile.render.process.InlineState.State;
import static com.meti.compile.render.process.MappedStack.Stack_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FormattingStageTest {
    @Test
    void test() {
        var value = Block(Return(Int(10)));
        var root = Function("supplier", ImplicitType_, value);
        var expected = State(root, Stack_);
        var actual = FormattingStage_.apply(expected);
        assertEquals(root, actual.getValue());
        assertEquals(Stack_, actual.getScope());
    }
}