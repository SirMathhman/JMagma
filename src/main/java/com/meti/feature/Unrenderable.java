package com.meti.feature;

public interface Unrenderable extends Renderable {
    @Override
    default String render() {
        String format = "Instances of '%s' cannot be rendered.";
        String message = String.format(format, getClass());
        throw new UnrenderableException(message);
    }
}
