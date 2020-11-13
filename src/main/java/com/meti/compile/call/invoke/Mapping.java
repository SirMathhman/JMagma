package com.meti.compile.call.invoke;

import com.meti.compile.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mapping implements Node {
    private final Node caller;
    private final List<Node> arguments;

    public Mapping(Node caller, List<Node> arguments) {
        this.caller = caller;
        this.arguments = arguments;
    }

    static None Mapping() {
        return new None(Collections.emptyList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mapping mapping = (Mapping) o;
        return Objects.equals(caller, mapping.caller) &&
               Objects.equals(arguments, mapping.arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caller, arguments);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Mapping;
    }

    @Override
    public String render() {
        return caller.render() + renderArguments();
    }

    private String renderArguments() {
        return arguments.stream()
                .map(Node::render)
                .collect(Collectors.joining(",", "(", ")"));
    }

    @Override
    public <T, R> R transformValue(Class<T> clazz, Function<T, R> function) {
        return function.apply(clazz.cast(caller));
    }

    @Override
    public Node mapByChild(Function<Node, Node> mapping) {
        Node newCaller = mapping.apply(caller);
        Complete builder = Mapping().withCaller(newCaller);
        return arguments.stream()
                .map(mapping)
                .reduce(builder, Complete::withArgument, (builder0, builder1) -> builder1)
                .complete();
    }

    static abstract class Builder<T> {
        protected final List<Node> arguments;

        protected Builder(List<Node> arguments) {
            this.arguments = arguments;
        }

        public T withArgument(Node argument) {
            List<Node> newArguments = new ArrayList<>(arguments);
            newArguments.add(argument);
            return complete(newArguments);
        }

        protected abstract T complete(List<Node> newArguments);
    }

    static class Complete extends Builder<Complete> {
        private final Node caller;

        public Complete(List<Node> arguments, Node caller) {
            super(arguments);
            this.caller = caller;
        }

        @Override
        protected Complete complete(List<Node> newArguments) {
            return new Complete(newArguments, caller);
        }

        Node complete() {
            return new Mapping(caller, arguments);
        }
    }

    static class None extends Builder<None> {
        protected None(List<Node> arguments) {
            super(arguments);
        }

        Complete withCaller(Node caller) {
            return new Complete(arguments, caller);
        }

        @Override
        protected None complete(List<Node> newArguments) {
            return new None(newArguments);
        }
    }
}
