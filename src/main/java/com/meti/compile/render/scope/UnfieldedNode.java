package com.meti.compile.render.scope;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.node.Node;

import java.util.function.Function;

public interface UnfieldedNode extends Node {
    default Node mapByFields(Function<Field, Field> mapper) {
        return this;
    }
}
