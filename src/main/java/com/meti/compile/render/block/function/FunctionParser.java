package com.meti.compile.render.block.function;

import com.meti.compile.render.node.Node;
import com.meti.compile.render.process.AbstractProcessor;
import com.meti.compile.render.process.State;
import com.meti.compile.render.resolve.MagmaResolver;
import com.meti.compile.render.type.Type;

import java.util.Optional;

public class FunctionParser extends AbstractProcessor {
    public FunctionParser(State state) {
        super(state);
    }

    @Override
    public Optional<State> process() {
        if (state.has(Node.Group.Function)) {
            var current = state.value();
            var identity = current.identity();
            var next = current.withIdentity(identity.mapByType(type -> checkImplicit(current, type)));
            var nextState = state.with(next);
            return Optional.of(nextState);
        }
        return Optional.empty();
    }

    private Type checkImplicit(Node current, Type type) {
        if (type.secondary().is(Type.Group.Implicit)) return type.mapByStart(type1 -> resolveReturnType(current));
        return type;
    }

    private Type resolveReturnType(Node current) {
        var value = current.value(Node.class);
        return MagmaResolver.Resolver(state.with(value))
                .resolve()
                .orElseThrow(() -> invalidateValue(value));
    }

    private IllegalStateException invalidateValue(Node value) {
        var format = "Unable to resolve function value of: %s";
        var message = format.formatted(value);
        return new IllegalStateException(message);
    }
}
