package com.meti.api.io;

import java.io.IOException;

public interface Path {
    Path resolveName(String name);

    Path resolvePath(Path other);

    boolean exists();

    void createAsFile() throws IOException;
}
