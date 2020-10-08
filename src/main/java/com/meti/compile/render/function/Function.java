package com.meti.compile.render.function;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.field.InlineField;
import com.meti.compile.render.node.EmptyNode;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.type.Type;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Function implements EmptyNode {
    private final String name;
    private final List<Field> parameters;
    private final Type returnType;
    private final Node value;

    public Function(String name, List<Field> parameters, Type returnType, Node value) {
        this.name = name;
        this.parameters = Collections.unmodifiableList(parameters);
        this.returnType = returnType;
        this.value = value;
    }

    @Override
    public Node mapByIdentity(java.util.function.Function<Field, Field> mapper) {
        return withIdentity(mapper.apply(identity()));
    }

    @Override
    public Node mapByChildren(java.util.function.Function<Node, Node> mapper) {
        return new Function(name, parameters, returnType, mapper.apply(value));
    }

    @Override
    public Node mapByFields(java.util.function.Function<Field, Field> mapper) {
        return new Function(name, parameters.stream().map(mapper).collect(Collectors.toList()), returnType, value);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Function;
    }

    @Override
    public Field identity() {
        var parametersAsType = parameters
                .stream()
                .map(Field::type)
                .collect(Collectors.toList());
        var type = new FunctionType(returnType, parametersAsType);
        return new InlineField(name, type);
    }

    @Override
    public <T> T value(Class<T> clazz) {
        return clazz.cast(value);
    }

    @Override
    public Node withIdentity(Field identity) {
        var newName = identity.name();
        var newType = identity.type();
        var newReturnType = newType.head();
        return new Function(newName, parameters, newReturnType, value);
    }

    @Override
    public String render() {
        var renderedParameters = renderParameters();
        return returnType.render(name + renderedParameters) + value.render();
    }

    private String renderParameters() {
        return parameters.stream()
                .map(Field::render)
                .collect(Collectors.joining(",", "(", ")"));
    }
}
