package com.methodhandlers.arguments;

import java.awt.Point;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class WorkingWithFoldAndCollect {

	// Collecting arguments

	// The way collecting arguments works is it takes a target and a filter handler
	// and creates a new handler. If the filter handler has
	// its own arguments, the new handler will have to invoke those arguments on the
	// position given when creating a new handler.
	// The filter takes those arguments and either returns a value (Which will be
	// the argument for target handle) or returns a void.
	// The filter doesn't care about target arguments, it cares about its own
	// arguments. When invoking the new handler, you use both the filter
	// arguments and target arguments(if they exist)
	// the target handler can be a handler for virtual method only if the filter
	// returns the reference needed for the virtual method

	// Here we have an example of a collecting arguments. The target handle takes
	// one argument which is of a ContractMock type.
	// The filer takes 3 arguments and returns one which is of a ContractMock type.
	// The 3 arguments that filter dissolves from invoker don't get passed to target
	// handle, they are only used by filter.
	// Filter takes those 3 arguments and returns one argument which is passed to
	// target. Target prints out the ContractMock fields.
	public static void collectArgumentsExample(String content, Point A, Point B) throws Throwable {

		MethodHandle print = MethodHandles.lookup().findStatic(ContractMock.class, "printContract",
				MethodType.methodType(void.class, ContractMock.class));
		MethodHandle filter = MethodHandles.lookup().findStatic(ContractMock.class, "createContract",
				MethodType.methodType(ContractMock.class, String.class, Point.class, Point.class));

		MethodHandle collect = MethodHandles.collectArguments(print, 0, filter);

		collect.invoke(content, A, B);

	}

	// Folding arguments

	// Folding arguments takes a target and filter. The filter takes arguments of
	// the target.
	// It can process them or if they are referenced it can change them.
	// Filter can return a value, in that case the target takes that return value as
	// its argument.
	// When invoking a newly created handle, we need to put arguments that target
	// needs.
	// If the filter returns a value, we do not put that argument into invoker since
	// the filter holds that value.

	
	// In this case we use foldArguments to create a new handle which will take same arguments as target handle. 
	// Each of those arguments will go through filter, we take ContractMock arguments, change a field of each ContractMock object and we return a void.
	// Even though we return nothing we change the objects which go to main handler modified.
	public static boolean foldArgumentsExample(Point C, ContractMock mockX, ContractMock mockY) throws Throwable {

		MethodHandle target = MethodHandles.lookup().findStatic(ContractMock.class, "setMiddleContract",
				MethodType.methodType(boolean.class, Point.class, ContractMock.class, ContractMock.class));
		MethodHandle filter = MethodHandles.lookup().findStatic(ContractMock.class, "changeSecondPoint",
				MethodType.methodType(void.class, Point.class, ContractMock.class, ContractMock.class));

		MethodHandle collect = MethodHandles.foldArguments(target, filter);

		return (boolean) collect.invoke(C, mockX, mockY);

	}

	
}
