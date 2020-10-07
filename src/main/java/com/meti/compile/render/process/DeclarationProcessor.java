package com.meti.compile.render.process;

import com.meti.compile.render.node.Node;
import com.meti.compile.render.primitive.ImplicitType;

import java.util.Optional;

public class DeclarationProcessor implements Processor {
    public static final String Format = "Declaration for '%s' doesn't have a type.";
    private final State state;

    public DeclarationProcessor(State state) {
        this.state = state;
    }

    @Override
    public Optional<State> process() {
        if (state.has(Node.Group.Declaration)) {
            var current = state.current();
            var identity = current.identity();
            var name = identity.name();
            var type = identity.type();
            if (type == ImplicitType.ImplicitType) {
                var message = Format.formatted(name);
                throw new IllegalStateException(message);
            }
        }
        return Optional.empty();
    }
}
