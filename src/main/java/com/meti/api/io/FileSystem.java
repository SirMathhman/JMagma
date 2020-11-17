package com.meti.api.io;

import java.io.IOException;

public interface FileSystem {
    @Deprecated
    Path RootPath();

    Directory Root() throws IOException;
}
