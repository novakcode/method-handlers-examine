package com.methodhandlers.callsite.mock;

import java.util.Arrays;
import java.util.Comparator;

public class Mock {

	private String field;

	public Mock(String field) {
		this.field = field;
	}

	public void print() {
		System.out.println(field);
	}

	public static MockUser[] sortByBalance(MockUser[] users) {
		Comparator<MockUser> comparator = Comparator.comparing((e) -> e.getBalance());
		Arrays.sort(users, comparator.reversed());
		return users;

	}

	public static MockUser[] sortByAge(MockUser[] users) {
		Arrays.sort(users, Comparator.comparing((e) -> e.getAge()));
		;
		return users;
	}

//
	public static MockUser[] sortByName(MockUser[] users) {

		Arrays.sort(users, Comparator.comparing((e) -> e.getName()));
		;
		return users;
	}

	public static MockUser get(MockUser[] users) {
		return users[0];
	}

	public static class MockUser {

		private String name;
		private int balance;
		private int age;

		public MockUser(String name, int balance, int age) {
			super();
			this.name = name;
			this.balance = balance;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public int getBalance() {
			return balance;
		}

		public int getAge() {
			return age;
		}

		@Override
		public boolean equals(Object obj) {
			MockUser usermock = (MockUser) obj;

			return usermock.getAge() == age && usermock.getBalance() == balance && usermock.equals(name);
		}

		@Override
		public String toString() {
			return name + "," + balance + "," + age;
		}
	}
}
