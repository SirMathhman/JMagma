package com.meti;

import java.util.Objects;
import java.util.function.Function;

public class ContentType implements Type {
    private final String content;

    public ContentType(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentType that = (ContentType) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public <T, R> R mapContent(Class<T> clazz, Function<T, R> function) {
        return function.apply(clazz.cast(content));
    }

    @Override
    public boolean is(Group group){
        return group == Group.Content;
    }
}
