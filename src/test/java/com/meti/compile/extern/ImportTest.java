package com.meti.compile.extern;

import com.meti.compile.Node;
import com.meti.compile.scope.vars.Variable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.meti.compile.extern.Import.Import;
import static org.junit.jupiter.api.Assertions.*;

class ImportTest {
    private Node createNode() {
        return Import()
                .with(new Variable("test"))
                .complete();
    }

    @Test
    void testEquals(){
        assertEquals(createNode(), createNode());
    }

    @Test
    void is() {
        assertTrue(createNode().is(Node.Group.Import));
    }

    @Test
    void render() {
        assertEquals("test", createNode().render());
    }
}