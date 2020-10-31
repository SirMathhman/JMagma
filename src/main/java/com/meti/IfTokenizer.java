package com.meti;

import java.util.Optional;

public class IfTokenizer extends StringTokenizer<Node> {
    public IfTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        if (content.startsWith("if") && content.contains("(") && content.contains(")")) {
            int start = content.indexOf('(');
            int end = findEnd(start);
            Node condition = extractCondition(start, end);
            Node value = extractValue(end);
            return Optional.of(new If_(condition, value));
        }
        return Optional.empty();
    }

    private Node extractCondition(int start, int end) {
        String conditionSlice = content.substring(start + 1, end);
        String conditionTrim = conditionSlice.trim();
        Node condition = new ContentNode(conditionTrim);
        return condition;
    }

    private Node extractValue(int end) {
        String valueSlice = content.substring(end + 1);
        String valueTrim = valueSlice.trim();
        return new ContentNode(valueTrim);
    }

    private int findEnd(int start) {
        int depth = 0;
        for (int i = start + 1; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c == ')' && depth == 0) {
                return i;
            } else {
                if (c == '(') depth++;
                if (c == ')') depth--;
            }
        }
        return -1;
    }
}
