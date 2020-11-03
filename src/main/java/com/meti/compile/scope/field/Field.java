package com.meti.compile.scope.field;

import com.meti.compile.Renderable;
import com.meti.compile.Type;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public interface Field extends Renderable {
    static None Field() {
        return new None(Collections.emptySet());
    }

    Field mapByType(Function<Type, Type> mapping);

    boolean is(Flag flag);

    String render(String parameters);

    Type type();

    boolean isNamed(String name);

    enum Flag {
        NATIVE,
        DEF,
        IN,
        OUT,
        CONST,
        LET,
    }

    class FieldImpl implements Field {
        private final String name;
        private final Type type;
        private final Set<Flag> flags;

        public FieldImpl(Set<Flag> flags, String name, Type type) {
            this.name = name;
            this.type = type;
            this.flags = flags;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FieldImpl field = (FieldImpl) o;
            return Objects.equals(name, field.name) &&
                   Objects.equals(type, field.type) &&
                   Objects.equals(flags, field.flags);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, type, flags);
        }

        @Override
        public String render() {
            return type.render(name);
        }

        @Override
        public Field mapByType(Function<Type, Type> mapping) {
            return new FieldImpl(flags, name, mapping.apply(type));
        }

        @Override
        public boolean is(Flag flag) {
            return flags.contains(flag);
        }

        @Override
        public String render(String parameters) {
            return type.secondary().render(name + parameters);
        }

        @Override
        public Type type() {
            return type;
        }

        @Override
        public boolean isNamed(String name) {
            return this.name.equals(name);
        }
    }

    abstract class Builder<T> {
        protected final Set<Flag> flags;

        public Builder(Set<Flag> flags) {
            this.flags = flags;
        }

        public T withFlag(Flag flag) {
            Set<Flag> newFlags = new HashSet<>(flags);
            newFlags.add(flag);
            return copy(newFlags);
        }

        protected abstract T copy(Set<Flag> flags);
    }

    class None extends Builder<None> {
        public None(Set<Flag> flags) {
            super(flags);
        }

        public WithName withName(String name) {
            return new WithName(flags, name);
        }

        public WithType withType(Type type) {
            return new WithType(flags, type);
        }

        @Override
        protected None copy(Set<Flag> flags) {
            return new None(flags);
        }
    }

    class WithType extends Builder<WithType> {
        private final Type type;

        public WithType(Set<Flag> flags, Type type) {
            super(flags);
            this.type = type;
        }

        @Override
        protected WithType copy(Set<Flag> flags) {
            return new WithType(flags, type);
        }

        public Both withName(String name) {
            return new Both(flags, name, type);
        }
    }

    class WithName extends Builder<WithName> {
        private final String name;

        public WithName(Set<Flag> flags, String name) {
            super(flags);
            this.name = name;
        }

        public Both withType(Type type) {
            return new Both(flags, name, type);
        }

        @Override
        protected WithName copy(Set<Flag> flags) {
            return new WithName(flags, name);
        }
    }

    class Both extends Builder<Both> {
        private final String name;
        private final Type type;

        public Both(Set<Flag> flags, String name, Type type) {
            super(flags);
            this.name = name;
            this.type = type;
        }

        @Override
        protected Both copy(Set<Flag> flags) {
            return new Both(flags, name, type);
        }

        public Field complete() {
            return new FieldImpl(flags, name, type);
        }
    }
}
