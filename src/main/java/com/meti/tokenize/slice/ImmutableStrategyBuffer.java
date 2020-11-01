package com.meti.tokenize.slice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Stream;

public class ImmutableStrategyBuffer implements StrategyBuffer {
    public static final StrategyBuffer EmptyBuffer = new ImmutableStrategyBuffer();
    private final Collection<String> list;
    private final String buffer;
    private final int depth;

    ImmutableStrategyBuffer() {
        this(Collections.emptyList());
    }

    ImmutableStrategyBuffer(Collection<String> list) {
        this(list, 0);
    }

    private ImmutableStrategyBuffer(Collection<String> list, int depth) {
        this(list, depth, "");
    }

    ImmutableStrategyBuffer(Collection<String> list, int depth, String buffer) {
        this.list = list;
        this.buffer = buffer;
        this.depth = depth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutableStrategyBuffer that = (ImmutableStrategyBuffer) o;
        return depth == that.depth &&
               Objects.equals(list, that.list) &&
               Objects.equals(buffer, that.buffer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list, buffer, depth);
    }

    @Override
    public Stream<String> trim() {
        return list.stream()
                .filter(s -> !s.isBlank())
                .map(String::trim);
    }

    @Override
    public boolean isLevel() {
        return depth == 0;
    }

    @Override
    public boolean isShallow() {
        return depth == 1;
    }

    @Override
    public StrategyBuffer descend() {
        return new ImmutableStrategyBuffer(list, depth - 1, buffer);
    }

    @Override
    public StrategyBuffer ascend() {
        return new ImmutableStrategyBuffer(list, depth + 1, buffer);
    }

    @Override
    public StrategyBuffer append(char c) {
        return new ImmutableStrategyBuffer(list, depth, buffer + c);
    }

    @Override
    public StrategyBuffer complete() {
        Collection<String> newList = new ArrayList<>(list);
        newList.add(buffer);
        return new ImmutableStrategyBuffer(newList, depth);
    }
}
