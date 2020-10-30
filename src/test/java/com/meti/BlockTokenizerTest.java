package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTokenizerTest {

    @Test
    void tokenize() {
        Node expected = Block.Block()
                .append(new ContentNode("test0"))
                .append(new ContentNode("test1"))
                .complete();
        Node actual = new BlockTokenizer("{test0;test1}")
                .tokenize()
                .orElseThrow();
        assertEquals(expected, actual);
    }
}