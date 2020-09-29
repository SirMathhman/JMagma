package com.meti.feature;

import java.util.function.Function;

public class Declaration implements EmptyToken, UntypedToken {
    public static final String Format = "%s=%s;";
    private final Field identity;
    private final Token value;

    public Declaration(Field identity, Token value) {
        this.identity = identity;
        this.value = value;
    }

    @Override
    public Token mapByChildren(Function<Token, Token> mapping) {
        return new Declaration(identity, mapping.apply(value));
    }

    @Override
    public Token mapByFields(Function<Field, Field> mapping) {
        return new Declaration(mapping.apply(identity), value);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Declaration;
    }

    @Override
    public String render() {
        return String.format(Format, identity.render(), value.render());
    }
}
