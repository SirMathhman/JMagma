package com.meti;

public interface CCache extends Cache<CCache.Group> {
	enum Group {
		NativeSource,
		NativeHeader,
		SourceHeader
	}
}
