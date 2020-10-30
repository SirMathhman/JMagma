package com.meti;

import java.util.function.Function;

public interface Type extends Renderable {
    default <T, R> R mapContent(Class<T> clazz, Function<T, R> function) {
        Class<? extends Type> thisClass = getClass();
        String format = "Instances of '%s' have no content.";
        String message = format.formatted(thisClass);
        throw new IllegalStateException(message);
    }

    boolean is(Group group);

    enum Group {
        Content,
        Primitive
    }

    default String render(String name){
        Class<? extends Type> clazz = getClass();
        String format = "Cannot render instances of '%s' with name '%s'.";
        String message = format.formatted(clazz, name);
        throw new IllegalStateException(message);
    }

    default String render() {
        return render("");
    }
}
