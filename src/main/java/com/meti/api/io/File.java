package com.meti.api.io;

import java.io.IOException;

public interface File<E> {
    E ensure() throws IOException;
}
