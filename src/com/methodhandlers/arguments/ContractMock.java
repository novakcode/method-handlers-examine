package com.methodhandlers.arguments;

import java.awt.Point;

public class ContractMock {

	private String content;
	private Point A, B;

	public ContractMock(String content, Point A, Point B) {
		this.content = content;
		this.A = A;
		this.B = B;
	}

	public static boolean setMiddleContract(Point C, ContractMock mockX, ContractMock mockY) {
		return mockX.getB().equals(C) && mockY.getB().equals(C);
	}

	public static void changeSecondPoint(Point C, ContractMock mockX, ContractMock mockY) {
		mockX.setB(C);
		mockY.setB(C);
	}

	public static ContractMock createContract(String content, Point A, Point B) {
		return new ContractMock(content, A, B);
	}

	public static void printContract(ContractMock contract) {
		System.out.println(contract.getA() + " - " + contract.getB());
		System.out.println(contract.getContent());
	}

	public String getContent() {
		return content;
	}

	public Point getA() {
		return A;
	}

	public Point getB() {
		return B;
	}

	public void setA(Point a) {
		A = a;
	}

	public void setB(Point b) {
		B = b;
	}

}
