package com.meti.compile.render.process;

import com.meti.compile.render.field.Field;

import java.util.HashMap;
import java.util.Map;
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
    public Frame define(Field field) {
        Map<String, Field> copy = new HashMap<>(map);
        copy.put(field.name(), field);
        return new MappedFrame(copy);
    }

    @Override
    public boolean isDefined(String name) {
        return map.containsKey(name);
    }
}
