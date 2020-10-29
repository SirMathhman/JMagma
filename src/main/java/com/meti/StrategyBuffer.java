package com.meti;

import java.util.stream.Stream;

public interface StrategyBuffer {
    Stream<String> trim();

    boolean isLevel();

    boolean isSurface();

    StrategyBuffer descend();

    StrategyBuffer ascend();

    StrategyBuffer append(char c);

    StrategyBuffer complete();
}
