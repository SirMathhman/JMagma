package com.meti;

public class Return implements Node {
    private final Node value;
    private static final String Format = "return %s;";

    public Return(Node value) {
        this.value = value;
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Return;
    }

    @Override
    public String render() {
        String render = value.render();
        return Format.formatted(render);
    }
}
