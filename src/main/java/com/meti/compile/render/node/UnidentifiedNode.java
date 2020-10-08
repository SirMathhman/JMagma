package com.meti.compile.render.node;

import com.meti.compile.render.field.Field;

import java.util.function.Function;

public interface UnidentifiedNode extends Node {
    @Override
    default Node mapByIdentity(Function<Field, Field> mapper) {
        return this;
    }

    @Override
    default Field identity() {
        var format = "No identity exists for: %s";
        var message = format.formatted(getClass());
        throw new UnidentifiedException(message);
    }

    @Override
    default Node withIdentity(Field identity) {
        var format = "No identity exists for: %s";
        var message = format.formatted(getClass());
        throw new UnidentifiedException(message);
    }
}
