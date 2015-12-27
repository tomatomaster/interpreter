package com.ono.interpreter.service;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class Interpret {

	public static void main(String[] args) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(File.class.getCanonicalName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// ここはGUI
		Object[] initargs = { "/test" };

		Constructor<?>[] constractors = clazz.getConstructors();
		// インスタンスを格納するオブジェクト
		Object instance = null;
		for (final Constructor<?> constractor : constractors) {
			try {
				try {
					instance = clazz.getConstructor(String.class).newInstance(initargs);
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				showSeparator("Success!");
				break;
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		ClassContents.showContents(clazz);
		showSeparator("FieldsValue");
		ClassContents.printFields(instance);
		showSeparator("Finish !");
		try {
			Field field = clazz.getDeclaredField("path");
			if (!field.isAccessible()) {
				field.setAccessible(true); // privateフィールドのアクセス権取得
				field.set(instance, "newPath");
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		ClassContents.printFields(instance);
	}

	private static void showSeparator(String message) {
		System.out.println("=======================================");
		System.out.println("=====" + message + "======");
		System.out.println("=======================================");
	}

	/**
	 * パラメータのクラスのフィールドを表示する
	 * @param targetClass
	 */
	public static void showFields(Class<?> targetClass) {
		Field[] fields = targetClass.getFields();
		for (final Field field : fields) {
			String modifier = Modifier.toString(field.getModifiers());
			String typeName = field.getType().getSimpleName();
			String name = field.getName();
			System.out.println(modifier + " " + typeName + " " + name);
		}
	}
}