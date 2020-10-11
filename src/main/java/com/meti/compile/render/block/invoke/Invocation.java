package com.meti.compile.render.block.invoke;

import com.meti.compile.render.node.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Invocation implements Node {
    protected final Node caller;
    protected final List<? extends Node> arguments;

    public Invocation(Node caller, List<? extends Node> arguments) {
        this.caller = caller;
        this.arguments = arguments;
    }

    @Override
    public <T extends Container<T>> T reduce(T identity, Function<T, T> operator) {
        var withCaller = operator.apply(identity.with(caller));
        var newCaller = withCaller.value();
        var newArguments = new ArrayList<Node>();
        var current = withCaller;
        for (Node argument : arguments) {
            var processedArgument = current.with(argument);
            var apply = operator.apply(processedArgument);
            newArguments.add(apply.value());
            current = processedArgument;
        }
        var value = complete(newCaller, newArguments);
        return current.with(value);
    }

    protected abstract Invocation complete(Node newCaller, List<Node> newArguments);

    @Override
    public Stream<? extends Node> streamChildren() {
        return Stream.concat(Stream.of(caller), arguments.stream());
    }

    @Override
    public Node mapByChildren(Function<Node, Node> mapper) {
        return complete(mapper.apply(caller), mapArguments(mapper));
    }

    private List<Node> mapArguments(Function<Node, Node> mapper) {
        return arguments.stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public String render() {
        return caller.render() + renderArguments() + extension();
    }

    protected abstract String extension();

    private String renderArguments() {
        return arguments.stream()
                .map(Node::render)
                .collect(Collectors.joining(",", "(", ")"));
    }
}
