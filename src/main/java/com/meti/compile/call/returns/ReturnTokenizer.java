package com.meti.compile.call.returns;

import com.meti.compile.Node;
import com.meti.compile.tokenize.AbstractTokenizer;
import com.meti.compile.content.ContentNode;

import java.util.Optional;

public class ReturnTokenizer extends AbstractTokenizer<Node> {
    public ReturnTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        if (content.startsWith("return ")) {
            String returnSlice = content.substring(7);
            String returnTrim = returnSlice.trim();
            Node value = new ContentNode(returnTrim);
            return Optional.of(new Return(value));
        }
        return Optional.empty();
    }
}
