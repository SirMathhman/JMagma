package com.meti.compile;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NIOFileSystem implements FileSystem {
    private static final Path Root = Paths.get(".");
    static final FileSystem FileSystem_ = new NIOFileSystem();

    private NIOFileSystem() {
    }

    @Override
    public Source Root() {
        return new Source(Root);
    }
}