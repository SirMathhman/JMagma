package com.meti.function;

import com.meti.Node;
import com.meti.field.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Implementation implements Node {
    private final Field identity;
    private final Node value;
    private final List<Field> parameters;

    public Implementation(Field identity, Node value, List<Field> parameters) {
        this.identity = identity;
        this.value = value;
        this.parameters = parameters;
    }

    static None Implementation() {
        return new None(Collections.emptyList());
    }

    @Override
    public Node mapByMembers(Function<Field, Field> mapping) {
        Both builder = Implementation()
                .withIdentity(identity)
                .withValue(value);
        return parameters.stream()
                .map(mapping)
                .reduce(builder, Both::withParameter, (both, both2) -> both2)
                .complete();
    }

    @Override
    public Node mapByIdentity(Function<Field, Field> mapping) {
        return new Implementation(mapping.apply(identity), value, parameters);
    }

    @Override
    public Node mapByChild(Function<Node, Node> mapping) {
        return new Implementation(identity, mapping.apply(value), parameters);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Implementation that = (Implementation) o;
        return Objects.equals(identity, that.identity) &&
               Objects.equals(value, that.value) &&
               Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, value, parameters);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Implementation;
    }

    @Override
    public String render() {
        String renderParameters = parameters.stream()
                .map(Field::render)
                .collect(Collectors.joining(",", "(", ")"));
        return identity.render(renderParameters) + value.render();
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

        public Both withValue(Node value) {
            return new Both(identity, value, parameters);
        }
    }

    static class OnlyValue {
        private final Node value;
        private final List<Field> parameters;

        OnlyValue(Node value, List<Field> parameters) {
            this.value = value;
            this.parameters = parameters;
        }

        public OnlyValue withParameter(Field parameter) {
            List<Field> newParameters = new ArrayList<>(parameters);
            newParameters.add(parameter);
            return new OnlyValue(value, newParameters);
        }

        public Both withIdentity(Field identity) {
            return new Both(identity, value, parameters);
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

        public OnlyValue withValue(Node value) {
            return new OnlyValue(value, parameters);
        }
    }

    static class Both {
        private final Field identity;
        private final Node value;
        private final List<Field> parameters;

        Both(Field identity, Node value, List<Field> parameters) {
            this.identity = identity;
            this.value = value;
            this.parameters = parameters;
        }

        public Both withParameter(Field parameter) {
            List<Field> newParameters = new ArrayList<>(parameters);
            newParameters.add(parameter);
            return new Both(identity, value, newParameters);
        }

        public Node complete() {
            return new Implementation(identity, value, parameters);
        }
    }
}
