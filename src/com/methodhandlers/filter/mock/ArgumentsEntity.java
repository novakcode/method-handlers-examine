package com.methodhandlers.filter.mock;

public class ArgumentsEntity {

	private String name;
	private int val;
	private boolean active;

	public ArgumentsEntity(String name) {
		this.name = name;

	}

	public ArgumentsEntity(String name, int val, boolean active) {
		this.name = name;
		this.val = val;
		this.active = active;
	}

	public static String updateName(String name) {
		return name.toUpperCase();
	}

	public static int updateVal(int val) {
		return val * 2;
	}

	public static ArgumentsEntity getUpdated(boolean active, String name, int val) {

		return new ArgumentsEntity(name, val, active);
	}

	public static ArgumentsEntity getArgumentsEntity(ArgumentsEntity argumentsEntity) {
		return new ArgumentsEntity(argumentsEntity.getName());
	}

	// static
	public static ArgumentsEntity update(ArgumentsEntity entity) {
		return new ArgumentsEntity(entity.getName().toUpperCase());
	}

	// virtual no args
	public ArgumentsEntity update() {
		return new ArgumentsEntity(name.toUpperCase());
	}

	// virtual args
	public ArgumentsEntity updateWithArgument(String arg) {
		return new ArgumentsEntity(name.concat(arg));
	}

	public String getName() {
		return name;
	}

	public boolean isActive() {
		return active;
	}

	public int getVal() {
		return val;
	}

	@Override
	public String toString() {
		return name + "," + val + "," + active;
	}

}
