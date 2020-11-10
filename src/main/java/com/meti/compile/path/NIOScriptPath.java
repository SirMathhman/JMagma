package com.meti.compile.path;

import com.meti.api.io.Directory;
import com.meti.api.io.Extant;
import com.meti.compile.ExceptionNode;
import com.meti.compile.Node;
import com.meti.compile.content.ContentNode;
import com.meti.compile.extern.Import.Builder;
import com.meti.compile.tokenize.slice.BracketSplitter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static com.meti.compile.extern.Import.Import;

public class NIOScriptPath implements ScriptPath {
    public static final String Format = "%s.mg";
    private final Directory directory;

    public NIOScriptPath(Directory directory) {
        this.directory = directory;
    }

    @Override
    public Node read(List<String> package_, String name) {
        Extant path = package_.isEmpty() ?
                formatFile(name) :
                formatPackage(package_, name);
        try {
            return complete(path);
        } catch (IOException e) {
            String qualified = formatFullName(package_, name);
            String format = "Failed to import '%s' from '%s'.";
            String message = format.formatted(qualified, path);
            return new ExceptionNode(message, e);
        }
    }

    private String formatFullName(Iterable<String> package_, String name) {
        String format = "%s.%s";
        String join = String.join(".", package_);
        return format.formatted(join, name);
    }

    private Extant formatPackage(Collection<String> package_, String name) {
        return package_.stream()
                .reduce(directory, Directory::resolveDirectory, (directory, directory2) -> directory2)
                .resolveFile(Format.formatted(name));
    }

    private Extant formatFile(String name) {
        return directory.resolveFile(name + ".mg");
    }

    private Node complete(Extant path) throws IOException {
        return new BracketSplitter(path.readAsString())
                .split()
                .map(ContentNode::new)
                .reduce(Import(), Builder::with, (builder, builder2) -> builder2)
                .complete();
    }
}