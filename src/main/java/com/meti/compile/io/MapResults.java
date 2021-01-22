package com.meti.compile.io;

import com.meti.api.java.collect.JavaMap;
import com.meti.api.magma.collect.List;
import com.meti.api.magma.core.Option;

public class MapResults {
	public static Builder MapResult() {
		return new Builder(new JavaMap());
	}

	public static Impl of(JavaMap map) {
		return new Impl(map);
	}

	public static record Builder(JavaMap map) {
		public Result complete() {
			return new Impl(map);
		}

		public Builder with(Source source, Result.Mapping mapping) {
			return new Builder(map.put(source, mapping));
		}
	}

	private static final record Impl(JavaMap map) implements Result {
		@Override
		public Option<Mapping> apply(Source source) {
			return map.apply(source);
		}

		@Override
		public int count() {
			return map.size();
		}

		@Override
		public List<Source> listSources() {
			return map.listKeys();
		}
	}
}
