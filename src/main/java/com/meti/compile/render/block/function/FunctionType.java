package com.meti.compile.render.block.function;

import com.meti.compile.render.primitive.EmptyType;
import com.meti.compile.render.type.Type;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionType implements EmptyType {
    private final Type returnType;
    private final List<Type> parameters;

    public FunctionType(Type returnType, List<Type> parameters) {
        this.returnType = returnType;
        this.parameters = parameters;
    }

    @Override
    public Type mapByChildren(Function<Type, Type> mapper) {
        var newReturnType = mapReturnType(mapper);
        var newParameters = mapParameters(mapper);
        return new FunctionType(newReturnType, newParameters);
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
    public Type start() {
        return returnType;
    }

    @Override
    public Type mapByStart(Function<Type, Type> mapper) {
        return new FunctionType(mapper.apply(returnType), parameters);
    }
}
