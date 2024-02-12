package com.methodhandlers.filter;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import com.methodhandlers.filter.mock.ArgumentsEntity;

public class WorkingWithArgumentFilters {

	// The way argument filtering works is simple. The argument is passed to method
	// handler that uses it for invocation. Here's the important part.
	// If the the filtering method handler is for a virtual method, that method must
	// not have any argument. If it did, IllegalArgumentException would be thrown.
	// Since a virtual method required a reference object, that reference object is
	// passed as argument we are filtering.
	// Meaning, the reference object and the argument we are filtering have to be
	// the same type.
	// If the filtering method handle required more arguments, then when the
	// invocation of it is called, it will lack the arguments needed.
	// Because the only argument it will have will be reference object.

	// On the other hand, when the filtering method handler is for a static method,
	// the reference object is not needed and the filtering method can have an
	// argument.
	// Again, that argument has to be the same type as the argument we filter.

	// With this method, we create a getArgumentsEntity handler which returns
	// ArgumentsEntity class and has an ArgumentsEntity argument.
	// Filter handler takes the argument which is its reference object, modifies the
	// field "name" of ArgumentsEntity, and returns a new ArgumentsEntity.
	// Since filter handler is for a virtual method, it must have no arguments as it
	// gets the reference object from the argument its filtering.
	public static String filteringArgumentsWithVirtualNoArgs(String name) throws Throwable {

		MethodHandle get = MethodHandles.lookup().findStatic(ArgumentsEntity.class, "getArgumentsEntity",
				MethodType.methodType(ArgumentsEntity.class, ArgumentsEntity.class));

		MethodHandle updateFilter = MethodHandles.lookup().findVirtual(ArgumentsEntity.class, "update",
				MethodType.methodType(ArgumentsEntity.class));

//		
		MethodHandle filtered = MethodHandles.filterArguments(get, 0, updateFilter);

		return ((ArgumentsEntity) filtered.invoke(new ArgumentsEntity(name))).getName();

	}

	// We try creating a filter with virtual method which takes an additional
	// argument.
	// It should take the reference object and concat argument to the field "name"
	// of EntityObject it uses as reference.
	// This will throw IllegalArgumentException, since the target and filter types
	// do no match.
	// The filter expects both the reference and its own argument. It only gets the
	// reference.
	public static String filteringArgumentsWithVirtualAndArgs(String name, String arg) throws Throwable {

		MethodHandle get = MethodHandles.lookup().findStatic(ArgumentsEntity.class, "getArgumentsEntity",
				MethodType.methodType(ArgumentsEntity.class, ArgumentsEntity.class));

		MethodHandle updateFilter = MethodHandles.lookup().findVirtual(ArgumentsEntity.class, "updateWithArgument",
				MethodType.methodType(ArgumentsEntity.class, String.class));

//		
		MethodHandle filtered = MethodHandles.filterArguments(get, 0, updateFilter);

		return ((ArgumentsEntity) filtered.invoke(new ArgumentsEntity(name), arg)).getName();

	}

	// With handler for a static method we need to match the argument we are
	// filtering with argument of method that filters the argument.
	// The filtering method can't be without argument.
	public static String filteringArgumentsWithStaticHandler(String name) throws Throwable {
		MethodHandle get = MethodHandles.lookup().findStatic(ArgumentsEntity.class, "getArgumentsEntity",
				MethodType.methodType(ArgumentsEntity.class, ArgumentsEntity.class));

		MethodHandle updateFilter = MethodHandles.lookup().findStatic(ArgumentsEntity.class, "update",
				MethodType.methodType(ArgumentsEntity.class, ArgumentsEntity.class));

//		
		MethodHandle filtered = MethodHandles.filterArguments(get, 0, updateFilter);

		return ((ArgumentsEntity) filtered.invoke(new ArgumentsEntity(name))).getName();
	}

	// Lets create filters for two arguments of method "getUpdated" which takes 3
	// arguments.
	// We will filter "name" and "val" by using two handlers. We will set position
	// to 1 so we skip the first argument which we won't filter
	// args(boolean active, #String name, #int val)
	public static String filtertingWithMultipleFilters(boolean active, String name, int val) throws Throwable {

		MethodHandle getUpdated = MethodHandles.lookup().findStatic(ArgumentsEntity.class, "getUpdated",
				MethodType.methodType(ArgumentsEntity.class, boolean.class, String.class, int.class));

		MethodHandle filterForName = MethodHandles.lookup().findStatic(ArgumentsEntity.class, "updateName",
				MethodType.methodType(String.class, String.class));

		MethodHandle filterForVal = MethodHandles.lookup().findStatic(ArgumentsEntity.class, "updateVal",
				MethodType.methodType(int.class, int.class));

		MethodHandle filtered = MethodHandles.filterArguments(getUpdated, 1, filterForName, filterForVal);

		return filtered.invoke(active, name, val).toString();

	}


}
