package com.meti.slice;

public class ParameterStrategy implements Strategy {
    public static final Strategy ParameterStrategy_ = new ParameterStrategy();

    @Override
    public StrategyBuffer process(StrategyBuffer buffer, char c) {
        if (c == ',' && buffer.isLevel()) {
            return buffer.complete();
        } else if (c == '(') {
            return buffer.append('(').ascend();
        } else if (c == ')') {
            return buffer.append(')').descend();
        } else {
            return buffer.append(c);
        }
    }
}
