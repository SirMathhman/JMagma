package com.meti.compile.call.function;

import com.meti.compile.Node;
import com.meti.compile.Renderable;
import com.meti.compile.Type;
import com.meti.compile.scope.field.Field;
import com.meti.compile.primitive.ints.Int;
import com.meti.compile.call.returns.Return;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Collections;

import static com.meti.compile.call.block.Block.Block;
import static com.meti.compile.scope.field.Field.Field;
import static com.meti.compile.call.function.FunctionType.FunctionType;
import static com.meti.compile.call.function.Implementation.Implementation;
import static com.meti.compile.primitive.Primitive.I16;
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