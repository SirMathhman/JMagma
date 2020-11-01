package com.meti.bracket;

import com.meti.Splitter;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.meti.bracket.BracketStrategy.BracketStrategy_;
import static com.meti.ImmutableStrategyBuffer.EmptyBuffer;

public class BracketSplitter implements Splitter {
    private final String value;

    public BracketSplitter(String value) {
        this.value = value;
    }

    @Override
    public Stream<String> split() {
        return IntStream.range(0, value.length())
                .mapToObj(value::charAt)
                .reduce(EmptyBuffer, BracketStrategy_::process, (oldBuffer, newBuffer) -> newBuffer)
                .complete().trim();
    }
}
