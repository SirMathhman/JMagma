package com.meti.compile.render.block.function;

import org.junit.jupiter.api.Test;

import static com.meti.compile.render.block.function.Function.Function;
import static com.meti.compile.render.block.function.FunctionParser.FunctionParser;
import static com.meti.compile.render.block.structure.NamedStructureType.StructureType;
import static com.meti.compile.render.block.structure.ObjectType.ObjectType;
import static com.meti.compile.render.process.InlineState.State;
import static com.meti.compile.render.process.MappedStack.Stack_;
import static com.meti.compile.render.scope.Variable.This;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionParserTest {

    @Test
    void validateThisKeyword() {
        var returnType = StructureType("Dummy");
        var node = Function("Dummy", returnType, This);
        var state = State(node, Stack_);
        var actual = FunctionParser(state).process().orElseThrow();
        var expected = Function("Dummy", ObjectType("Dummy"), null);
        assertEquals(actual, actual);
    }
}