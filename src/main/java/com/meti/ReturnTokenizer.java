package com.meti;

import java.util.Optional;

public class ReturnTokenizer extends StringTokenizer<Node> {
    public ReturnTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        if(content.startsWith("return ")) {
            String returnSlice = content.substring(7);
            String returnTrim = returnSlice.trim();
            Node value = new ContentNode(returnTrim);
            return Optional.of(new Return(value));
        }
        return Optional.empty();
    }
}
