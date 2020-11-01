package com.meti.declare;

import com.meti.field.Field;
import com.meti.field.FieldTokenizer;
import com.meti.Node;
import com.meti.AbstractTokenizer;

import java.util.Optional;

public class DeclarationTokenizer extends AbstractTokenizer<Node> {
    public DeclarationTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        return new FieldTokenizer(content)
                .tokenize()
                .filter(value -> value.is(Field.Flag.CONST) || value.is(Field.Flag.LET))
                .map(Declaration::new);
    }
}
