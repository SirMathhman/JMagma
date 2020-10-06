package com.meti.api.reference;

import java.util.function.Function;

public interface Option<T> {
    static <T> Some<T> Some(T value) {
        return new Some<>(value);
    }

    static <T> None<T> None() {
        return new None<>();
    }

    <R> R transformOrElse(Function<T, R> function, R other);

    class Some<T> implements Option<T> {
        private final T value;

        public Some(T value) {
            this.value = value;
        }

        @Override
        public <R> R transformOrElse(Function<T, R> function, R other) {
            return function.apply(value);
        }
    }

    class None<T> implements Option<T> {
        @Override
        public <R> R transformOrElse(Function<T, R> function, R other) {
            return other;
        }
    }
}
