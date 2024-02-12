package com.methodhandlers.testing;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import com.methodhandlers.filter.WorkingWithArgumentFilters;
import com.methodhandlers.filter.WorkingWithReturnFilters;

public class FiltersTest {

	@Test
	public void filteringArgumentsWithVirtualAndArgs() {

		String name = "name";
		String arg = "arg";

		assertThrows(IllegalArgumentException.class,
				() -> WorkingWithArgumentFilters.filteringArgumentsWithVirtualAndArgs(name, arg));

	}

	@Test
	public void filteringArgumentsWithVirtualNoArgs() throws Throwable {
		String name = "name";
		String expected = "NAME";

		assertEquals(expected, WorkingWithArgumentFilters.filteringArgumentsWithVirtualNoArgs(name));

	}

	@Test
	public void filteringArgumentsWithStaticHandler() throws Throwable {

		String name = "name";
		String expected = "NAME";

		assertEquals(expected, WorkingWithArgumentFilters.filteringArgumentsWithStaticHandler(name));

	}

	@Test
	public void filtertingWithMultipleFilters() throws Throwable {

		String name = "name";
		int val = 1;
		String expected = "NAME,2,true";

		assertEquals(expected, WorkingWithArgumentFilters.filtertingWithMultipleFilters(true, name, val));
	}

	
	@Test
	public void filteringReturnValueWithVirtualHandler() throws Throwable {
		String arr = "name,java,pass";
		String[] expected = arr.split(",");

		assertArrayEquals(expected, WorkingWithReturnFilters.filteringReturnValueWithVirtualHandler(arr));
	}

	@Test
	public void filteringReturnValueWithStaticHandler() throws Throwable {
		String arr = "name,java,pass";
		int expected = arr.split(",").length;

		assertEquals(expected, WorkingWithReturnFilters.filteringReturnValueWithStaticHandler(arr));
	}

}
