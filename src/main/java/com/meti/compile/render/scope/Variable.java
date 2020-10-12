package com.meti.compile.render.scope;

import com.meti.compile.render.node.LeafNode;
import com.meti.compile.render.node.Node;

public class Variable implements LeafNode, Node {
    public static final Node this_ = Variable("this");
    private final String content;

    private Variable(String content) {
        this.content = content;
    }

    public static Variable Variable(String content) {
        return new Variable(content);
    }

    @Override
    public String render() {
        return content;
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Variable;
    }

    @Override
    public <T> T value(Class<T> clazz) {
        return clazz.cast(content);
    }

    @Override
    public Node withValue(Object value) {
        return Variable(value.toString());
    }
}
