import _.Option;

struct Exception {
    const message : String;
    in const getCause : () => Option[Exception];
}

const MessageException = (message : String) => {
    const getCause = () => None();
    out const Exception = [Exception]{message};
}

const ParentException = (message : String, cause : Exception) => {
    const getCause = () => Some(cause);
    out const Exception = [Exception]{message};
}