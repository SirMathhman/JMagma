package com.meti.feature;

import java.util.Optional;
import java.util.function.Function;

public class ContentType implements LeafType, UnrenderableType {
    private final String content;

    public ContentType(String content) {
        this.content = content;
    }

    @Override
    public <R> Optional<R> transformContent(Function<String, R> mapping) {
        return Optional.of(mapping.apply(content));
    }
}
