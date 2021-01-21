struct Equatable[T]{
	in const equalsTo : T => Bool;
}

struct Equator[T]{
	in const equalsTo : (T, T) => Bool;
}