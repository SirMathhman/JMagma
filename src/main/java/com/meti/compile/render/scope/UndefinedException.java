package com.meti.compile.render.scope;

import com.meti.compile.render.CompileException;

public class UndefinedException extends CompileException {
    public UndefinedException(String message) {
        super(message);
    }
}
