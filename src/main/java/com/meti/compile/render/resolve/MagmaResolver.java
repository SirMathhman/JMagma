package com.meti.compile.render.resolve;

import com.meti.compile.render.block.BlockResolver;
import com.meti.compile.render.block.function.ReturnResolver;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.primitive.IntNumberResolver;
import com.meti.compile.render.type.Type;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MagmaResolver implements Resolver {
    private static final List<Function<Node, Resolver>> Factories = List.of(
            ReturnResolver::new,
            BlockResolver::new,
            IntNumberResolver::new);

    private final Node value;

    public MagmaResolver(Node value) {
        this.value = value;
    }

    @Override
    public Optional<Type> resolve() {
        return Factories.stream()
                .map(factory -> factory.apply(value))
                .map(Resolver::resolve)
                .flatMap(Optional::stream)
                .findFirst();
    }
}
