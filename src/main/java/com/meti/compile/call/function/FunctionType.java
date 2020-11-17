package com.meti.compile.call.function;

import com.meti.compile.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionType implements Type {
    private static final String Format = "(*%s)%s";
    private final Type returnType;
    private final List<Type> parameters;

    public FunctionType(Type returnType, List<Type> parameters) {
        this.returnType = returnType;
        this.parameters = parameters;
    }

    public static None FunctionType() {
        return new None(Collections.emptyList());
    }

    @Override
    public Type mapByChild(Function<Type, Type> mapping) {
        Type newReturnType = mapping.apply(returnType);
        Prototype identity = FunctionType().withReturn(newReturnType);
        return parameters.stream()
                .map(mapping)
                .reduce(identity, FunctionTypeBuilder::withParameter, (prototype, prototype2) -> prototype2)
                .complete();
    }

    @Override
    public String render(String name) {
        String renderParameters = renderParameters();
        String message = Format.formatted(name, renderParameters);
        return returnType.render(message);
    }

    @Override
    public Type secondary() {
        return returnType;
    }

    private String renderParameters() {
        return parameters.stream()
                .map(Type::render)
                .collect(Collectors.joining(",", "(", ")"));
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Function;
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

    public static class Prototype extends FunctionTypeBuilder<Prototype> {
        private final Type returnType;

        Prototype(Type returnType, List<Type> parameters) {
            super(parameters);
            this.returnType = returnType;
        }

        @Override
        protected Prototype complete(List<Type> newParameters) {
            return new Prototype(returnType, newParameters);
        }

        public Type complete() {
            return new FunctionType(returnType, parameters);
        }
    }

    public static class None extends FunctionTypeBuilder<None> {
        public None(List<Type> parameters) {
            super(parameters);
        }

        @Override
        protected None complete(List<Type> newParameters) {
            return new None(newParameters);
        }

        public Prototype withReturn(Type returnType) {
            return new Prototype(returnType, parameters);
        }
    }

    public static abstract class FunctionTypeBuilder<T> {
        final List<Type> parameters;

        public FunctionTypeBuilder(List<Type> parameters) {
            this.parameters = parameters;
        }

        public T withParameter(Type parameter) {
            List<Type> newParameters = new ArrayList<>(parameters);
            newParameters.add(parameter);
            return complete(newParameters);
        }

        protected abstract T complete(List<Type> newParameters);
    }
}
