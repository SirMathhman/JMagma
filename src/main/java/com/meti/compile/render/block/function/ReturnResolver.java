package com.meti.compile.render.block.function;

import com.meti.compile.render.node.Node;
import com.meti.compile.render.process.State;
import com.meti.compile.render.resolve.AbstractResolver;
import com.meti.compile.render.resolve.MagmaResolver;
import com.meti.compile.render.type.Type;

import java.util.Optional;

public class ReturnResolver extends AbstractResolver {
    public ReturnResolver(State state) {
        super(state);
    }

    @Override
    public Optional<Type> resolve() {
        if (state.has(Node.Group.Return)) {
            return Optional.of(MagmaResolver.Resolver(state.with(state.getValue().value(Node.class)))
                    .resolve()
                    .orElseThrow(() -> invalidateReturn(state.getValue())));
        }
        return Optional.empty();
    }

    private IllegalStateException invalidateReturn(Node value) {
        var format = "Unable to resolve return getValue of: %s";
        var message = format.formatted(value);
        return new IllegalStateException(message);
    }
}
