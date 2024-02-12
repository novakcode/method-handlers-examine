package com.methodhandlers.filter.mock;

public class ReturnEntity {

	private String arr;
	private char splitter;

	public ReturnEntity(String arr) {
		this.arr = arr;
		this.splitter = ',';
	}

	public static ReturnEntity newEntity(String arr) {
		return new ReturnEntity(arr);
	}

	public static int length(String[] arr) {
		return arr.length;
	}

	public String[] getList() {
		return arr.split("" + splitter);
	}

}
