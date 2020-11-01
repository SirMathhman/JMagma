package com.meti.block;

import com.meti.Node;
import com.meti.Node.Group;
import com.meti.Renderable;
import com.meti.vars.Variable;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.meti.block.Block.Block;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlockTest {
    @Test
    void testEquals() {
        Node expected = Block().append(new Variable("test")).complete();
        Node actual = Block().append(new Variable("test")).complete();
        assertEquals(expected, actual);
    }

    @Test
    void block() {
        assertEquals("{firstsecond}", Block()
                .append(new Variable("first"))
                .append(new Variable("second"))
                .complete()
                .render());
    }

    @Test
    void is() {
        Node node = new Block(Collections.emptyList());
        assertTrue(node.is(Group.Block));
    }

    @Test
    void render() {
        Node value0 = new Variable("first");
        Node value1 = new Variable("second");
        List<Node> list = List.of(value0, value1);
        Renderable node = new Block(list);
        assertEquals("{firstsecond}", node.render());
    }
}