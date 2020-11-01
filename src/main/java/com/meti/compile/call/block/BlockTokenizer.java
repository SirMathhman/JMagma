package com.meti.compile.call.block;

import com.meti.compile.tokenize.slice.BracketSplitter;
import com.meti.compile.content.ContentNode;
import com.meti.compile.Node;
import com.meti.compile.tokenize.AbstractTokenizer;

import java.util.Optional;

import static com.meti.compile.call.block.Block.Block;

public class BlockTokenizer extends AbstractTokenizer<Node> {
    public BlockTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        if (content.startsWith("{") && content.endsWith("}")) {
            int length = content.length();
            String childrenSlice = content.substring(1, length - 1);
            String childrenTrim = childrenSlice.trim();
            return Optional.of(format(childrenTrim));
        }
        return Optional.empty();
    }

    private Node format(String childrenTrim) {
        return new BracketSplitter(childrenTrim)
                .split()
                .map(ContentNode::new)
                .reduce(Block(), Block.Builder::append, (builder, builder2) -> builder2)
                .complete();
    }
}
