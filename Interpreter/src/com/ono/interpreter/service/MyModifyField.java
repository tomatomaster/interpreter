package com.ono.interpreter.service;

import java.io.File;
import java.lang.reflect.Field;

public class MyModifyField {
	public static void main(String[] args) {
		File file = new File("/tmp");
		Class<? extends File> clazz = file.getClass();

		Field field = null;

		try {
			field = clazz.getDeclaredField("path");
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}

			System.out.println(field.get(file));

			field.set(file, "tmp/newfile");
			System.out.println(field.get(file));

		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
