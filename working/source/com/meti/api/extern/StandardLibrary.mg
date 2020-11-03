import native stdlib;

native def malloc(size : Size) => Any;
native def calloc(size : Size) => Any;
native def realloc(buffer : Any, size : Size) => Any;
native def free(buffer : Any) => Void;