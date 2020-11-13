package com.meti.api.io;

import java.io.IOException;

public interface Extant extends File<Extant> {
    String readAsString() throws IOException;

    Extinct delete() throws IOException;

    void write(CharSequence output) throws IOException;

    @Override
    default Extant ensure() {
        return this;
    }
}
