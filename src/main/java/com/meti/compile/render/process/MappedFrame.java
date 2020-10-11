package com.meti.compile.render.process;

import com.meti.compile.render.field.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class MappedFrame implements Frame {
    private final Map<String, Field> map;

    public MappedFrame() {
        this(new HashMap<>());
    }

    public MappedFrame(Map<String, Field> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return map.keySet()
                .stream()
                .collect(Collectors.joining(",", "(", ")"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MappedFrame that = (MappedFrame) o;
        var thisKeys = map.keySet();
        var thatKeys = that.map.keySet();
        return thisKeys.containsAll(thatKeys) && thatKeys.containsAll(thisKeys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }

    @Override
    public Frame define(Field field) {
        Map<String, Field> copy = new HashMap<>(map);
        copy.put(field.name(), field);
        return new MappedFrame(copy);
    }

    @Override
    public boolean isDefined(String name) {
        return map.containsKey(name);
    }

    @Override
    public Field getDefinition(String name) {
        return map.get(name);
    }
}
