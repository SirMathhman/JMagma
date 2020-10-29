package com.meti;

public interface Strategy {
    StrategyBuffer process(StrategyBuffer buffer, char c);
}
