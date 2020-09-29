package com.meti.render.feature.scope;

import com.meti.render.evaluate.token.Token;
import com.meti.render.evaluate.token.UntypedToken;
import com.meti.render.evaluate.field.Field;
import com.meti.render.evaluate.token.EmptyToken;

import java.util.function.Function;

public class Initialization implements EmptyToken, UntypedToken {
    public static final String Format = "%s=%s;";
    private final Field identity;
    private final Token value;

    public Initialization(Field identity, Token value) {
        this.identity = identity;
        this.value = value;
    }

    @Override
    public Token mapByChildren(Function<Token, Token> mapping) {
        return new Initialization(identity, mapping.apply(value));
    }

    @Override
    public Token mapByFields(Function<Field, Field> mapping) {
        return new Initialization(mapping.apply(identity), value);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Initialization;
    }

    @Override
    public String render() {
        return String.format(Format, identity.render(), value.render());
    }
}
