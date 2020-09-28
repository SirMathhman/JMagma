package com.meti;

import com.meti.feature.Int;
import com.meti.feature.Token;
import com.meti.feature.Variable;

import java.math.BigInteger;
import java.util.Optional;

public class Compiler {
    String compileToString(String value) {
        return tokenize(value).render();
    }

    private Optional<Token> tokenizeInteger(String value){
        try{
            BigInteger integer = new BigInteger(value);
            return Optional.of(new Int(integer));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private Token tokenize(String value) {
        Optional<Token> optional = tokenizeInteger(value);
        return optional.orElseGet(() -> new Variable(value));
    }
}