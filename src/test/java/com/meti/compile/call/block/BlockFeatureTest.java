package com.meti.compile.call.block;

import com.meti.compile.FeatureTest;
import org.junit.jupiter.api.Test;

class BlockFeatureTest extends FeatureTest {
    @Test
    void empty() {
        assertCompile("""
                {
                }""", "{}");
    }

    @Test
    void single() {
        assertCompile("""
                {
                10}""", "{10}");
    }

    @Test
    void multiple() {
        assertCompile("""
                {
                1020}""", "{10;20}");
    }
}
