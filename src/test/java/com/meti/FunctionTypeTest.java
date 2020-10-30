package com.meti;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.meti.FunctionType.FunctionType;
import static com.meti.Primitive.Void;
import static com.meti.Primitive.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FunctionTypeTest {
    @Test
    void builder() {
        assertEquals("void (*myFunction)(unsigned char ,long long )", FunctionType()
                .withReturnType(Void)
                .withParameter(U8)
                .withParameter(I64)
                .complete()
                .render("myFunction"));
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