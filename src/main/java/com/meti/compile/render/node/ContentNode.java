package com.meti.compile.render.node;

import java.util.function.Function;

public class ContentNode implements LeafNode, UnidentifiedNode, UnfieldedNode {
    private final String content;

    public ContentNode(String content) {
        this.content = content;
    }

    @Override
    public <T> T transformContent(Function<String, T> function) {
        return function.apply(content);
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
        return new ContentNode(value.toString());
    }

    @Override
    public String render() {
        var format = "Cannot render node with content '%s'.";
        var message = format.formatted(content);
        throw new UnrenderableException(message);
    }
}
