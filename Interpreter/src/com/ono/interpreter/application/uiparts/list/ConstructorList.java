package com.ono.interpreter.application.uiparts.list;


import java.awt.Component;
import java.awt.Dimension;
import java.util.Objects;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class ConstructorList extends JList<Object> {

	private static final long	serialVersionUID	= 1L;
	private static ConstructorList instance = null;

	//Dimensionの値
	private static final int WIDTH = 650;
	private static final int HEIGHT = 200;
	
	//Singleton
	public static ConstructorList getInstance() {
		if(instance == null) {
			instance = new ConstructorList();
		}
		return instance;
	}
	
	public static Component getComponentModel() {
		ConstructorList instance = getInstance();
		JScrollPane scrollPane = new JScrollPane(instance);
		scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		return scrollPane;
	}
	
	private ConstructorList() {
		super();
		//複数のリスト選択を禁止
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	/**
	 * リストに表示するオブジェクトをセットする
	 * @param obj
	 */
	public void setList(Object[] obj) {
		setListData(Objects.requireNonNull(obj));
	}
	
	/**
	 * 選択されているリストを呼び出し側に返す
	 * @return
	 */
	public Object getList() {
		Object selectedValue = Objects.requireNonNull(getSelectedValue());
		return selectedValue;
	}
}
