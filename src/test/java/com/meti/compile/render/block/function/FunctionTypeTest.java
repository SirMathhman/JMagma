package com.meti.compile.render.block.function;

import com.meti.compile.render.primitive.Primitive;
import com.meti.compile.render.primitive.PrimitiveTokenizer;
import com.meti.compile.render.type.Type;
import org.junit.jupiter.api.Test;

import static com.meti.compile.render.block.function.FunctionType.FunctionType;
import static com.meti.compile.render.primitive.Primitive.*;
import static org.junit.jupiter.api.Assertions.*;

class FunctionTypeTest {

    @Test
    void testEquals(){
        var expected = FunctionType(U8, Any);
        var actual = FunctionType(U8, Any);
        assertEquals(expected, actual);
    }

    @Test
    void withSecondary() {
        var root = FunctionType(U32, Any);
        var expected = FunctionType(U64, Any);
        var actual = root.withSecondary(U64);
        assertEquals(expected, actual);
    }
}