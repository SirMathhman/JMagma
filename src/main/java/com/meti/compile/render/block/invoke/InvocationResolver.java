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
        if (state.has(Node.Group.Mapping) || state.has(Node.Group.Procedure)) {
            Node caller = state.value()
                    .streamChildren()
                    .findFirst()
                    .orElseThrow();
            return Optional.of(Resolver(state.with(caller))
                    .resolve()
                    .orElseThrow(() -> invalidateCaller(caller))
                    .secondary());
        }
        return Optional.empty();
    }

    private IllegalStateException invalidateCaller(Node caller) {
        var format = "Unable to resolve caller: %s";
        var message = format.formatted(caller);
        return new IllegalStateException(message);
    }
}
