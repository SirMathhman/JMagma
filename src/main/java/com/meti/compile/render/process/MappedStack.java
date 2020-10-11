package com.meti.compile.render.process;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.scope.UndefinedException;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MappedStack implements Stack {
    private final Deque<Frame> frames;

    public MappedStack() {
        this(new LinkedList<>(List.of(new MappedFrame())));
    }

    public MappedStack(Deque<Frame> frames) {
        this.frames = frames;
    }

    @Override
    public String toString() {
        return frames.stream()
                .map(Frame::toString)
                .collect(Collectors.joining(",", "[", "]"));
    }

    @Override
    public Stack define(Field field) {
        var newFrames = new LinkedList<>(frames);
        var oldLast = newFrames.pop();
        var newLast = oldLast.define(field);
        newFrames.push(newLast);
        return new MappedStack(newFrames);
    }

    @Override
    public boolean isDefined(String name) {
        return frames.stream().anyMatch(frame -> frame.isDefined(name));
    }

    @Override
    public Field getDefinition(String name) {
        return frames.stream()
                .filter(frame -> frame.isDefined(name))
                .map(frame -> frame.getDefinition(name))
                .findFirst()
                .orElseThrow(() -> invalidateName(name));
    }

    private UndefinedException invalidateName(String name) {
        var format = "%s is not defined in %s";
        var message = format.formatted(name, this);
        return new UndefinedException(message);
    }
}
