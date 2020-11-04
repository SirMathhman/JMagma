package com.meti.compile.tokenize;

import com.meti.compile.Node;
import com.meti.compile.path.EmptyScriptPath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTokenizerTest {
    @Test
    void returnFirst() {
        assertTrue(new NodeTokenizer("""
                return perform();
                """.trim(), EmptyScriptPath.EmptyPath)
                .tokenize()
                .orElseThrow()
                .is(Node.Group.Return));
    }

    @Test
    void blockFirst() {
        assertTrue(new NodeTokenizer("""
                {
                    def perform() : I16 => {
                        return first + second;
                    }
                    return perform();
                }
                """.trim(), EmptyScriptPath.EmptyPath)
                .tokenize()
                .orElseThrow()
                .is(Node.Group.Block));
    }
}