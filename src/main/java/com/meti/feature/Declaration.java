package com.meti.feature;

import java.util.Optional;
import java.util.function.Function;

class Declaration implements UntypedToken {
    private final Field identity;

    public Declaration(Field identity) {
        this.identity = identity;
    }

    @Override
    public Token mapByChildren(Function<Token, Token> mapping) {
        return this;
    }

    @Override
    public Token mapByFields(Function<Field, Field> mapping) {
        return new Declaration(mapping.apply(identity));
    }

    @Override
    public <R> Optional<R> transformContent(Function<String, R> mapping) {
        return Optional.empty();
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Declaration;
    }

    @Override
    public String render() {
        return String.format("%s;", identity.render());
    }
}
