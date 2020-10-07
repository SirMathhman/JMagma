package com.meti.compile.render.process;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.primitive.ImplicitType;
import com.meti.compile.render.resolve.Resolver;
import com.meti.compile.render.type.Type;

import java.util.Optional;
import java.util.function.Function;

public class InitializationProcessor extends AbstractProcessor {
    public static final String Format = "Failed to resolve value of initialization of '%s': %s";
    private final Function<Node, Resolver> factory;

    public InitializationProcessor(State state, Function<Node, Resolver> factory) {
        super(state);
        this.factory = factory;
    }

    @Override
    public Optional<State> process() {
        if (state.has(Node.Group.Initialization)) {
            var current = state.current();
            var identity = current.identity();
            var field = identity.mapByType(type -> checkImplicit(current, identity, type));
            var node = current.withIdentity(field);
            return Optional.of(state.with(node));
        }
        return Optional.empty();
    }

    private Type checkImplicit(Node current, Field identity, Type type) {
        return type == ImplicitType.ImplicitType ? resolveImplicit(current, identity) : type;
    }

    private Type resolveImplicit(Node current, Field identity) {
        var value = current.value(Node.class);
        return factory.apply(value)
                .resolve()
                .orElseThrow(() -> invalidateValue(value, identity));
    }

    private IllegalStateException invalidateValue(Node value, Field identity) {
        var message = Format.formatted(identity.name(), value.getClass());
        return new IllegalStateException(message);
    }
}
