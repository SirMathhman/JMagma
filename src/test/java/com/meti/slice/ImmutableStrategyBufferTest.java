package com.meti.slice;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.meti.slice.ImmutableStrategyBuffer.EmptyBuffer;
import static org.junit.jupiter.api.Assertions.*;

class ImmutableStrategyBufferTest {

    @Test
    void testEqualsEmpty() {
        Assertions.assertEquals(EmptyBuffer, EmptyBuffer);
    }

    @Test
    void testEqualsContent(){
        StrategyBuffer expected = new ImmutableStrategyBuffer(Collections.emptyList(), 0,"c");
        StrategyBuffer actual = new ImmutableStrategyBuffer(Collections.emptyList(), 0,"c");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void trim() {
        StrategyBuffer expected = new ImmutableStrategyBuffer(List.of("test"));
        StrategyBuffer actual = new ImmutableStrategyBuffer(List.of("test"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void isLevel() {
        Assertions.assertTrue(EmptyBuffer.isLevel());
    }

    @Test
    void isShallow() {
        Assertions.assertTrue(EmptyBuffer.ascend().isShallow());
    }

    @Test
    void descend() {
        Assertions.assertTrue(EmptyBuffer.ascend().descend().isLevel());
    }

    @Test
    void ascend() {
        Assertions.assertFalse(EmptyBuffer.ascend().isLevel());
    }

    @Test
    void append() {
        StrategyBuffer expected = new ImmutableStrategyBuffer(Collections.emptyList(), 0,"c");
        StrategyBuffer actual = EmptyBuffer.append('c');
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void complete() {
        StrategyBuffer expected = new ImmutableStrategyBuffer(Collections.emptyList(), 0, "c").complete();
        StrategyBuffer actual = new ImmutableStrategyBuffer(Collections.singletonList("c"), 0, "");
        Assertions.assertEquals(expected, actual);
    }
}