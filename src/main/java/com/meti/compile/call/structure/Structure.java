package com.meti.compile.call.structure;

import com.meti.compile.Node;
import com.meti.compile.scope.field.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Structure implements Node {
    private final String name;
    private final List<Field> members;

    public Structure(String name, List<Field> members) {
        this.name = name;
        this.members = members;
    }

    static None Structure() {
        return new None(Collections.emptyList());
    }


    @Override
    public String toString() {
        String membersString = members.stream()
                .map(Field::toString)
                .collect(Collectors.joining(",", "[", "]"));
        return ("{\n" +
                "    \"name\" : \"%s\",\n" +
                "    \"members\" : %s,\n" +
                "}\n").formatted(name, membersString);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Structure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Structure structure = (Structure) o;
        return Objects.equals(name, structure.name) &&
               Objects.equals(members, structure.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, members);
    }

    @Override
    public String render() {
        String renderedMembers = renderMembers();
        return "struct %s%s;".formatted(name, renderedMembers);
    }

    private String renderMembers() {
        return members.stream()
                .map(Field::render)
                .map("%s;"::formatted)
                .collect(Collectors.joining("", "{", "}"));
    }

    @Override
    public Node mapByMembers(Function<Field, Field> mapping) {
        return members.stream()
                .map(mapping)
                .reduce(Structure().withName(name), Builder::withField, (withName, withName2) -> withName2)
                .complete();

    }

    static class None extends Builder<None> {
        public None(List<Field> members) {
            super(members);
        }

        @Override
        None complete(List<Field> copy) {
            return new None(copy);
        }

        public WithName withName(String name) {
            return new WithName(members, name);
        }
    }

    static abstract class Builder<T> {
        protected final List<Field> members;

        Builder(List<Field> members) {
            this.members = members;
        }

        abstract T complete(List<Field> copy);

        T withField(Field field) {
            List<Field> newMembers = new ArrayList<>(members);
            newMembers.add(field);
            return complete(newMembers);
        }
    }

    static class WithName extends Builder<WithName> {
        private final String name;

        public WithName(List<Field> members, String name) {
            super(members);
            this.name = name;
        }

        @Override
        WithName complete(List<Field> copy) {
            return new WithName(copy, name);
        }

        public Node complete() {
            return new Structure(name, members);
        }
    }
}
