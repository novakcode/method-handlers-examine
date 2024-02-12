package com.methodhandlers.callsite;

import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.methodhandlers.callsite.mock.Mock;
import com.methodhandlers.callsite.mock.Mock.MockUser;

public class WorkingWithCallSites {

	// CallSites give usability and allows us to reuse same object but with
	// different handlers. It acts as a wrapper for MethodHandles;
	// With MutableCallSite we set MethodType either by setting a MethodHandler from
	// where we extract MethodType or directly setting MethodType;
	// This allows us to use Callsite object and by using setTarget change the
	// handler.
	// Important rule, when calling MutableCallSite constructor using MethodType,
	// if the target will be a virtual method, MethodType needs to have both return
	// and reference argument (and then other arguments if needed)

	// Here we have two CallSites. One is constant and handler stays the same while
	// the other is mutable so we change handler based on what we need.
	// Each mutable site handler is accepting "MockUser[]" as argument and return
	// "MockUser[]" which is passed to constantCallSite invoker.
	// ConstantCallSite accepts "MockUser[]" and returns the first element. Its
	// handler stays the same.
	public static MockUser handlingCallSitesAndHandlers(MockUser[] users) throws Throwable {
		MutableCallSite mcs = new MutableCallSite(MethodType.methodType(MockUser[].class, MockUser[].class));

		ConstantCallSite ccs = new ConstantCallSite(MethodHandles.lookup().findStatic(Mock.class, "get",
				MethodType.methodType(MockUser.class, MockUser[].class)));
		MethodHandle constantInvoker = ccs.dynamicInvoker();
		MethodHandle invoker = mcs.dynamicInvoker();

		MockUser user = null;
		String name;
		int highestBalance;
		int youngestAge;

		mcs.setTarget(MethodHandles.lookup().findStatic(Mock.class, "sortByName",
				MethodType.methodType(MockUser[].class, MockUser[].class)));
		user = (MockUser) constantInvoker.invoke(invoker.invoke(users));
		name = user.getName();

		mcs.setTarget(MethodHandles.lookup().findStatic(Mock.class, "sortByBalance",
				MethodType.methodType(MockUser[].class, MockUser[].class)));
		user = (MockUser) constantInvoker.invoke(invoker.invoke(users));
		highestBalance = user.getBalance();

		mcs.setTarget(MethodHandles.lookup().findStatic(Mock.class, "sortByAge",
				MethodType.methodType(MockUser[].class, MockUser[].class)));
		user = (MockUser) constantInvoker.invoke(invoker.invoke(users));
		youngestAge = user.getAge();

		return new MockUser(name, highestBalance, youngestAge);
	}

	

}
