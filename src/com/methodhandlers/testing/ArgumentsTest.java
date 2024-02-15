package com.methodhandlers.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import com.methodhandlers.arguments.ContractMock;
import com.methodhandlers.arguments.WorkingWithFoldAndCollect;

public class ArgumentsTest {

	@Test
	public void foldArgumentsExample() throws Throwable {
		Point pointC = new Point(5, 5);
		ContractMock contractX = new ContractMock("content", new Point(1, 1), new Point(2, 2));
		ContractMock contractY = new ContractMock("content", new Point(1, 1), new Point(2, 2));
		
		assertEquals(true, WorkingWithFoldAndCollect.foldArgumentsExample(pointC, contractX, contractY));
	}

	@Test
	public void collectArgumentsExample() {

		String content = "content";
		Point A = new Point(1, 1);
		Point B = new Point(2, 2);

		assertDoesNotThrow(() -> WorkingWithFoldAndCollect.collectArgumentsExample(content, A, B));

	}

}
