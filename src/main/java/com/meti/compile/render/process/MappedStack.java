package com.meti.compile.render.process;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.scope.UndefinedException;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MappedStack implements Stack {
    public static Stack Stack_ = new MappedStack();
    private final Deque<Frame> frames;

    @Deprecated
    public MappedStack() {
        this(new LinkedList<>(List.of(new MappedFrame())));
    }

    public MappedStack(Deque<Frame> frames) {
        this.frames = frames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MappedStack that = (MappedStack) o;
        return frames.containsAll(that.frames) && that.frames.containsAll(frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
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
        var oldLast = newFrames.pollLast();
        assert oldLast != null;
        var newLast = oldLast.define(field);
        newFrames.add(newLast);
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

    @Override
    public Stack enter() {
        Deque<Frame> newFrames = new LinkedList<>(frames);
        newFrames.push(new MappedFrame());
        return new MappedStack(newFrames);
    }

    @Override
    public Stack defineAll(List<Field> fields) {
        Stack current = this;
        for (Field field : fields) {
            current = define(field);
        }
        return current;
    }

    @Override
    public Stack exit() {
        Deque<Frame> newFrames = new LinkedList<>(frames);
        newFrames.pollLast();
        return new MappedStack(newFrames);
    }

    private UndefinedException invalidateName(String name) {
        var format = "%s is not defined in %s";
        var message = format.formatted(name, this);
        return new UndefinedException(message);
    }

    @Override
    public Stack enterWithIdentity(Field identity){
        throw new UnsupportedOperationException();
    }
}
