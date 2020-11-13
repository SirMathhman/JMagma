package com.meti.compile.call.function;

import com.meti.compile.Node;
import com.meti.compile.scope.field.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Abstraction implements Node {
    private final Field identity;
    private final List<Field> parameters;

    public Abstraction(Field identity, List<Field> parameters) {
        this.identity = identity;
        this.parameters = parameters;
    }

    static None Abstraction() {
        return new None(Collections.emptyList());
    }

    @Override
    public Node mapByMembers(Function<Field, Field> mapping) {
        OnlyIdentity onlyIdentity = Abstraction()
                .withIdentity(identity);
        OnlyIdentity builder = new OnlyIdentity(onlyIdentity.identity, onlyIdentity.parameters);
        return parameters.stream()
                .map(mapping)
                .reduce(builder, OnlyIdentity::withParameter, (both, both2) -> both2)
                .complete();
    }

    @Override
    public Node mapByIdentity(Function<Field, Field> mapping) {
        return new Abstraction(mapping.apply(identity), parameters);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abstraction that = (Abstraction) o;
        return Objects.equals(identity, that.identity) &&
               Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, null, parameters);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Abstraction;
    }

    @Override
    public String render() {
        String renderParameters = parameters.stream()
                .map(Field::render)
                .collect(Collectors.joining(",", "(", ")"));
        return identity.render(renderParameters) + ";";
    }

    static class OnlyIdentity {
        private final Field identity;
        private final List<Field> parameters;

        OnlyIdentity(Field identity, List<Field> parameters) {
            this.identity = identity;
            this.parameters = parameters;
        }

        public OnlyIdentity withParameter(Field parameter) {
            List<Field> newParameters = new ArrayList<>(parameters);
            newParameters.add(parameter);
            return new OnlyIdentity(identity, newParameters);
        }

        public Node complete() {
            return new Abstraction(identity, parameters);
        }
    }

    static class None {
        private final List<Field> parameters;

        None(List<Field> parameters) {
            this.parameters = parameters;
        }

        public None withParameter(Field parameter) {
            List<Field> newParameters = new ArrayList<>(parameters);
            newParameters.add(parameter);
            return new None(newParameters);
        }

        public OnlyIdentity withIdentity(Field identity) {
            return new OnlyIdentity(identity, parameters);
        }
    }
}
