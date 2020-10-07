package com.meti.compile.feature.type;

import com.meti.compile.feature.Renderable;

public interface Type extends Renderable {
    String render(String name);

    @Override
    default String render() {
        return render("");
    }
}
