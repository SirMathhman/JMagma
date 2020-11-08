package com.meti.compile.call.invoke;

import com.meti.compile.Node;
import com.meti.compile.Type;
import com.meti.compile.resolve.Resolver;
import com.meti.compile.resolve.StateResolver;
import com.meti.compile.state.State;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class InvocationResolver extends StateResolver {
    public InvocationResolver(State state) {
        super(state);
    }

    @Override
    public Optional<Type> resolve(Function<State, Resolver<State>> factory) {
        if (state.has(Node.Group.Mapping)) {
            Type type = state.transformCurrent(node -> node.transformValue(Node.class, node1 -> factory.apply(state.with(node1))
                    .resolve(factory)
                    .orElseThrow(() -> new IllegalArgumentException("Failed to resolve caller: " + node1))));
            return Optional.of(type.secondary());
        }
        return Optional.empty();
    }
}
