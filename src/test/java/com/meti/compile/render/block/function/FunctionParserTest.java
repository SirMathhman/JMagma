package com.meti.compile.render.block.function;

import com.meti.compile.render.block.structure.Construction;
import org.junit.jupiter.api.Test;

import static com.meti.compile.render.block.Block.Block;
import static com.meti.compile.render.block.function.Function.Function;
import static com.meti.compile.render.block.function.FunctionParser.FunctionParser;
import static com.meti.compile.render.block.function.Return.Return;
import static com.meti.compile.render.block.structure.Construction.Construct;
import static com.meti.compile.render.block.structure.NamedStructureType.StructureType;
import static com.meti.compile.render.block.structure.ObjectType.ObjectType;
import static com.meti.compile.render.field.InlineField.Field;
import static com.meti.compile.render.process.InlineState.State;
import static com.meti.compile.render.process.MappedStack.Stack_;
import static com.meti.compile.render.scope.Initialization.Initialize;
import static com.meti.compile.render.scope.Variable.This;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionParserTest {

    @Test
    void validateThisKeyword() {
        var node = Function("Dummy", StructureType("Dummy"), Block(Return(This)));
        var state = State(node, Stack_);
        var actual = FunctionParser(state)
                .process()
                .orElseThrow()
                .getValue();
        var returnType = ObjectType("Dummy");
        var value = Block(
                Initialize(Field("this", returnType), Construction.Construct()),
                Return(This)
        );
        var expected = Function("Dummy", returnType, value);
        assertEquals(expected, actual);
    }
}