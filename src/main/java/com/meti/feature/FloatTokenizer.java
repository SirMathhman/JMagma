package com.meti.feature;

import java.util.Optional;

public class FloatTokenizer implements Tokenizer {
    @Override
    public Optional<Tokenizable> create(String content) {
        try {
            float value = Float.parseFloat(content);
            return Optional.of(() -> new Float_(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private static class Float_ implements Token {
        private final float value;

        public Float_(float value) {
            this.value = value;
        }

        @Override
        public String render() {
            return String.valueOf(value);
        }
    }
}
