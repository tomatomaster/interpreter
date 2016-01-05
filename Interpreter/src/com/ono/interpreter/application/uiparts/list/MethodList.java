package com.ono.interpreter.application.uiparts.list;

import java.awt.Component;
import java.awt.Dimension;
import java.lang.reflect.Method;
import java.util.Objects;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MethodList extends JList<Method> {
	private static final long	serialVersionUID	= 1L;
	private static MethodList instance = null;
	
	//Dimensionの値
	private static final int WIDTH = 650;
	private static final int HEIGHT = 200;
	
	//Singleton
	public static MethodList getInstance() {
		if(instance == null) {
			instance = new MethodList();
		}
		return instance;
	}
	
	public static Component getComponentModel() {
		MethodList instance = getInstance();
		JScrollPane scrollPane = new JScrollPane(instance);
		scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		return scrollPane;
	}
	
	private MethodList() {
		super();
		//複数のリスト選択を禁止
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	/**
	 * リストに表示するオブジェクトをセットする
	 * @param obj
	 */
	public void setList(Method[] obj) {
		setListData(Objects.requireNonNull(obj));
	}
}
