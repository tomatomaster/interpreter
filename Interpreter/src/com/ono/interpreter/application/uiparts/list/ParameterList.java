package com.ono.interpreter.application.uiparts.list;

import java.awt.Component;
import java.awt.Dimension;
import java.lang.reflect.Field;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * 今は使用していない
 * @author ono
 *
 */
public class ParameterList extends JList<Field> {
	private static final long	serialVersionUID	= 1L;
	private static ParameterList instance = null;
	
	//Dimensionの値
	private static final int WIDTH = 650;
	private static final int HEIGHT = 200;
	
	//Singleton
	public static ParameterList getInstance() {
		if(instance == null) {
			instance = new ParameterList();
		}
		return instance;
	}
	
	public static Component getComponentModel() {
		ParameterList instance = getInstance();
		JScrollPane scrollPane = new JScrollPane(instance);
		scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		return scrollPane;
	}
	
	private ParameterList() {
		super();
		//複数のリスト選択を禁止
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
	}
}
