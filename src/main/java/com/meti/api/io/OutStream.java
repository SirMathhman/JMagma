package com.meti.api.io;

import java.io.Closeable;
import java.io.IOException;

public interface OutStream extends Closeable {
    OutStream write(int value) throws IOException;

    OutStream flush() throws IOException;
}
