package com.meti.feature;

import java.util.Optional;
import java.util.function.Function;

public class Content implements Leaf {
    private final String content;

    public Content(String content) {
        this.content = content;
    }

    @Override
    public String render() {
        String format = "Content of '%s' cannot be rendered.";
        String message = String.format(format, content);
        throw new UnrenderableException(message);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Content;
    }

    @Override
    public <R> Optional<R> transformContent(Function<String, R> mapping) {
        return Optional.of(mapping.apply(content));
    }
}
