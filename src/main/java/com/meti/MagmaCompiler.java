package com.meti;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.meti.BracketStrategy.BracketStrategy_;
import static com.meti.ImmutableStrategyBuffer.EmptyBuffer;

public class MagmaCompiler implements Compiler {
    static final Compiler MagmaCompiler_ = new MagmaCompiler();

    static Stream<String> split(String value) {
        return IntStream.range(0, value.length())
                .mapToObj(value::charAt)
                .reduce(EmptyBuffer, BracketStrategy_::process, (oldBuffer, newBuffer) -> newBuffer)
                .complete().trim();
    }

    @Override
    public String compile(String value) {
        return split(value)
                .map(MagmaTokenizer::new)
                .map(Tokenizer::tokenize)
                .map(s -> s.orElseThrow(() -> invalidateToken(value)))
                .map(Node::render)
                .collect(Collectors.joining());
    }

    private IllegalArgumentException invalidateToken(String value) {
        String format = "Cannot tokenize '%s'.";
        String message = format.formatted(value);
        return new IllegalArgumentException(message);
    }

}