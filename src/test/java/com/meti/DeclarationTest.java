package com.meti;

import com.meti.Node.Group;
import org.junit.jupiter.api.Test;

import static com.meti.Field.Field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeclarationTest {
    private Node createDeclaration() {
        return new Declaration(createIdentity());
    }

    private Field createIdentity() {
        return Field()
                .withName("value")
                .withType(Primitive.I16)
                .complete();
    }

    @Test
    void is() {
        assertTrue(createDeclaration().is(Group.Declaration));
    }

    @Test
    void render() {
        assertEquals("int value;", createDeclaration().render());
    }
}