package com.meti.api.io;

import com.meti.api.core.Exception;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.function.Function;

public class File {
    private final Path path;

    public File(Path path) {
        this.path = path;
    }

    public <T> T read(Function<Input, T> action) {
        var pathString = path.toString();
        var path = formatJavaPath(pathString);

        try {
            var stream = Files.newInputStream(path);
            var input = new Input() {
                @Override
                public int read() {
                    try {
                        return stream.read();
                    } catch (IOException e) {
                        throw new Exception("Failed to read byte of: " + pathString, e);
                    }
                }
            };
            var result = action.apply(input);
            stream.close();
            return result;
        } catch (IOException e) {
            throw new Exception("Failed to create Input for: " + pathString, e);
        }
    }

    private java.nio.file.Path formatJavaPath(String pathString) {
        try {
            var uri = new URI(pathString);
            return java.nio.file.Path.of(uri);
        } catch (URISyntaxException e) {
            throw new Exception("Failed to create URI for: " + pathString, e);
        }
    }

    public interface Path {
        Path asAbsolute();
    }
}
