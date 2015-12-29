package com.ono.interpreter.application.uiparts.list;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JList;
import javax.swing.ListSelectionModel;


public class ConstructorList extends JList<Object> {

	private static final long	serialVersionUID	= 1L;
	private static ConstructorList instance = null;
	
	//Singleton
	public static ConstructorList getInstance() {
		if(instance == null) {
			instance = new ConstructorList();
		}
		return instance;
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
