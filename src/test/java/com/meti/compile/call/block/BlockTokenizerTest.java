package com.meti.compile.call.block;

import com.meti.compile.content.ContentNode;
import com.meti.compile.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTokenizerTest {

    @Test
    void tokenize() {
        Node expected = Block.Block()
                .with(new ContentNode("test0"))
                .with(new ContentNode("test1"))
                .complete();
        Node actual = new BlockTokenizer("{test0;test1}")
                .tokenize()
                .orElseThrow();
        assertEquals(expected, actual);
    }
}