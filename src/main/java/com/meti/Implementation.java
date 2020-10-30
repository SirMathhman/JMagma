package com.meti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
