package com.meti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

public class ImmutableStrategyBuffer implements StrategyBuffer {
    public static final StrategyBuffer ImmutableStrategyBuffer_ = new ImmutableStrategyBuffer();
    private final Collection<String> list;
    private final StringBuilder buffer;
    private final int depth;

    private ImmutableStrategyBuffer() {
        this(Collections.emptyList(), new StringBuilder(), 0);
    }

    private ImmutableStrategyBuffer(Collection<String> list, StringBuilder buffer, int depth) {
        this.list = list;
        this.buffer = buffer;
        this.depth = depth;
    }

    @Override
    public Stream<String> trim() {
        return list.stream()
                .filter(s -> !s.isBlank());
    }

    @Override
    public boolean isLevel() {
        return depth == 0;
    }

    @Override
    public boolean isSurface() {
        return depth == 1;
    }

    @Override
    public StrategyBuffer descend() {
        return new ImmutableStrategyBuffer(list, buffer, depth - 1);
    }

    @Override
    public StrategyBuffer ascend() {
        return new ImmutableStrategyBuffer(list, buffer, depth + 1);
    }

    @Override
    public StrategyBuffer append(char c) {
        return new ImmutableStrategyBuffer(list, buffer.append(c), depth + 1);
    }

    @Override
    public StrategyBuffer complete() {
        Collection<String> newList = new ArrayList<>(list);
        String bufferAsString = buffer.toString();
        newList.add(bufferAsString);
        StringBuilder buffer = new StringBuilder();
        return new ImmutableStrategyBuffer(newList, buffer, depth);
    }
}
