package com.meti.compile.render.block.function;

import com.meti.compile.render.node.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.meti.compile.render.block.Block.Block_;
import static com.meti.compile.render.block.function.Function.Function;
import static com.meti.compile.render.primitive.Primitive.I16;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionTest {
    @Test
    void streamChildren() {
        var expected = Block_;
        var node = Function("test", I16, expected);
        var actual = node.streamChildren().findAny().orElseThrow();
        assertSame(expected, actual);
    }
}
