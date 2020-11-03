import com.meti.api.core.Option;

const Exception = class(message : Any) => {
    in const findParent ==> None();
}

const ParentException = class(message : Any, parent : Exception) => {
    const findParent ==> Some(parent);
    out const Exception = Exception(message);
}