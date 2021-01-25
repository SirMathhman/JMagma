struct Some_I16 {
	int value;
	int (*orElse)(void*, int other);
};

int orElse_Some_I16(void* self, int other) {
	struct Some_I16* this = (struct Some_I16*) self;
	return (*(this)).value;
}

struct Some_I16 Some_I16_(int value){
	struct Some_I16 this;
	this.value;
	this.orElse = orElse_Some_I16;
	return this;
}

struct Option_Some_I16 {
	struct Some_I16 super;
	int (*orElse)(void*, struct Some_I16, int);
};

int orElse_Option_Some_I16(void* self, struct Some_I16 super, int value){
	struct Option_Some_I16* this = self;
	return value;
}

struct Option_Some_I16 Option_Some_I16_(struct Some_I16 super) {
	struct Option_Some_I16 this;
	this.super = super;
	this.orElse = orElse_Option_Some_I16;
	return this;
}

struct Option_Some_I16 Option_Some_I16__(struct Some_I16 super){
	return Option_Some_I16_(super);
}

int supply_Option_Some_I16(struct Option_Some_I16 option) {
	return option.orElse(&option, option.super, -1);
}

int main() {
	return supply_Option_Some_I16(Option_Some_I16__(Some_I16_(-1)));
}