package com.meti;

public class BracketStrategy implements Strategy {
    static final Strategy BracketStrategy_ = new BracketStrategy();

    @Override
    public StrategyBuffer process(StrategyBuffer buffer, char c) {
        if (c == '}' && buffer.isSurface()) {
            buffer = buffer.descend()
                    .append('}')
                    .complete();
        } else if (c == ';' && buffer.isLevel()) {
            buffer = buffer.complete();
        } else if (c == '{') {
            buffer = buffer.ascend().append(c);
        } else if (c == '}') {
            buffer = buffer.descend().append(c);
        }
        return buffer;
    }
}