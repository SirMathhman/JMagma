package com.meti.compile.render.block;

import com.meti.compile.render.tokenize.AbstractNodeTokenizer;
import com.meti.compile.render.node.Node;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.meti.compile.render.node.ContentNode.ContentNode_;

public class BlockTokenizer extends AbstractNodeTokenizer {
    public BlockTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        if (content.startsWith("{") && content.endsWith("}")) {
            var substring = content.substring(1, content.length() - 1);
            var values = substring.trim();
            var length = values.length();
            var builder = new StringBuilder();
            var buffer = new ArrayList<String>();
            var depth = 0;
            for (int i = 0; i < length; i++) {
                var c = values.charAt(i);
                if (c == ';' && depth == 0) {
                    buffer.add(builder.toString());
                    builder = new StringBuilder();
                } else if (c == '}' && depth == 1) {
                    depth = 0;
                    builder.append('}');
                    buffer.add(builder.toString());
                    builder = new StringBuilder();
                } else {
                    if (c == '{') depth++;
                    if (c == '}') depth--;
                    builder.append(c);
                }
            }
            buffer.add(builder.toString());
            var nodes = buffer.stream()
                    .filter(s -> !s.isBlank())
                    .map(String::trim)
                    .map(ContentNode_)
                    .collect(Collectors.toList());
            return Optional.of(Block.Block(nodes));
        }
        return Optional.empty();
    }
}
