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
            return Optional.of(MagmaResolver.Resolver(state.with(state.value().value(Node.class)))
                    .resolve()
                    .orElseThrow(() -> invalidateReturn(state.value())));
        }
        return Optional.empty();
    }

    private IllegalStateException invalidateReturn(Node value) {
        var format = "Unable to resolve return value of: %s";
        var message = format.formatted(value);
        return new IllegalStateException(message);
    }
}
