package com.methodhandlers.arguments;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class ManipulationOfArguments {

	// Permuting arguments

	// Creates a method handle which returns the same type as target handle. It
	// allows changing the arguments that invoker will need.
	// After we create a new MethodType object we need to make sure all the
	// arguments of the original handle are covered
	// by new arguments of new MethodType by putting the position of argument of new
	// MethodType for each argument in original handle.

	
	// Example method that takes in 4 arguments. 
	public static int calculateSum(int i, int k, int v, int l) {
		return i * 0 + k * v + l * 0;
	}

	
	// Creates a handle that accepts one argument but places that argument at 4 argument spots of our example handle.
	public static int permutate(int val) throws Throwable {

		MethodHandle calculate = MethodHandles.lookup().findStatic(ManipulationOfArguments.class, "calculateSum",
				MethodType.methodType(int.class, int.class, int.class, int.class, int.class));
		calculate = MethodHandles.permuteArguments(calculate, MethodType.methodType(int.class, int.class), 0, 0, 0, 0);
		
		// val * 0 + val * val + val * 0;
		return (int) calculate.invoke(val);
	}
	
	


	// Dropping arguments

	// It allows us to create dummy arguments. It creates a handler which will take
	// the original arguments and dummy arguments.
	// The dummy the arguments have to be injected into invoker but they are not used.
	
	public static int dummyArguments(String dummy) throws Throwable {
		MethodHandle calculate = MethodHandles.lookup().findStatic(ManipulationOfArguments.class, "calculateSum",
				MethodType.methodType(int.class, int.class, int.class, int.class, int.class));
		calculate = MethodHandles.dropArguments(calculate, 0, String.class);
		
		return (int) calculate.invoke(dummy,1,2,3,4);
		
	}
	

}
