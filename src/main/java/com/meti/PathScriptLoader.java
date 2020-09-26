package com.meti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//TODO: write integration test for this class
public class PathScriptLoader implements ScriptLoader {
    private final Path source;

    public PathScriptLoader(Path source) {
        this.source = source;
    }

    @Override
    public String load(List<String> mainPackage) throws IOException {
        Path withoutExtension = findPath(mainPackage);
        Path withExtension = formatExtension(withoutExtension);
        if (!Files.exists(withExtension)) {
            String format = "No script with package '%s' was found at: %s";
            String packageAsString = String.join(".", mainPackage);
            String message = String.format(format, packageAsString, withExtension.toAbsolutePath());
            throw new NoSuchFileException(message);
        } else {
            return Files.readString(withExtension);
        }
    }

    /*
    We need to reformat the path such that it ends with '.magma',
    otherwise it won't be picked up and will not be found, i.e.:

    ./com/meti/Main -> ./com/meti/Main.magma
     */
    private Path formatExtension(Path mainPath) {
        Path name = mainPath.getName(mainPath.getNameCount() - 1);
        return mainPath.resolveSibling(name + ".magma");
    }

    private Path findPath(List<String> packageList) {
        return packageList.stream()
                .filter(s -> !s.isBlank())
                .map(String::trim)
                .map(Paths::get)
                .reduce(source, Path::resolve);
    }
}