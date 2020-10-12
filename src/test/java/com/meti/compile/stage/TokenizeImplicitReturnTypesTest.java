package com.meti.compile.stage;

import com.meti.compile.render.node.Node;
import org.junit.jupiter.api.Test;

import static com.meti.compile.stage.TokenizerStage.Tokenize;
import static com.meti.compile.render.node.ContentNode.ContentNode;
import static com.meti.compile.render.node.Node.Group.Function;
import static com.meti.compile.render.primitive.ImplicitType.ImplicitType_;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TokenizeImplicitReturnTypesTest {
    @Test
    void hasCorrectName() {
        assertEquals("supplier", Instantiate()
                .identity()
                .name());
    }

    @Test
    void hasCorrectReturnType() {
        assertEquals(ImplicitType_, Instantiate()
                .identity()
                .type()
                .secondary());
    }

    private Node Instantiate() {
        return Tokenize(ContentNode("def supplier() => {return 10;}"));
    }

    @Test
    void hasCorrectChild() {
        var root = Instantiate();
        assertEquals(0, root.streamFields().count());
        assertEquals("{return 10;}", root.streamChildren()
                .findFirst()
                .orElseThrow()
                .render());
    }

    @Test
    void tokenizesFunction() {
        assertTrue(Instantiate().is(Function));
    }
}