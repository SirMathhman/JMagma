package com.meti.compile.call.function;

import com.meti.compile.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.meti.compile.call.function.FunctionType.FunctionType;
import static com.meti.compile.primitive.Primitive.*;
import static com.meti.compile.primitive.Void.Void_;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FunctionTypeTest {
    @Test
    void builder() {
        assertEquals("void (*myFunction)(unsigned char ,long long )", createType()
                .render("myFunction"));
    }

    private Type createType() {
        return FunctionType()
                .withReturn(Void_)
                .withParameter(U8)
                .withParameter(I64)
                .complete();
    }

    @Test
    void testEquals() {
        Type expected = createType();
        Type actual = createType();
        assertEquals(expected, actual);
    }

    @Test
    void render() {
        assertEquals("void (*myFunction)(unsigned char ,long long )", new FunctionType(Void_, List.of(U8, I64)).render("myFunction"));
    }

    @Test
    void is() {
        assertTrue(new FunctionType(I16, Collections.emptyList()).is(Type.Group.Function));
    }
}