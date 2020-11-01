package com.meti.call.function;

import com.meti.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.meti.call.function.FunctionType.FunctionType;
import static com.meti.primitive.Primitive.Void;
import static com.meti.primitive.Primitive.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FunctionTypeTest {
    @Test
    void builder() {
        Assertions.assertEquals("void (*myFunction)(unsigned char ,long long )", createType()
                .render("myFunction"));
    }

    private Type createType() {
        return FunctionType()
                .withReturnType(Void)
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
        assertEquals("void (*myFunction)(unsigned char ,long long )", new FunctionType(Void, List.of(U8, I64)).render("myFunction"));
    }

    @Test
    void is() {
        assertTrue(new FunctionType(I16, Collections.emptyList()).is(Type.Group.Function));
    }
}