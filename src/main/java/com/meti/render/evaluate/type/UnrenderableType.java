package com.meti.render.evaluate.type;

import com.meti.render.Unrenderable;

public interface UnrenderableType extends Type, Unrenderable {
    @Override
    default String render() {
        return Unrenderable.super.render();
    }

    default String render(String name) {
        return render();
    }
}
