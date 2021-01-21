package com.meti.api.magma.collect;

import java.util.ArrayList;

public class Lists {
	public Lists() {
	}

	public static java.util.List<String> fromJava(List<String> names) {
		var list = new ArrayList<String>();
		for (int i = 0; i < names.size(); i++) {
			try {
				list.add(names.apply(i));
			} catch (IndexException e) {
				throw new UnsupportedOperationException();
			}
		}
		return list;
	}
}
