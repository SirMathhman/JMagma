package com.meti.compile.call.ifs;

import com.meti.compile.Node;
import org.junit.jupiter.api.Test;

import static com.meti.compile.call.block.Block.Block;
import static com.meti.compile.primitive.True.true_;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class If_Test {
    @Test
    void testEquals() {
        assertEquals(createNode(), createNode());
    }

    @Test
    void is() {
        assertTrue(createNode().is(Node.Group.If));
    }

    private If_ createNode() {
        return new If_(true_, Block().complete());
    }

    @Test
    void render() {
        assertEquals("if(1){}", createNode().render());
    }
}