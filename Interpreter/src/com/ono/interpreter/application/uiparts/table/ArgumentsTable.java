package com.ono.interpreter.application.uiparts.table;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class ArgumentsTable extends JTable {

	private final static String NAME = "arguments";
	private final String[] Attribute = {"TYPE", "VALUE"};
	
	private Class<?>[] types = new Class<?>[0];
	private Object[] objects = new Object[0];
	    
	
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
}
