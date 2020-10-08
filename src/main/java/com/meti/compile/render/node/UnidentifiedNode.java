package com.meti.compile.render.node;

import com.meti.compile.render.field.Field;

public interface UnidentifiedNode extends Node {
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
