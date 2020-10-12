package com.meti.compile.render.block.structure;

import com.meti.compile.render.node.Node;
import org.junit.jupiter.api.Test;

import static com.meti.compile.render.block.structure.Construction.Construct;
import static com.meti.compile.render.primitive.IntNumber.Int;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConstructionTest {

    @Test
    void is() {
        var node = Construction.Construct();
        assertTrue(node.is(Node.Group.Construction));
    }

    @Test
    void renderSingle() {
        var node = Construction.Construct(Int(10));
        assertEquals("{10}", node.render());
    }

    @Test
    void renderMultiple() {
        var node = Construction.Construct(Int(10), Int(20));
        assertEquals("{10,20}", node.render());
    }
}