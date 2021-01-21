import com.meti.api.magma.{
	core.Option,
	string.String
}

struct Exception {
	const message : String = ""
	const cause : Option[Exception] = None[Exception];
}