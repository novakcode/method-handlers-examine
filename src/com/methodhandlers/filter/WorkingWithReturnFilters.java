package com.methodhandlers.filter;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import com.methodhandlers.filter.mock.ReturnEntity;

public class WorkingWithReturnFilters {

	// When we filter a return value, the return value will be argument for out
	// filter handler.
	// If the filter handle is of a virtual method, the return value of target
	// handler
	// will be reference object for filter handler.
	// The return value of filter handler can be any type, we only have to match
	// return value of target handle and type of argument for filter handle.

	// Filter handle is for a virtual method in this example.
	// In this case the target method "newEntity" will return new ReturnEntity
	// object.
	// We will use this object as reference for our filter method "getList" which is
	// virtual method for ResponseEntity.
	public static String[] filteringReturnValueWithVirtualHandler(String arr) throws Throwable {

		MethodHandle target = MethodHandles.lookup().findStatic(ReturnEntity.class, "newEntity",
				MethodType.methodType(ReturnEntity.class, String.class));

		MethodHandle filter = MethodHandles.lookup().findVirtual(ReturnEntity.class, "getList",
				MethodType.methodType(String[].class));

		MethodHandle filtered = MethodHandles.filterReturnValue(target, filter);

		return (String[]) filtered.invoke(arr);

	}

	// Here, filter handle is for a static method.
	// Our target is virtual method, so we pass the reference object when invoking.
//	 Our filter handle takes "String[]", which is return value of target handle, and returns its length;
	public static int filteringReturnValueWithStaticHandler(String arr) throws Throwable {

		MethodHandle target = MethodHandles.lookup().findVirtual(ReturnEntity.class, "getList",
				MethodType.methodType(String[].class));

		MethodHandle filter = MethodHandles.lookup().findStatic(ReturnEntity.class, "length",
				MethodType.methodType(int.class, String[].class));

		MethodHandle filtered = MethodHandles.filterReturnValue(target, filter);

		return (int) filtered.invoke(new ReturnEntity(arr));

	}

	public static void main(String[] args) throws Throwable {

		String arr = "name,java,pass";

		String[] split = WorkingWithReturnFilters.filteringReturnValueWithVirtualHandler(arr);
		int length = WorkingWithReturnFilters.filteringReturnValueWithStaticHandler(arr);

	}

}
