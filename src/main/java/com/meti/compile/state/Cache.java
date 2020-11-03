package com.meti.compile.state;

import com.meti.compile.Node;

import java.util.*;
import java.util.stream.Collectors;

public class Cache {
    private final Map<Group, List<Node>> internalMap;

    public Cache() {
        this(Collections.emptyMap());
    }

    public Cache(Map<Group, List<Node>> internalMap) {
        this.internalMap = internalMap;
    }

    public Cache with(Group group, Node node) {
        var copy = new HashMap<>(internalMap);
        if (!copy.containsKey(group)) {
            copy.put(group, Collections.emptyList());
        }
        copy.get(group).add(node);
        return new Cache(copy);
    }

    public String render(Group group) {
        if (internalMap.containsKey(group)) {
            return internalMap.get(group)
                    .stream()
                    .map(Node::render)
                    .collect(Collectors.joining());
        } else {
            return "";
        }
    }

    enum Group {
        Include,
        Structure,
        Header,
        Function,
    }
}
