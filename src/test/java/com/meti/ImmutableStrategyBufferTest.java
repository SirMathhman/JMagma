package com.meti;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.meti.ImmutableStrategyBuffer.EmptyBuffer;
import static org.junit.jupiter.api.Assertions.*;

class ImmutableStrategyBufferTest {

    @Test
    void testEqualsEmpty() {
        assertEquals(EmptyBuffer, EmptyBuffer);
    }

    @Test
    void testEqualsContent(){
        StrategyBuffer expected = new ImmutableStrategyBuffer(Collections.emptyList(), 0,"c");
        StrategyBuffer actual = new ImmutableStrategyBuffer(Collections.emptyList(), 0,"c");
        assertEquals(expected, actual);
    }

    @Test
    void trim() {
        StrategyBuffer expected = new ImmutableStrategyBuffer(List.of("test"));
        StrategyBuffer actual = new ImmutableStrategyBuffer(List.of("test"));
        assertEquals(expected, actual);
    }

    @Test
    void isLevel() {
        assertTrue(EmptyBuffer.isLevel());
    }

    @Test
    void isShallow() {
        assertTrue(EmptyBuffer.ascend().isShallow());
    }

    @Test
    void descend() {
        assertTrue(EmptyBuffer.ascend().descend().isLevel());
    }

    @Test
    void ascend() {
        assertFalse(EmptyBuffer.ascend().isLevel());
    }

    @Test
    void append() {
        StrategyBuffer expected = new ImmutableStrategyBuffer(Collections.emptyList(), 0,"c");
        StrategyBuffer actual = EmptyBuffer.append('c');
        assertEquals(expected, actual);
    }

    @Test
    void complete() {
        StrategyBuffer expected = new ImmutableStrategyBuffer(Collections.emptyList(), 0, "c").complete();
        StrategyBuffer actual = new ImmutableStrategyBuffer(Collections.singletonList("c"), 0, "");
        assertEquals(expected, actual);
    }
}