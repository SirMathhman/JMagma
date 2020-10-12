package com.meti.compile.render.block.function;

import com.meti.compile.render.block.Block;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.process.AbstractProcessor;
import com.meti.compile.render.process.State;

import java.util.Optional;

import static com.meti.compile.render.block.Block.Block;

public class FunctionFormatter extends AbstractProcessor {
    public FunctionFormatter(State state) {
        super(state);
    }

    @Override
    public Optional<State> process() {
        if(state.has(Node.Group.Function)) {
            var current = state.getValue();
            var oldValue = current.value(Node.class);
            var asReturn = wrapReturn(oldValue);
            var asBlock = wrapBlock(oldValue, asReturn);
            return Optional.of(state.with(current.withValue(asBlock)));
        }
        return Optional.empty();
    }

    private Node wrapBlock(Node oldValue, Node inReturn) {
        return oldValue.is(Node.Group.Block) ?
                inReturn : Block(inReturn);
    }

    private Node wrapReturn(Node oldValue) {
        return oldValue.is(Node.Group.Block) || oldValue.is(Node.Group.Return) ?
                oldValue : Return.Return(oldValue);
    }
}
