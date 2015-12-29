package com.ono.interpreter.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * オブジェクトの生成を行う
 * @author ono
 *
 */
public class ObjectFactoryService {
	private static ObjectFactoryService instance;
	private static Constructor<?> constructor;
	private static Object[] args;
	private static Object obj;
	
	public Constructor<?> getConstructor() {
		return constructor;
	}

	public static void setConstructor(Constructor<?> constructor) {
		ObjectFactoryService.constructor = constructor;
	}

	public static Object[] getArgs() {
		return args;
	}

	public static void setArgs(Object[] args) {
		ObjectFactoryService.args = args;
	}
	
	//Singleton
	public static ObjectFactoryService getInstance() {
		if(instance == null) {
			instance = new ObjectFactoryService();
		}
		return instance;
	}
	
	//Singleton
	public static ObjectFactoryService getInstance(Constructor<?> constructor) {
		if(instance == null) {
			instance = new ObjectFactoryService(constructor);
		}
		return instance;
	}
	
	/**
	 * 今の所何もしない
	 */
	private ObjectFactoryService() {}

	private ObjectFactoryService(Constructor<?> constructor) {
		this.constructor = Objects.requireNonNull(constructor);
	}
	
	/**
	 * コンストラクタに渡したコンストラクラスオブジェクトを利用してインスタンスを作成する
	 * 例外はapplication側でキャッチしてエラーダイアログを表示すること
	 * @param args
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Object makeObject(Object... args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object result = constructor.newInstance(args);
		return result;
	}
	
	/**
	 * 引数のコンストラクタに引数のパラメーターを渡してインスタンスを生成する
	 * @param constractor
	 * @param args
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Object makeObject(Constructor<?> constractor, Object... args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object result = Objects.requireNonNull(constractor).newInstance(args);		
		return result;
	}
	
	public static Object makeObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		obj = constructor.newInstance(args);
		return obj;
	}
}
