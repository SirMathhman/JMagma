package com.meti.compile.tokenize;

import com.meti.compile.Node;
import com.meti.compile.content.ContentNode;

public abstract class ConditionTokenizer extends AbstractTokenizer<Node> {
    public ConditionTokenizer(String content) {
        super(content);
    }

    protected Node extractCondition(int start, int end) {
        String conditionSlice = content.substring(start + 1, end);
        String conditionTrim = conditionSlice.trim();
        Node condition = new ContentNode(conditionTrim);
        return condition;
    }

    protected Node extractValue(int end) {
        String valueSlice = content.substring(end + 1);
        String valueTrim = valueSlice.trim();
        return new ContentNode(valueTrim);
    }

    protected int findEnd(int start) {
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
