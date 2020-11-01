package com.meti.slice;

public interface Strategy {
    StrategyBuffer process(StrategyBuffer buffer, char c);
}
