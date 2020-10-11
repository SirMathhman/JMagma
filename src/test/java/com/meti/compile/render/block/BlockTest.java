package com.meti.compile.render.block;

import com.meti.compile.render.block.function.Return;
import com.meti.compile.render.primitive.IntNumber;
import org.junit.jupiter.api.Test;

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
}