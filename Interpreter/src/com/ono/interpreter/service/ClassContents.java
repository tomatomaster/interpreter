package com.ono.interpreter.service;


import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassContents {
	public static void main(String[] args) {
		Class<?> c;
		try {
			c = Class.forName(args[0]);
			System.out.println(c);
			
			printMembers(getAllFieldIncludingSuperClass(c.getFields(), c.getDeclaredFields()));
			printMembers(getAllFieldIncludingSuperClass(c.getConstructors(), c.getDeclaredConstructors()));
			printMembers(getAllFieldIncludingSuperClass(c.getMethods(), c.getDeclaredMethods()));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void showContents(Class<?> c) {

			System.out.println(c);
			printMembers(getAllFieldIncludingSuperClass(c.getFields(), c.getDeclaredFields()));
			printMembers(getAllFieldIncludingSuperClass(c.getConstructors(), c.getDeclaredConstructors()));
			printMembers(getAllFieldIncludingSuperClass(c.getMethods(), c.getDeclaredMethods()));
	}

	private static Member[] getAllFieldIncludingSuperClass(Member[] normalM, Member[] declaredM) {
		List<Member> normalMList = new ArrayList<>();
		//public 宣言されたメンバの配列をリスト化（containsメソッドを使用したいので）
		for(final Member m : normalM) {
			normalMList.add(m);
		}
		//Declaredメンバ固有のメンバを取得
		for(final Member m: declaredM) {
			if(!normalMList.contains(m)) {
				normalMList.add(m);
			}	
		}
		return normalMList.toArray(new Member[normalMList.size()]);
	}
	
	/**
	 * オブジェクトのメソッド配列を返す
	 * @param normalM
	 * @param declaredM
	 * @return
	 */
	public static Method[] getMethods(Class<?> clazz) {
		Member[] normalM = clazz.getMethods();
		Member[] declaredM = clazz.getDeclaredMethods();
		List<Member> normalMList = new ArrayList<>();
		//public 宣言されたメンバの配列をリスト化（containsメソッドを使用したいので）
		for(final Member m : normalM) {
			normalMList.add(m);
		}
		//Declaredメンバ固有のメンバを取得
		for(final Member m: declaredM) {
			if(!normalMList.contains(m)) {
				normalMList.add(m);
			}	
		}
		return normalMList.toArray(new Method[normalMList.size()]);
	}
	
	/**
	 * オブジェクトのメソッド配列を返す
	 * @param normalM
	 * @param declaredM
	 * @return
	 */
	public static Field[] getFields(Class<?> clazz) {
		Member[] normalM = clazz.getFields();
		Member[] declaredM = clazz.getDeclaredFields();
		List<Member> normalMList = new ArrayList<>();
		//public 宣言されたメンバの配列をリスト化（containsメソッドを使用したいので）
		for(final Member m : normalM) {
			normalMList.add(m);
		}
		//Declaredメンバ固有のメンバを取得
		for(final Member m: declaredM) {
			if(!normalMList.contains(m)) {
				normalMList.add(m);
			}	
		}
		return normalMList.toArray(new Field[normalMList.size()]);
	}
	
	public static void printFields(Object obj) {
		Field[] fies = obj.getClass().getDeclaredFields();
		for(final Field f: fies) {
			if(!f.isAccessible()) {
				f.setAccessible(true);
			}
			try {
				System.out.println(f.getName() + " = " + f.get(obj));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void printMembers(Member[] mems) {
		for(final Member m: mems) {
			if(m.getDeclaringClass() == Object.class) {
				continue;
			}

			String decl = m.toString();
			System.out.print(" ");
			System.out.println(strip(decl, "java.lang."));
		}
	}
	
	private static String strip(String str, String delimi) {
		return str.replaceAll(delimi, "");
	}
}
