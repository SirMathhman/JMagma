package com.meti.compile;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.field.InlineField;
import com.meti.compile.render.node.ContentNode;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.primitive.ImplicitType;
import org.junit.jupiter.api.Test;

import static com.meti.compile.TokenizerStage.Tokenize;
import static com.meti.compile.render.field.InlineField.Field;
import static com.meti.compile.render.node.ContentNode.ContentNode;
import static com.meti.compile.render.node.Node.Group.Function;
import static com.meti.compile.render.primitive.ImplicitType.ImplicitType_;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TokenizerStageTest {
    @Test
    void applyImplicits() {
        var node = ContentNode("def supplier() => {return 10;}");
        var root = Tokenize(node);

        assertTrue(root.is(Function));

        var identity = root.identity();
        assertEquals(Field("supplier", ImplicitType_), identity);

        assertEquals(0, root.streamFields().count());
        assertEquals("{return 10;}", root.streamChildren()
                .findFirst()
                .orElseThrow()
                .render());
    }
}