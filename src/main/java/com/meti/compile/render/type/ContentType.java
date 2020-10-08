package com.meti.compile.render.type;

import com.meti.compile.render.node.UnrenderableException;

import java.util.function.Function;

public class ContentType implements LeafType {
    public static final String Format = """
            Cannot render content with type '%s' and name '%s'. 
            Not that this class can be rendered, 
            but rather this instance should have been parsed/removed in compilation 
            and a programming error has occurred.""";
    private final String content;

    public ContentType(String content) {
        this.content = content;
    }

    @Override
    public <T> T transformContent(Function<String, T> transformer) {
        return transformer.apply(content);
    }

    @Override
    public String render(String name) {
        throw new UnrenderableException(Format.formatted(content, name));
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Content;
    }
}
