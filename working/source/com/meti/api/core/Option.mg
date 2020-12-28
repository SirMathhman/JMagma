struct Option[T]{
    const isPresent : () => Bool,
	const isEmpty : () => Bool,

	const map : [R, E](T => R ? E) ? E,

	const orElse : T => T,
	const orElseThrow : [E](() => E) => T ? E,

	const ifPresent : [E](T => Void) => Void ? E,
}