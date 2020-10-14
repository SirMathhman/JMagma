package com.meti.compile.render.process;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.scope.UndefinedException;

import java.util.*;
import java.util.stream.Collectors;

import static com.meti.compile.render.process.IdentifiedFrame.IdentifiedFrame;

@Deprecated
public class MappedCallStack implements CallStack {
    @Deprecated
    public static CallStack Stack_ = new MappedCallStack();
    private final Deque<Frame> frames;

    @Deprecated
    public MappedCallStack() {
        this(new LinkedList<>(List.of(new EmptyFrame())));
    }

    public MappedCallStack(Deque<Frame> frames) {
        this.frames = frames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MappedCallStack that = (MappedCallStack) o;
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
    public CallStack define(Field field) {
        var newFrames = new LinkedList<>(frames);
        var oldLast = newFrames.pollLast();
        assert oldLast != null;
        var newLast = oldLast.define(field);
        newFrames.add(newLast);
        return new MappedCallStack(newFrames);
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
    public CallStack enter() {
        Deque<Frame> newFrames = new LinkedList<>(frames);
        newFrames.push(new EmptyFrame());
        return new MappedCallStack(newFrames);
    }

    @Override
    public CallStack defineAll(List<Field> fields) {
        CallStack current = this;
        for (Field field : fields) {
            current = define(field);
        }
        return current;
    }

    @Override
    public CallStack exit() {
        Deque<Frame> newFrames = new LinkedList<>(frames);
        newFrames.pollLast();
        return new MappedCallStack(newFrames);
    }

    @Override
    public Optional<Context> getContext() {

        return Optional.empty();
    }

    private UndefinedException invalidateName(String name) {
        var format = "%s is not defined in %s";
        var message = format.formatted(name, this);
        return new UndefinedException(message);
    }

    @Override
    public CallStack enterWithIdentity(Field identity) {
        Deque<Frame> newFrames = new LinkedList<>(frames);
        newFrames.addLast(IdentifiedFrame(identity));
        return new MappedCallStack(newFrames);
    }
}
