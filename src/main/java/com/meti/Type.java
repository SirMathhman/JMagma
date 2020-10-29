package com.meti;

public interface Type extends Renderable {
    String render(String name);

    default String render() {
        return render("");
    }
}
