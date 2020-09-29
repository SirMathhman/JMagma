package com.meti.feature;

public interface Type extends Renderable {
    @Override
    default String render() {
        return render("");
    }

    String render(String name);
}
