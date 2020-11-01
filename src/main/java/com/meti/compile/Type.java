package com.meti.compile;

import java.util.function.Function;

public interface Type extends Renderable {
    default Type mapByChild(Function<Type, Type> mapping) {
        return this;
    }

    default <T, R> R mapContent(Class<T> clazz, Function<T, R> function) {
        Class<? extends Type> thisClass = getClass();
        String format = "Instances of '%s' have no content.";
        String message = format.formatted(thisClass);
        throw new IllegalStateException(message);
    }

    default Type secondary() {
        Class<? extends Type> thisClass = getClass();
        String format = "Instances of '%s' have no secondary types.";
        String message = format.formatted(thisClass);
        throw new IllegalStateException(message);
    }

    boolean is(Group group);

    default String render(String name) {
        Class<? extends Type> clazz = getClass();
        String format = "Cannot render instances of '%s' with name '%s'.";
        String message = format.formatted(clazz, name);
        throw new IllegalStateException(message);
    }

    default String render() {
        return render("");
    }

    enum Group {
        Content,
        Function, Primitive
    }
}
