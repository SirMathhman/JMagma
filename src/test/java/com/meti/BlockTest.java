package com.meti;

import com.meti.Node.Group;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.BlockView;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlockTest {

    @Test
    void block() {
        assertEquals("{firstsecond}", Block.Block()
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