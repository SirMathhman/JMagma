package com.meti.compile.tokenize.slice;

public interface Strategy {
    StrategyBuffer process(StrategyBuffer buffer, char c);
}
