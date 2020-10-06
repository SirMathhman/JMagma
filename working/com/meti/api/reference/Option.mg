struct Option[T] {
    implicit const transformOrElse : [R]((T) => R,  R) => R;
}

def Some[T](value : T) {
    implicit def transformOrElse[R](mapping : (T) => R, other : R) => mapping(value);
    implicit const Option = Option();
}

def None[T](value : T) {
    implicit def transformOrElse[R](mapping : (T) => R, other : R) => other;
    implicit const Option = Option();
}