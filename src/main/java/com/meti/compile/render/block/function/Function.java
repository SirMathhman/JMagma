package com.meti.compile.render.block.function;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.field.InlineField;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.type.Type;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Function implements Node {
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
    public Stream<Field> streamFields() {
        return parameters.stream();
    }

    @Override
    public <T extends Container<T>> T reduce(T identity, java.util.function.Function<T, T> operator) {
        return operator.apply(identity.with(value));
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
        var type = FunctionType.FunctionType(returnType, parametersAsType);
        return InlineField.Field(name, type);
    }

    @Override
    public <T> T value(Class<T> clazz) {
        return clazz.cast(value);
    }

    @Override
    public Node withIdentity(Field identity) {
        var newName = identity.name();
        var newType = identity.type();
        var newReturnType = newType.secondary();
        return new Function(newName, parameters, newReturnType, value);
    }

    @Override
    public Node withValue(Object value) {
        return new Function(name, parameters, returnType, (Node) value);
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
