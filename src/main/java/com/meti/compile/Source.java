package com.meti.compile;

import java.nio.file.Path;

public class Source {
    private final Path root;

    public Source(Path root) {
        this.root = root;
    }

    Source resolve(String name) {
        return new Source(root.resolve(name));
    }

    public Path getRoot() {
        return root;
    }
}
