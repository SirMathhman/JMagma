package com.meti.exec.compile;

import com.meti.api.core.Comparable;

public interface Result<E> {
	Result<E> with(E group, String result);

	String apply(E group);

	class Group implements Comparable<Group> {
		static final Group Target = new Group(0);
		private final int order;

		public Group(int order) {
			this.order = order;
		}

		@Override
		public int compareTo(Group o) {
			return order;
		}
	}
}
