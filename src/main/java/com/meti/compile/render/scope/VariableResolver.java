package com.meti.compile.render.scope;

import com.meti.compile.render.node.Node;
import com.meti.compile.render.process.State;
import com.meti.compile.render.resolve.AbstractResolver;
import com.meti.compile.render.type.Type;

import java.util.Optional;

public class VariableResolver extends AbstractResolver {
    public VariableResolver(State state) {
        super(state);
    }

    @Override
    public Optional<Type> resolve() {
        if (state.has(Node.Group.Variable)) {
            var name = state.getValue().value(String.class);
            var scope = state.scope();
            var definition = scope.getDefinition(name);
            return Optional.of(definition.type());
        }
        return Optional.empty();
    }
}
