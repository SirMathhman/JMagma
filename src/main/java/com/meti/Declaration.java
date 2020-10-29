package com.meti;

public class Declaration implements Node {
    private static final String Format = "%s;";
    private final Field identity;

    public Declaration(Field identity) {
        this.identity = identity;
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Declaration;
    }

    @Override
    public String render() {
        String render = identity.render();
        return Format.formatted(render);
    }
}
