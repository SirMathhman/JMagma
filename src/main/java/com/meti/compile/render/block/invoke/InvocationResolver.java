package com.meti.compile.render.block.invoke;

import com.meti.compile.render.node.Node;
import com.meti.compile.render.process.State;
import com.meti.compile.render.resolve.AbstractResolver;
import com.meti.compile.render.type.Type;

import java.util.Optional;

import static com.meti.compile.render.resolve.MagmaResolver.Resolver;

public class InvocationResolver extends AbstractResolver {
    public InvocationResolver(State state) {
        super(state);
    }

    @Override
    public Optional<Type> resolve() {
        return Optional.of(state)
                .filter(this::hasInvocation)
                .map(State::getValue)
                .map(this::findCaller)
                .map(this::resolveCallerReturnType);
    }

    private boolean hasInvocation(State state) {
        return state.has(Node.Group.Mapping) || this.state.has(Node.Group.Procedure);
    }

    private Node findCaller(Node value) {
        return value.streamChildren()
                .findFirst()
                .orElseThrow(() -> noCaller(value));
    }

    private IllegalArgumentException noCaller(Node value) {
        var format = "%s has no caller.";
        var message = format.formatted(value);
        return new IllegalArgumentException(message);
    }

    private Type resolveCallerReturnType(Node caller) {
        return Resolver(state.with(caller))
                .resolve()
                .orElseThrow(() -> invalidateCaller(caller))
                .secondary();
    }

    private IllegalStateException invalidateCaller(Node caller) {
        var format = "Unable to resolve caller: %s";
        var message = format.formatted(caller);
        return new IllegalStateException(message);
    }
}
