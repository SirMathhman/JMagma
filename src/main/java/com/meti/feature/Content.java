package com.meti.feature;

import java.util.Optional;
import java.util.function.Function;

public class Content implements LeafToken, Unrenderable {
    private final String content;

    public Content(String content) {
        this.content = content;
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
