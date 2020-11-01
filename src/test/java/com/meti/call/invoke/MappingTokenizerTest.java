package com.meti.call.invoke;

import com.meti.Node;
import com.meti.content.ContentNode;
import org.junit.jupiter.api.Test;

import static com.meti.call.invoke.Mapping.Mapping;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MappingTokenizerTest {

    @Test
    void tokenize() {
        Node expected = Mapping()
                .withCaller(new ContentNode("caller"))
                .withArgument(new ContentNode("arg0"))
                .withArgument(new ContentNode("arg1"))
                .complete();
        Node actual = new MappingTokenizer("caller(arg0, arg1)")
                .tokenize()
                .orElseThrow();
        assertEquals(expected, actual);
    }
}