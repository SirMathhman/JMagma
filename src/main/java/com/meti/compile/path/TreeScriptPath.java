package com.meti.compile.path;

import com.meti.compile.Node;
import com.meti.compile.content.ContentNode;
import com.meti.compile.extern.Import;
import com.meti.compile.tokenize.slice.BracketSplitter;

import java.util.List;
import java.util.Optional;

public class TreeScriptPath implements ScriptPath {
    private final Package root;

    private TreeScriptPath(Element... elements) {
        this(List.of(elements));
    }

    public TreeScriptPath(List<Element> elements) {
        this.root = new Package("", elements);
    }

    static Element Script(String name, String value) {
        return new Script(name, value);
    }

    static Element Package(String name, Element... elements) {
        return new Package(name, List.of(elements));
    }

    public static TreeScriptPath ScriptPath(Element... elements) {
        return new TreeScriptPath(elements);
    }

    @Override
    public Node read(List<String> package_, String name) {
        return root.read(package_, name)
                .map(BracketSplitter::new)
                .map(BracketSplitter::split)
                .orElseThrow(() -> invalidatePackage(package_, name))
                .map(ContentNode::new)
                .reduce(Import.Import(), Import.Builder::with, (builder0, builder1) -> builder1)
                .complete();
    }

    private IllegalArgumentException invalidatePackage(List<String> package_, String name) {
        String format = "%s.%s does not exist.";
        String packageString = String.join(".", package_);
        String message = format.formatted(packageString, name);
        return new IllegalArgumentException(message);
    }

    static abstract class Element {
        private final String name;

        protected Element(String name) {
            this.name = name;
        }

        boolean isNamed(String name) {
            return this.name.equals(name);
        }

        abstract Optional<String> read(List<String> package_, String name);
    }

    static class Package extends Element {
        private final List<Element> children;

        public Package(String name, List<Element> children) {
            super(name);
            this.children = children;
        }

        @Override
        Optional<String> read(List<String> package_, String name) {
            if (!package_.isEmpty()) {
                return children.stream()
                        .filter(element -> element.isNamed(package_.get(0)))
                        .map(element -> element.read(package_.subList(1, package_.size()), name))
                        .flatMap(Optional::stream)
                        .findFirst();
            } else {
                return children.stream()
                        .filter(child -> child.isNamed(name))
                        .map(child -> child.read(package_, name))
                        .flatMap(Optional::stream)
                        .findFirst();
            }
        }
    }

    static class Script extends Element {
        private final String value;

        public Script(String name, String value) {
            super(name);
            this.value = value;
        }

        @Override
        Optional<String> read(List<String> package_, String name) {
            return Optional.of(value);
        }
    }
}
