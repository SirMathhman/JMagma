package com.meti.tokenize.slice;

import java.util.stream.Stream;

public interface Splitter {
    Stream<String> split();
}
