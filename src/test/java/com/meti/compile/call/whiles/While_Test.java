package com.meti.compile.call.whiles;

import com.meti.compile.Node;
import org.junit.jupiter.api.Test;

import static com.meti.compile.call.block.Block.Block;
import static com.meti.compile.primitive.True.true_;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class While_Test {
    @Test
    void testEquals() {
        assertEquals(createNode(), createNode());
    }

    @Test
    void is() {
        assertTrue(createNode().is(Node.Group.While));
    }

    private While_ createNode() {
        return new While_(true_, Block().complete());
    }

    @Test
    void render() {
        assertEquals("while(1){}", createNode().render());
    }
}