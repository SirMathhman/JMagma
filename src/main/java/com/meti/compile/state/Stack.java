package com.meti.compile.state;

import com.meti.compile.scope.field.Field;

import java.util.*;

public class Stack {
    private final Deque<Frame> frames;

    public Stack() {
        this(new LinkedList<>(Collections.singletonList(new Frame(Collections.emptySet()))));
    }

    public Stack(Deque<Frame> frames) {
        this.frames = frames;
    }

    public Stack exit() {
        Deque<Frame> newFrames = new LinkedList<>(frames);
        newFrames.pop();
        return new Stack(newFrames);
    }

    public Stack enter() {
        Deque<Frame> newFrames = new LinkedList<>(frames);
        newFrames.add(new Frame(Collections.emptySet()));
        return new Stack(newFrames);
    }

    public Stack defineAll(Collection<Field> fields) {
        return fields.stream().reduce(this, Stack::define, (stack, stack2) -> stack2);
    }

    public Stack define(Field field) {
        Deque<Frame> newFrames = new LinkedList<>(frames);
        Frame newFrame = newFrames.pop().define(field);
        newFrames.push(newFrame);
        return new Stack(newFrames);
    }

    public boolean isDefined(String name) {
        return frames.stream().anyMatch(frame -> frame.isDefined(name));
    }

    public Boolean isDefined(Field field) {
        return frames.stream().anyMatch(frame -> frame.isDefined(field));
    }

    static class Frame {
        private final Set<Field> entries;

        Frame(Set<Field> entries) {
            this.entries = entries;
        }

        Frame define(Field field) {
            Set<Field> newEntries = new HashSet<>(entries);
            newEntries.add(field);
            return new Frame(newEntries);
        }

        boolean isDefined(String name) {
            return entries.stream()
                    .anyMatch(entry -> entry.isNamed(name));
        }

        public boolean isDefined(Field field) {
            return entries.contains(field);
        }
    }
}
