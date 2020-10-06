package com.meti.api.io;

import java.util.function.Function;

public interface File {
    <T> T read(Function<Input, T> action);

    interface Path {
        Path asAbsolute();
    }
}
