package com.meti.compile.render.block.structure;

import com.meti.compile.render.process.State;
import com.meti.compile.render.resolve.AbstractResolver;
import com.meti.compile.render.type.Type;

import java.util.Optional;

public class ThisResolver extends AbstractResolver {
    public ThisResolver(State state) {
        super(state);
    }

    @Override
    public Optional<Type> resolve() {
        return Optional.empty();
    }
}
