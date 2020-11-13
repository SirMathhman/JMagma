package com.meti.compile;

import static com.meti.compile.path.EmptyScriptPath.EmptyPath;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class ParserTest {
    protected void assertParse(String expected, String input) {
        Node node = new TokenizerStage(EmptyPath).apply(input);
        String actual = createParser(node)
                .process()
                .orElseThrow()
                .transformCurrent(Node::render);
        assertEquals(expected, actual);
    }

    protected abstract Parser createParser(Node node);
}
