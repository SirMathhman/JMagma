package com.meti;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Structure implements Node {
    private final String name;
    private final List<Field> members;

    public Structure(String name, List<Field> members) {
        this.name = name;
        this.members = members;
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
}
