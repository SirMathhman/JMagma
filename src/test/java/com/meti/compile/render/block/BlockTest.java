package com.meti.compile.render.block;

import com.meti.compile.render.block.function.Return;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.primitive.IntNumber;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.meti.compile.render.block.Block.Block;
import static com.meti.compile.render.block.function.Return.Return;
import static com.meti.compile.render.primitive.IntNumber.Int;
import static org.junit.jupiter.api.Assertions.*;

class BlockTest {
    @Test
    void testEquals(){
        var expected= Block(Return(Int(10)));
        var actual = Block(Return(Int(10)));
        assertEquals(expected, actual);
    }

    @Test
    void streamChildren() {
        var expected = List.of(Int(10), Int(20));
        var block = Block(expected);
        var actual = block.streamChildren().collect(Collectors.toList());
        assertIterableEquals(expected, actual);
    }
}