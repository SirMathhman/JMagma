package com.meti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionType implements Type {
    private static final String Format = "(*%s)%s";
    private final Type returnType;
    private final List<Type> parameters;

    public FunctionType(Type returnType, List<Type> parameters) {
        this.returnType = returnType;
        this.parameters = parameters;
    }

    static None FunctionType() {
        return new None(Collections.emptyList());
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

    static class Complete extends FunctionTypeBuilder<Complete> {
        private final Type returnType;

        Complete(Type returnType, List<Type> parameters) {
            super(parameters);
            this.returnType = returnType;
        }

        @Override
        protected Complete complete(List<Type> newParameters) {
            return new Complete(returnType, newParameters);
        }

        public Type complete() {
            return new FunctionType(returnType, parameters);
        }
    }

    static class None extends FunctionTypeBuilder<None> {
        public None(List<Type> parameters) {
            super(parameters);
        }

        @Override
        protected None complete(List<Type> newParameters) {
            return new None(newParameters);
        }

        public Complete withReturnType(Type returnType) {
            return new Complete(returnType, parameters);
        }
    }

    public static abstract class FunctionTypeBuilder<T> {
        final List<Type> parameters;

        public FunctionTypeBuilder(List<Type> parameters) {
            this.parameters = parameters;
        }

        T withParameter(Type parameter) {
            List<Type> newParameters = new ArrayList<>(parameters);
            newParameters.add(parameter);
            return complete(newParameters);
        }

        protected abstract T complete(List<Type> newParameters);
    }
}
