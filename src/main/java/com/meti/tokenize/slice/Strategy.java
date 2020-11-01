package com.meti.tokenize.slice;

public interface Strategy {
    StrategyBuffer process(StrategyBuffer buffer, char c);
}
