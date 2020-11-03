const Option = class[T]() => {
    in const map = [R](mapping : T => R) : Option[R];
    in const peek = (consumer : T => Void) => Option[T];
}

const Some = class[T](value : T) => {
    const map = (mapping : T => R) => Some(mapping(value);
    const peek = (consumer : T => Void) => consumer(value);
    out const Option = [T]Option();
}

const None = class[T](value : T) => {
    const map = (mapping : T => R) None[R]();
    const peek = (consumer : T => Void) => {}
    out const Option = [T]Option();
}