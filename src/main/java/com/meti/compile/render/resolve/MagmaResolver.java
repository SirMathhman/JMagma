package com.meti.compile.render.resolve;

import com.meti.compile.render.block.BlockResolver;
import com.meti.compile.render.block.function.ReturnResolver;
import com.meti.compile.render.block.invoke.InvocationResolver;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.primitive.IntNumberResolver;
import com.meti.compile.render.process.State;
import com.meti.compile.render.scope.VariableResolver;
import com.meti.compile.render.type.Type;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MagmaResolver extends AbstractResolver {
    private static final List<Function<State, Resolver>> Factories = List.of(
            VariableResolver::new,
            InvocationResolver::new,
            ReturnResolver::new,
            BlockResolver::new,
            IntNumberResolver::new);

    public MagmaResolver(State state) {
        super(state);
    }

    public static MagmaResolver Resolver(State state) {
        return new MagmaResolver(state);
    }

    @Override
    public Optional<Type> resolve() {
        return Factories.stream()
                .map(factory -> factory.apply(state))
                .map(Resolver::resolve)
                .flatMap(Optional::stream)
                .findFirst();
    }
}
