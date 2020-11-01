package com.meti.tokenize.slice;

import java.util.stream.Stream;

public interface StrategyBuffer {
    Stream<String> trim();

    boolean isLevel();

    boolean isShallow();

    StrategyBuffer descend();

    StrategyBuffer ascend();

    StrategyBuffer append(char c);

    StrategyBuffer complete();
}
