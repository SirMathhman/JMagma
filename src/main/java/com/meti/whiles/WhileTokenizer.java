package com.meti.whiles;

import com.meti.ConditionTokenizer;
import com.meti.Node;

import java.util.Optional;

public class WhileTokenizer extends ConditionTokenizer {
    public WhileTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        if (content.startsWith("while") && content.contains("(") && content.contains(")")) {
            int start = content.indexOf('(');
            int end = findEnd(start);
            Node condition = extractCondition(start, end);
            Node value = extractValue(end);
            return Optional.of(new While_(condition, value));
        }
        return Optional.empty();
    }

}
