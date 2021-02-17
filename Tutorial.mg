//Inline comment
/* First
   Second
   Third
*/

/*
Types

--- Integers ---
U8, U16, U32, U64,
I8, I16, I32, I64

U or I indicates whether the number is signed.
Trailing number indicates the number of bits.

There exists a boolean type 'Bool'.
There exists a void type 'Void'.
There exists an any type 'Any', used for variables representing any type of data.
	(This is to clear up the void* usages in C)
*/

//empty braces
{
}

{
	//immutable
	const x : I16 = 420;

	//mutable
	let y : I16 = 420;
}

//we can remove the type because numbers with no annotation is assumed to be of I16.
{
	const x = 420;
	let y = 420;
}

{
	if(true){
		//this always happens
	}

	if(false){
		//this never happens
	} else {
		//this always happens
	}

	if(true){
	} elif(true){
	} else {
	}

	const x = 420;

	//first variant
	let y : I16;
	switch(x){
		case 420 => {
			y = 10;
		}
		case 400 => y = 0;
		default => -1;
	}

	//second variant
	let z = switch(x){
		case 420 => 10;
		case 400 => 0;
		default => -1;
	}
}

{
	let x = 10;
	let y = 20;
	let z = x + y;
	let a = x >> 2;
}

{
	def myFunction(value : I16) : I16 => {
		return value;
	}
	def myFunction1(value : I16) : I16 => return value;
	def myFunction2(value : I16) : I16 => value;
	def myFunction3(value : I16) => value;

	//all above functions are equivalent

	let x = myFunction(10);

	//referring to functions by reference
	const myFunctionRef : (I16) => I16 = myFunction;
	const myFunctionRef1 = myFunction;
}

{
	struct Point {
		const x : I16;
		const y : I16;
	}

	const myPoint = [Point]{3, 4};
	const myX = myPoint.x; //or
	const myX2 = myPoint => x; //personal preference

	// doesn't work because members are constant -> const myPoint : Point
	const myPointRef : Ref[Point] = &myPoint;
	const myX3 = myPointRef.x;
	//automatic dereference
}

{
	//methods
	struct Sum {
        const x : I16;
        const y : I16;
    }

	def sum0(self : Ref[Any]) => {
		//potentially unsafe case
		const this = [Ref[Sum]] self;
		return this.x + this.y;
	}

	//use methods for safety! compiles to above!
	method def sum1(this : Ref[Sum]) => this.x + this.y;

	const mySum = [Sum]{3, 4};
	const noMethod = sum0(&mySum);


	// here, sum1 acts as an extension method to the struct
	// both lines are equivalent and the latter compiles to the former
	const asMethod = sum1(&mySum);
	const asMethodWithCaller = mySum.sum1(&mySum);
	// but this doesn't work because Sum doesn't have sum1 -> mySum => sum1

	/*
	=> always returns the value of the member
	. sometimes returns the value of the member (in the case of primitives)
		but may also result in a method invocation
	*/

	struct AnotherSum {
        const first : I16;
        const second : I16;

        //example
        const sum0 = method(this : Ref[Sum]) => {
            return this.x + this.y;
        }
        //remove the braces
        const sum1 = method(this : Ref[Sum]) => this.x + this.y;

        /*
        remove the "this" keyword, this is implicit b/c the function is default inside of a struct
        that struct type carries over to 'this'
        */
        const sum2 = method() => this.x + this.y;

        //don't need the 'this' keyword b/c we're in a struct
        const sum3 = method() => x + y;

        //don't the 'method' keyword b/c again, we're in a struct
        const sum4 = () => x + y;

        // we need the parentheses otherwise it'll look funny -> const sum5 ==> x + y;
        // imagine that '=>' is the entire indicator that a function is present...
    }

    def AnotherSum(const first : I16, const second : I16) => {
        // implicit this with the type of the function;
        // method binding occurs later
        //let this = [AnotherSum]{first, second, null};
        const firstSum = () => x + y;
        //compiles to this.firstSum = () => x + y;, invokes method binding
        def secondSum() => x + y;
        return this;
    }

    //since 'return this;' will become a recurrent feature, that can be replaced with the 'class' keyword
    class def AnotherSum1(const first : I16, const second : I16) => {
	    const firstSum = () => x + y;
	    def secondSum = () => x + y;
	}

	//we can also define as a lambda and assign immediately to variable
	const AnotherSum2 = class(const first : I16, const second : I16) => {
		const firstSum = () => x + y;
		def secondSum = () => x + y;
	}
	// because the struct is 1) auto-generated and 2) is created in a function, then
	// the function can treated as a constructor

	const mySum6 = AnotherSum(3, 4);
	/*
	we can invoke firstSum but not secondSum
	because firstSum was bound to the this keyword
	secondSum was declared locally, but not attached to the struct
	*/

	const result = mySum6.firstSum();
	//more or less, 'secondSum' is private...
	//doesn't work const result2 = mySum6.secondSum();
}

{
	//Generic Structures
	struct Wrapper[T]{
		const value : T;
	}

	const wrapper = [Wrapper[I16]]{420};
	const result = wrapper.value;

	//Generic Functions
	def genFunction[T](input : T) => T;
	const passing = genFunction(420); //T is I16
}

{
	// 'in' keyword, takes information from calling context implicitly

	def accepting(in value : I16) => value;

	const value = 420;
	const result = accepting();
	// the variable 'value' is passed implicitly
	// because the name of the variable and name of the parameter match
}

{
	// 'out' keyword, mostly used for implicit type conversions
	// examples include 'asString'

	out def toBoolean(value : I16) => value > 0;

	const x : I16 = 420;

	//explicit
	const y1 : Bool = toBoolean(x);

	/*
	implicit

	Here, y cannot be forced to the type of I16 because
	we've already set it explicitly to Bool

	However, there exists a conversion method from I16 to Bool,
	and that is implicitly invoked
	*/
	const y : Bool = x;
}

{
	// polymorphism

	// also we don't need the 'let' or 'const' keywords in parameters
	// it's assumed to be constant
	class def Vehicle[T](
		in super : T,
		in move : (T = super) => Void,
	);

	class def Car() => {
		out const Vehicle = () => Vehicle(this);
	}

	const myCar = Car();
	const myVehicle : Vehicle = myCar;
	myVehicle.move();
}

// API

{
	//strings
	const temp : Ref[I8] = "Hello World!"
	const string0 : String = temp;
	const string1 = "Hello World!";
}

{
	//options
	def onlyPositive(input : I32) : Option[U32] => if(input >= 0) Some(input)
												   else None();

}

