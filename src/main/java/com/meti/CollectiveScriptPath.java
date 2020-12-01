package com.meti;

import com.meti.api.collect.list.ArrayList;
import com.meti.api.collect.stream.ListStream;
import com.meti.api.collect.list.MutableList;

import java.util.Collection;

import static com.meti.api.collect.stream.ListStream.ListStreams.ofList;

public class CollectiveScriptPath<T> implements ScriptPath<T> {
	private final Collection<T> scriptPath;

	public CollectiveScriptPath(Collection<T> scriptPath) {
		this.scriptPath = scriptPath;
	}

	@Override
	public Collection<T> asCollection() {
		return scriptPath;
	}

	@Override
	public boolean load(T value) {
		return asCollection().add(value);
	}

	@Override
	public MutableList<T> asCollection2() {
		return ArrayList.ArrayList();
	}

	@Override
	public int count() {
		return asCollection().size();
	}

	@Override
	public ListStream<T> stream() {
		return ofList(asCollection2());
	}
}
