package com.meti.compile.render.process;

import com.meti.compile.render.field.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmptyFrame extends MappedFrame {
    public EmptyFrame() {
        this(new HashMap<>());
    }

    public EmptyFrame(Map<String, Field> map) {
        super(map);
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
}
