package com.meti.exec.compile;

import com.meti.api.collect.map.Map;
import com.meti.api.core.Option;
import com.meti.api.extern.Function0;

import static com.meti.api.collect.map.ListMap.ListMap;
import static com.meti.exec.compile.CompileException.CompileException;

public class MapDestination<G> implements Destination<G> {
	private final Map<Package, Map<Group, String>> internalMap;

	public MapDestination() {
		this(ListMap(Package::compareTo));
	}

	public MapDestination(Map<Package, Map<Group, String>> internalMap) {
		this.internalMap = internalMap;
	}

	@Override
	public Destination<G> put(Package p, Group group, String value) throws CompileException {
		Function0<Map<Group, String>> mapFunction0 = () -> ListMap(Group::compareTo);
		var map = internalMap.ensure(p, mapFunction0)
				.get(p)
				.orElseGet(mapFunction0);
		if (map.containsKey(group)) {
			throw CompileException("Value for package " + p + " and group " + group + " has already been set.");
		} else {
			var withValue = map.put(group, value);
			var withGroup = internalMap.put(p, withValue);
			return new MapDestination<>(withGroup);
		}
	}

	@Override
	public Option<String> apply(Package p, Group group) {
		return internalMap.get(p).flatMap(map -> map.get(group));
	}
}
