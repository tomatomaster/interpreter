package com.ono.interpreter.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成したオブジェクトの管理を行う
 * @author ono
 *
 */
final public class ObjectPool {
	private static ObjectPool	instance	= null;
	Map<String, Object>				objectPool;

	//Singleton
	public static ObjectPool getInstance() {
		if (instance == null) {
			instance = new ObjectPool();
		}
		return instance;
	}

	/**
	 * 
	 */
	private ObjectPool() {
		objectPool = new HashMap<>();
	}

	/**
	 * オブジェクトをSimpleNameで管理
	 * @param obj
	 */
	public void setObject(Object obj,String objName) {
		objectPool.put(objName, obj);
	}
	
	/**
	 * オブジェクトの取得を行う
	 * @param objName
	 */
	public Object getObject(String objName) {
		return objectPool.get(objName);
	}
	
	/**
	 * オブジェクトを管理しているマップを返す
	 * @return
	 */
	public Map<String, Object> getObjectPool() {
		return objectPool;
	}
	
	/**
	 * オブジェクトを管理しているマップをリセットする
	 */
	public void clear() {
		objectPool.clear();
	}
}
