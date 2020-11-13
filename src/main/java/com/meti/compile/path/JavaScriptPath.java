package com.meti.compile.path;

import com.meti.compile.ExceptionNode;
import com.meti.compile.Node;
import com.meti.compile.content.ContentNode;
import com.meti.compile.extern.Import.Builder;
import com.meti.compile.tokenize.slice.BracketSplitter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import static com.meti.compile.extern.Import.Import;

public class JavaScriptPath implements ScriptPath {
    private final Path sources;

    public JavaScriptPath(Path sources) {
        this.sources = sources;
    }

    @Override
    public Node read(List<String> package_, String name) {
        Path path = package_.isEmpty() ?
                formatFile(name) :
                formatPackage(package_, name);
        try {
            return complete(path);
        } catch (IOException e) {
            Path asAbsolute = path.toAbsolutePath();
            String qualified = formatFullName(package_, name);
            String format = "Failed to import '%s' from '%s'.";
            String message = format.formatted(qualified, asAbsolute);
            return new ExceptionNode(message, e);
        }
    }

    private String formatFullName(Iterable<String> package_, String name) {
        String format = "%s.%s";
        String join = String.join(".", package_);
        return format.formatted(join, name);
    }

    private Path formatPackage(Collection<String> package_, String name) {
        return package_.stream()
                .map(Paths::get)
                .reduce(sources, Path::resolve)
                .resolve(name + ".mg");
    }

    private Path formatFile(String name) {
        return sources.resolve(name + ".mg");
    }

    private Node complete(Path path) throws IOException {
        return new BracketSplitter(Files.readString(path))
                .split()
                .map(ContentNode::new)
                .reduce(Import(), Builder::with, (builder, builder2) -> builder2)
                .complete();
    }
}