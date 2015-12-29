package com.ono.interpreter.application.uiparts.table;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

public class ArgumentsTable extends JTable {

	private final static String NAME = "arguments";
	List<Class<?>> consData = new ArrayList<Class<?>>();
	String[][][] data;
	String[] Attribute = {"TYPE", "NAME", "VALUE"};
	
	public ArgumentsTable(Constructor<?> constructor) {
		for(final Class<?> paraType : constructor.getParameterTypes()) {
			paraType.getCanonicalName();
		}
	}
	
	ArgumentsTable(List<Class<?>> tableData) {
		super(tableData, NAME);
		for(final Class<?> paraType : constructor.getParameterTypes()) {
			tableData.add(paraType);
		}

	}
}
