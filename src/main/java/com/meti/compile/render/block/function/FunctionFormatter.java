package com.meti.compile.render.block.function;

import com.meti.compile.render.block.Block;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.process.AbstractProcessor;
import com.meti.compile.render.process.State;

import java.util.Optional;

public class FunctionFormatter extends AbstractProcessor {
    public FunctionFormatter(State state) {
        super(state);
    }

    @Override
    public Optional<State> process() {
        if(state.has(Node.Group.Function)) {
            var current = state.value();
            var oldValue = current.value(Node.class);
            var asReturn = wrapReturn(oldValue);
            var asBlock = wrapBlock(oldValue, asReturn);
            return Optional.of(state.with(current.withValue(asBlock)));
        }
        return Optional.empty();
    }

    private Node wrapBlock(Node oldValue, Node inReturn) {
        Node newValue;
        if(!oldValue.is(Node.Group.Block)) {
            newValue = new Block(inReturn);
        } else {
            newValue = inReturn;
        }
        return newValue;
    }

    private Node wrapReturn(Node oldValue) {
        Node inReturn;
        if(!oldValue.is(Node.Group.Block) && !oldValue.is(Node.Group.Return)) {
            inReturn = new Return(oldValue);
        } else {
            inReturn = oldValue;
        }
        return inReturn;
    }
}
