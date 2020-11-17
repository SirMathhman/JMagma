package com.meti.api.stream;

import com.meti.api.collect.List;
import com.meti.api.core.Some;
import org.junit.jupiter.api.Test;

import static com.meti.api.collect.ArrayList.ArrayList;
import static com.meti.api.stream.ListStream.ListStream;
import static org.junit.jupiter.api.Assertions.*;

class ListStreamTest {

	@Test
	void flatMapOptionally() throws StreamException {
		assertArrayEquals(new Object[]{0, 1, 2},
				ListStream(ArrayList(0, 1, 2))
						.flatMapOptionally(Some::Some)
						.foldLeft(ArrayList(), List::add)
						.toArray());
	}

	@Test
	void filter() throws StreamException {
		assertArrayEquals(new Object[]{"0", "2"},
				ListStream(ArrayList(0, 1, 2))
						.filter(value -> value % 2 == 0)
						.map(String::valueOf)
						.foldLeft(ArrayList(), List::add)
						.toArray());
	}

	@Test
	void map() throws StreamException {
		assertArrayEquals(new Object[]{"test0", "test1", "test2"},
				ListStream(ArrayList("0", "1", "2"))
						.map(value -> "test" + value)
						.foldLeft(ArrayList(), List::add)
						.toArray());
	}

	@Test
	void foldLeft() throws StreamException {
		assertArrayEquals(new Object[]{"test0", "test1", "test2"},
				ListStream(ArrayList("test0", "test1", "test2"))
						.foldLeft(ArrayList(), List::add)
						.toArray());
	}
}