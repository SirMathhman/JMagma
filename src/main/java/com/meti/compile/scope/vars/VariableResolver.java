package com.meti.compile.scope.vars;

import com.meti.compile.Node;
import com.meti.compile.Type;
import com.meti.compile.resolve.Resolver;
import com.meti.compile.resolve.StateResolver;
import com.meti.compile.scope.field.Field;
import com.meti.compile.state.State;

import java.util.Optional;
import java.util.function.Function;

public class VariableResolver extends StateResolver {
    public VariableResolver(State state) {
        super(state);
    }

    @Override
    public Optional<Type> resolve(Function<State, Resolver<State>> parent){
        if(state.has(Node.Group.Variable)) {
            Type type = state.transformCurrent(this::resolveToField).type();
            return Optional.of(type);
        } else {
            return Optional.empty();
        }
    }

    private Field resolveToField(Node node) {
        return node.transformValue(String.class, this::resolveFromName);
    }

    private Field resolveFromName(String name) {
        return state.transformStack(stack -> stack.resolve(name));
    }
}
