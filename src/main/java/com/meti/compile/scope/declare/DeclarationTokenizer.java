package com.meti.compile.scope.declare;

import com.meti.compile.scope.field.Field;
import com.meti.compile.scope.field.FieldTokenizer;
import com.meti.compile.Node;
import com.meti.compile.tokenize.AbstractTokenizer;

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
