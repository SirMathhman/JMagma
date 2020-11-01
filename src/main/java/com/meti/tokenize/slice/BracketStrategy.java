package com.meti.tokenize.slice;

public class BracketStrategy implements Strategy {
    static final Strategy BracketStrategy_ = new BracketStrategy();

    @Override
    public StrategyBuffer process(StrategyBuffer buffer, char c) {
        if (c == '}' && buffer.isShallow()) {
            return buffer.descend()
                    .append('}')
                    .complete();
        } else if (c == ';' && buffer.isLevel()) {
            return buffer.complete();
        } else if (c == '{') {
            return buffer.ascend().append(c);
        } else if (c == '}') {
            return buffer.descend().append(c);
        } else {
            return buffer.append(c);
        }
    }
}