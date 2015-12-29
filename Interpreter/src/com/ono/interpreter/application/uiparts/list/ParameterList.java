package com.ono.interpreter.application.uiparts.list;

/**
 * 今は使用していない
 * @author ono
 *
 */
public class ParameterList {

	private static ParameterList instance = null;
	
	public static ParameterList getInstance() {
		if(instance == null) {
			instance = new ParameterList();
		}
		return instance;
	}
	
	private ParameterList() {
		super();
	}
}
