package com.meti.compile.render.node;

import com.meti.compile.render.UnrenderableException;

import java.util.function.Function;

public class ContentNode implements Node{
    private final String content;

    public ContentNode(String content) {
        this.content = content;
    }

    @Override
    public <T> T transformContent(Function<String, T> function) {
        return function.apply(content);
    }

    @Override
    public String render() {
        var format = "Cannot render node with content '%s'.";
        var message = format.formatted(content);
        throw new UnrenderableException(message);
    }
}
