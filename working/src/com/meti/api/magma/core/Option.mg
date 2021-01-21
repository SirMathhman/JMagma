def class Option[T](
);

class Some[T](value : T) => {
	out const asOption = () => Option[T](self);
}

object None[T] {
	out const asOption = () => Option[T](self);
}