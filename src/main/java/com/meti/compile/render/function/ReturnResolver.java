package com.meti.compile.render.function;

import com.meti.compile.render.node.Node;
import com.meti.compile.render.resolve.AbstractResolver;
import com.meti.compile.render.resolve.MagmaResolver;
import com.meti.compile.render.type.Type;

import java.util.Optional;

public class ReturnResolver extends AbstractResolver {
    public ReturnResolver(Node current) {
        super(current);
    }

    @Override
    public Optional<Type> resolve() {
        if (current.is(Node.Group.Return)) {
            var value = current.value(Node.class);
            return Optional.of(new MagmaResolver(value)
                    .resolve()
                    .orElseThrow(() -> invalidateReturn(value)));
        }
        return Optional.empty();
    }

    private IllegalStateException invalidateReturn(Node value) {
        var format = "Unable to resolve return value of: %s";
        var message = format.formatted(value);
        return new IllegalStateException(message);
    }
}
