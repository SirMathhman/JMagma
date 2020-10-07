package com.meti.compile.render.type;

import com.meti.compile.render.Renderable;

public interface Type extends Renderable {
    String render(String name);

    @Override
    default String render() {
        return render("");
    }
}
