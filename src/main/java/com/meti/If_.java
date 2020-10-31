package com.meti;

public class If_ implements Node{
    private static final String Format = "if(%s)%s";
    private final Node condition;
    private final Node value;

    public If_(Node condition, Node value) {
        this.condition = condition;
        this.value = value;
    }

    @Override
    public boolean is(Group group) {
        return group == Group.If;
    }

    @Override
    public String render() {
        String renderCondition = condition.render();
        String renderValue = value.render();
        return Format.formatted(renderCondition, renderValue);
    }
}
