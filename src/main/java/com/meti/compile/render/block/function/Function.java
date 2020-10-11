package com.meti.compile.render.block.function;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.field.InlineField;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.type.Type;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Function implements Node {
    private final String name;
    private final List<Field> parameters;
    private final Type returnType;
    private final Node value;

    private Function(String name, Type returnType, Node value, List<Field> parameters) {
        this.name = name;
        this.parameters = Collections.unmodifiableList(parameters);
        this.returnType = returnType;
        this.value = value;
    }

    public static Function Function(String name, Type returnType, Node value, Field... parameters) {
        return Function(name, returnType, value, List.of(parameters));
    }

    public static Function Function(String name, Type returnType, Node value, List<Field> parameters) {
        return new Function(name, returnType, value, parameters);
    }

    @Override
    public String toString() {
        return "Function{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                ", returnType=" + returnType +
                ", value=" + value +
                '}';
    }

    @Override
    public Stream<? extends Node> streamChildren() {
        return Stream.of(value);
    }

    @Override
    public Node mapByIdentity(java.util.function.Function<Field, Field> mapper) {
        return withIdentity(mapper.apply(identity()));
    }

    @Override
    public Node mapByChildren(java.util.function.Function<Node, Node> mapper) {
        return Function(name, returnType, mapper.apply(value), parameters);
    }

    @Override
    public Node mapByFields(java.util.function.Function<Field, Field> mapper) {
        return Function(name, returnType, value, parameters.stream().map(mapper).collect(Collectors.toList()));
    }

    @Override
    public Stream<Field> streamFields() {
        return parameters.stream();
    }

    @Override
    public <T extends Container<T>> T reduce(T identity, java.util.function.Function<T, T> operator) {
        var newContainer = operator.apply(identity.with(value));
        var copy = new Function(name, returnType, newContainer.getValue(), parameters);
        return newContainer.with(copy);
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
        return Function(newName, newReturnType, value, parameters);
    }

    @Override
    public Node withValue(Object value) {
        return Function(name, returnType, (Node) value, parameters);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function function = (Function) o;
        return Objects.equals(name, function.name) &&
                Objects.equals(parameters, function.parameters) &&
                Objects.equals(returnType, function.returnType) &&
                Objects.equals(value, function.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parameters, returnType, value);
    }
}
