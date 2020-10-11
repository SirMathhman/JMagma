package com.meti.compile.render.process;

import com.meti.compile.render.scope.Initialization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static com.meti.compile.render.field.InlineField.Field;
import static com.meti.compile.render.primitive.ImplicitType.ImplicitType_;
import static com.meti.compile.render.primitive.IntNumber.Int;
import static com.meti.compile.render.process.InlineState.State;
import static org.junit.jupiter.api.Assertions.*;

class InitializationParserTest {

    @Test
    void process() {
        var identity = Field("test", ImplicitType_);
        var node = new Initialization(identity, Int(BigInteger.ONE));
        var stack = new MappedStack();
        var state = State(node, stack);
        var value = new InitializationParser(state)
                .process()
                .orElseThrow()
                .getValue()
                .render();
        assertEquals("char test=1;", value);
    }
}