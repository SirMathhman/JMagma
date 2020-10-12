package com.meti.compile.render.process;

import com.meti.compile.render.field.Field;

import java.util.HashMap;
import java.util.Map;

public class MappedFrame implements Frame {
    protected final Map<String, Field> map;

    public MappedFrame(Map<String, Field> map) {
        this.map = map;
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
