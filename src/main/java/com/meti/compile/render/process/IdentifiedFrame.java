package com.meti.compile.render.process;

import com.meti.compile.render.field.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class IdentifiedFrame extends MappedFrame {
    public static final String NameFormat = "%s=(";
    private final Field identity;

    private IdentifiedFrame(Field identity) {
        this(identity, new HashMap<>());
    }

    public IdentifiedFrame(Field identity, Map<String, Field> map) {
        super(map);
        this.identity = identity;
    }

    public static Frame IdentifiedFrame(Field identity) {
        return new IdentifiedFrame(identity);
    }

    @Override
    public String toString() {
        var name = identity.name();
        var prefix = NameFormat.formatted(name);
        return map.keySet().stream().collect(Collectors.joining(",", prefix, ")"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentifiedFrame that = (IdentifiedFrame) o;
        var thisKeys = map.keySet();
        var thatKeys = that.map.keySet();
        return thisKeys.containsAll(thatKeys)
               && thatKeys.containsAll(thisKeys)
               && identity.equals(that.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, map);
    }

    @Override
    protected Frame complete(Map<String, Field> copy) {
        return new IdentifiedFrame(identity, copy);
    }
}
