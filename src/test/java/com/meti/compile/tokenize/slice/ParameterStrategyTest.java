package com.meti.compile.tokenize.slice;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.meti.compile.tokenize.slice.ImmutableStrategyBuffer.EmptyBuffer;
import static org.junit.jupiter.api.Assertions.*;

class ParameterStrategyTest {

    @Test
    void process() {
        String value = "const x : Int,const mapping : () => Void";
        List<String> items = IntStream.range(0, value.length())
                .mapToObj(value::charAt)
                .reduce(EmptyBuffer, ParameterStrategy.ParameterStrategy_::process, (strategyBuffer, strategyBuffer2) -> strategyBuffer2)
                .complete()
                .trim()
                .collect(Collectors.toList());
        assertIterableEquals(List.of("const x : Int", "const mapping : () => Void"), items);
    }
}