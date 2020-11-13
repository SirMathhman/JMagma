struct Option[T] {
    in const flatMap : [R](T => Option[R]) => Option[R],
    in const peek : (T => Void) => Option[T],

    in const orElseSupply : (() => T) => T,
}

class def Some[T](value : T) => {
    const flatMap : [R](T => Option[R]) => Option[R] = _(value);
    const peek = (consumer : T => Void) => {
        consumer.accept(value);
        return this;
    }
    const orElseSupply = _ => value;
    out const Option = <Option[T]>{};
}

class def None[T]() => {
    const flatMap : [R](T => Option[R]) => Option[R] = None();
    const peek : (T => Void) => Option[T] = _ => this;
    const orElseSupply = _();
    out const Option = <Option[T]>{};
}