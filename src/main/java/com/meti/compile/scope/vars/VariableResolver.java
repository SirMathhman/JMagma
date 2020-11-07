package com.meti.compile.scope.vars;

import com.meti.compile.Node;
import com.meti.compile.Type;
import com.meti.compile.scope.field.Field;
import com.meti.compile.state.State;

import java.util.Optional;

public class VariableResolver {
    private final State state;

    public VariableResolver(State state) {
        this.state = state;
    }

    public Optional<Type> resolve(){
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
