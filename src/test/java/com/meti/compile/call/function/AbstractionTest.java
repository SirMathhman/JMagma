package com.meti.compile.call.function;

import com.meti.compile.Node;
import com.meti.compile.Renderable;
import com.meti.compile.Type;
import com.meti.compile.scope.field.Field;
import org.junit.jupiter.api.Test;

import static com.meti.compile.call.function.Abstraction.Abstraction;
import static com.meti.compile.call.function.FunctionType.FunctionType;
import static com.meti.compile.primitive.Primitive.I16;
import static com.meti.compile.scope.field.Field.Field;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AbstractionTest {
    @Test
    void testEquals() {
        Node expected = createNode();
        Node actual = createNode();
        assertEquals(expected, actual);
    }

    private Node createNode() {
        return Abstraction()
                .withIdentity(createIdentity())
                .complete();
    }

    @Test
    void implementation() {
        assertEquals("int main();", createNode().render());
    }

    @Test
    void is() {
        Field identity = createIdentity();
        Node node = new Abstraction(identity, emptyList());
        assertTrue(node.is(Node.Group.Abstraction));
    }

    private Field createIdentity() {
        return Field()
                .withName("main")
                .withType(createIdentityType())
                .complete();
    }

    private Type createIdentityType() {
        return FunctionType()
                .withReturn(I16)
                .complete();
    }

    @Test
    void render() {
        Field identity = createIdentity();
        Renderable node = new Abstraction(identity, emptyList());
        assertEquals("int main();", node.render());
    }
}