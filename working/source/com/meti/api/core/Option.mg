struct Option[T] {
}

const Some = [T](value : T) => {
    out const Option = Option();
}

const None = [T]() => {
    out const Option = Option();
}