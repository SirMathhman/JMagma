package com.meti;

import org.junit.jupiter.api.Test;

import static com.meti.Block.Block;
import static com.meti.True.true_;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class If_Test {

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