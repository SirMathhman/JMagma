package com.meti.compile.render.node;

import java.util.function.Function;

public class ContentNode implements Node {
    public static Function<String, Node> ContentNode_ = ContentNode::ContentNode;
    private final String content;

    private ContentNode(String content) {
        this.content = content;
    }

    public static ContentNode ContentNode(String content) {
        return new ContentNode(content);
    }

    @Override
    public String content() {
        return content;
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Content;
    }

    @Override
    public <T> T value(Class<T> clazz) {
        return clazz.cast(content);
    }

    @Override
    public Node withValue(Object value) {
        return ContentNode(value.toString());
    }

    @Override
    public String render() {
        var format = "Cannot render node with content '%s'.";
        var message = format.formatted(content);
        throw new UnrenderableException(message);
    }


}
