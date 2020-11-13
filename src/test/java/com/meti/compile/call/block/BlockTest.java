package com.meti.compile.call.block;

import com.meti.compile.Node;
import com.meti.compile.Node.Group;
import com.meti.compile.Renderable;
import com.meti.compile.scope.vars.Variable;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.meti.compile.call.block.Block.Block;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlockTest {
    @Test
    void testEquals() {
        Node expected = Block().with(new Variable("test")).complete();
        Node actual = Block().with(new Variable("test")).complete();
        assertEquals(expected, actual);
    }

    @Test
    void block() {
        assertEquals("{firstsecond}", Block()
                .with(new Variable("first"))
                .with(new Variable("second"))
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