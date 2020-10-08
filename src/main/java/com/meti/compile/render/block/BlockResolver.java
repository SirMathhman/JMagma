package com.meti.compile.render.block;

import com.meti.compile.render.node.Node;
import com.meti.compile.render.resolve.AbstractResolver;
import com.meti.compile.render.resolve.MagmaResolver;
import com.meti.compile.render.type.Type;

import java.util.List;
import java.util.Optional;

public class BlockResolver extends AbstractResolver {
    public BlockResolver(Node current) {
        super(current);
    }

    @Override
    public Optional<Type> resolve() {
        if (current.is(Node.Group.Block)) {
            return Optional.of(current.transformChildren(this::resolveLast));
        }
        return Optional.empty();
    }

    private Type resolveLast(List<? extends Node> nodes) {
        if (nodes.isEmpty()) throw new IllegalStateException("There are no values to resolve.");
        var last = nodes.get(nodes.size() - 1);
        return new MagmaResolver(last)
                .resolve()
                .orElseThrow(() -> invalidateLast(last));
    }

    private IllegalArgumentException invalidateLast(Node last) {
        var format = "Unable to resolve the block's last statement: %s";
        var message = format.formatted(last);
        return new IllegalArgumentException(message);
    }
}
