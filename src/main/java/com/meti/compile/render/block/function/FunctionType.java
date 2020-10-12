package com.meti.compile.render.block.function;

import com.meti.compile.render.primitive.EmptyType;
import com.meti.compile.render.type.Type;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionType implements EmptyType {
    private final Type returnType;
    private final List<Type> parameters;

    private FunctionType(Type returnType, List<Type> parameters) {
        this.returnType = returnType;
        this.parameters = parameters;
    }

    public static FunctionType FunctionType(Type returnType, Type... parameters) {
        return FunctionType(returnType, List.of(parameters));
    }

    public static FunctionType FunctionType(Type returnType, List<Type> parameters) {
        return new FunctionType(returnType, parameters);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FunctionType that = (FunctionType) o;
        return Objects.equals(returnType, that.returnType) &&
                Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(returnType, parameters);
    }

    @Override
    public Type withSecondary(Type secondary) {
        return new FunctionType(secondary, parameters);
    }

    @Override
    public Type mapByChildren(Function<Type, Type> mapper) {
        var newReturnType = mapReturnType(mapper);
        var newParameters = mapParameters(mapper);
        return FunctionType(newReturnType, newParameters);
    }

    private Type mapReturnType(Function<Type, Type> mapper) {
        return mapper.apply(returnType);
    }

    private List<Type> mapParameters(Function<Type, Type> mapper) {
        return parameters.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    @Override
    public String render(String name) {
        var parameterString = parameters.stream()
                .map(Type::render)
                .collect(Collectors.joining(",", "(", ")"));
        return returnType.render(name + parameterString);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Function;
    }

    @Override
    public Type secondary() {
        return returnType;
    }

}
