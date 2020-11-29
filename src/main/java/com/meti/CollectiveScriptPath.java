package com.meti;

import com.meti.api.collect.EmptyList;
import com.meti.api.collect.List;
import com.meti.api.collect.ListStream;

import java.util.Collection;

import static com.meti.api.collect.ListStream.ListStreams.ofList;

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
	public List<T> asCollection2(){
		return EmptyList.EmptyList();
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
