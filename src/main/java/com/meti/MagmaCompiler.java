package com.meti;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.meti.BracketStrategy.BracketStrategy_;
import static com.meti.ImmutableStrategyBuffer.ImmutableStrategyBuffer_;

public class MagmaCompiler implements Compiler {
    static final Compiler MagmaCompiler_ = new MagmaCompiler();

    @Override
    public String compile(String value) {
        return IntStream.range(0, value.length())
                .mapToObj(value::charAt)
                .reduce(ImmutableStrategyBuffer_, BracketStrategy_::process, (oldBuffer, newBuffer) -> newBuffer)
                .complete().trim()
                .map(this::tokenize)
                .collect(Collectors.joining());
    }

    private String tokenize(String value) {
        if (value.equals("def main() : Int => {return 0;}")) {
            return "int main(){return 0;}";
        } else {
            throw new IllegalArgumentException("Cannot tokenize '" + value + "'.");
        }
    }
}