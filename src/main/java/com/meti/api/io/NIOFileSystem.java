package com.meti.api.io;

import java.nio.file.Paths;

public class NIOFileSystem implements FileSystem {
    private static final java.nio.file.Path Root = Paths.get(".");
    public static final FileSystem FileSystem_ = new NIOFileSystem();

    private NIOFileSystem() {
    }

    @Override
    public Path Root() {
        return new Path(Root);
    }
}