package com.meti;

import java.util.Objects;

public class ContentNode implements Node {
    private final String value;

    public ContentNode(String value) {
        this.value = value;
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentNode that = (ContentNode) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String render() {
        String format = "Cannot render instances of '%s' with value '%s'.";
        String message = format.formatted(getClass(), value);
        throw new IllegalStateException(message);
    }
}
