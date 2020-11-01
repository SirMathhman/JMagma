package com.meti.call.function;

import com.meti.*;
import com.meti.scope.field.Field;
import com.meti.primitive.ints.Int;
import com.meti.call.returns.Return;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Collections;

import static com.meti.call.block.Block.Block;
import static com.meti.scope.field.Field.Field;
import static com.meti.call.function.FunctionType.FunctionType;
import static com.meti.call.function.Implementation.Implementation;
import static com.meti.primitive.Primitive.I16;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImplementationTest {
    @Test
    void testEquals() {
        Node expected = createNode();
        Node actual = createNode();
        assertEquals(expected, actual);
    }

    private Node createNode() {
        return Implementation()
                .withIdentity(createIdentity())
                .withValue(createValue())
                .complete();
    }

    @Test
    void implementation() {
        assertEquals("int main(){return 0;}", createNode().render());
    }

    @Test
    void is() {
        Field identity = createIdentity();
        Node value = createValue();
        Node node = new Implementation(identity, value, Collections.emptyList());
        assertTrue(node.is(Node.Group.Implementation));
    }

    private Field createIdentity() {
        return Field()
                .withName("main")
                .withType(createIdentityType())
                .complete();
    }

    private Type createIdentityType() {
        return FunctionType()
                .withReturnType(I16)
                .complete();
    }

    private Node createValue() {
        return Block()
                .append(new Return(new Int(BigInteger.ZERO)))
                .complete();
    }

    @Test
    void render() {
        Field identity = createIdentity();
        Node value = createValue();
        Renderable node = new Implementation(identity, value, Collections.emptyList());
        assertEquals("int main(){return 0;}", node.render());
    }
}