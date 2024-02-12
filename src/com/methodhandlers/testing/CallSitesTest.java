package com.methodhandlers.testing;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.methodhandlers.callsite.WorkingWithCallSites;
import com.methodhandlers.callsite.mock.Mock.MockUser;

public class CallSitesTest {

	@Test
	public void handlingCallSitesAndHandlers() throws Throwable {
		MockUser[] users = new MockUser[] { new MockUser("John Malkovich", 300, 22),
				new MockUser("Adam Rogan", 3400, 31), new MockUser("Sam Loudermilk", 1000, 45) };
		MockUser expected = new MockUser("Adam Rogan", 3400, 22);
		MockUser result = WorkingWithCallSites.handlingCallSitesAndHandlers(users);

		assertEquals(expected.getName(), result.getName());
		assertEquals(expected.getBalance(), result.getBalance());
		assertEquals(expected.getAge(), result.getAge());

		
		
		
	}
}
