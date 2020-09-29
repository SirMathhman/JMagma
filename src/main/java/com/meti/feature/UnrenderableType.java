package com.meti.feature;

public interface UnrenderableType extends Type, Unrenderable {
    @Override
    default String render() {
        return Unrenderable.super.render();
    }

    default String render(String name) {
        return render();
    }
}
