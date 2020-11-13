package com.meti.compile.scope.declare;

import com.meti.compile.AbstractParser;
import com.meti.compile.Node;
import com.meti.compile.scope.field.Field;
import com.meti.compile.scope.initialize.Initialization;
import com.meti.compile.state.Stack;
import com.meti.compile.state.State;

import java.util.Optional;

public class DeclarationParser extends AbstractParser {
    public DeclarationParser(State previous) {
        super(previous);
    }

    @Override
    public Optional<State> process() {
        if (previous.has(Node.Group.Declaration)) {
            if (previous.transformBoth(this::isDefined)) {
                throw previous.transformCurrent(this::createAlreadyDefined);
            } else {
                return Optional.of(previous
                        .mapByStack(this::define)
                        .mapByCurrent(this::initialize));
            }
        }
        return Optional.empty();
    }

    private Node initialize(Node node) {
        return node.transformValue(Field.class, this::initialize);
    }

    private Node initialize(Field field) {
        return new Initialization(field, field.createDefault());
    }

    private Stack define(Node node, Stack stack) {
        return node.transformValue(Field.class, stack::define);
    }

    private IllegalStateException createAlreadyDefined(Node node) {
        return node.transformValue(Field.class, this::createAlreadyDefined);
    }

    private IllegalStateException createAlreadyDefined(Field field) {
        String formatted = field.formatName("'%s' is already defined.");
        return new IllegalStateException(formatted);
    }

    private Boolean isDefined(Node node, Stack stack) {
        return node.transformValue(Field.class, stack::isDefined);
    }
}
