package com.meti.compile.scope.declare;

import com.meti.compile.scope.field.Field;
import com.meti.compile.Node;
import com.meti.compile.Node.Group;
import com.meti.compile.primitive.Primitive;
import org.junit.jupiter.api.Test;

import static com.meti.compile.scope.field.Field.Field;
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

    @Test
    void testEquals() {
        Node expected = createDeclaration();
        Node actual = createDeclaration();
        assertEquals(expected, actual);
    }
}