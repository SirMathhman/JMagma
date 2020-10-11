package com.meti.compile.render.scope;

import com.meti.compile.render.node.Node;
import com.meti.compile.render.process.AbstractProcessor;
import com.meti.compile.render.process.Stack;
import com.meti.compile.render.process.State;

import java.util.Optional;

public class Definer extends AbstractProcessor {
    public Definer(State state) {
        super(state);
    }

    @Override
    public Optional<State> process() {
        if (state.has(Node.Group.Declaration) || state.has(Node.Group.Initialization)) {
            Stack define = define(state.scope(), state.getValue());
            return Optional.of(state.with(define));
        }
        return Optional.empty();
    }

    private Stack define(Stack scope, Node value) {
        var identity = value.identity();
        return scope.define(identity);
    }
}
