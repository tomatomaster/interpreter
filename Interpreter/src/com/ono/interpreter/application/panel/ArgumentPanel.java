package com.ono.interpreter.application.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.PrimitiveType;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class ArgumentPanel extends JPanel {

	private static ArgumentPanel	instance	= null;
	private List<JSpinner>			spinners	= new ArrayList<>();

	public static Constructor<?>	constructor;

	public static ArgumentPanel getInstance() {
		if (instance == null) {
			instance = new ArgumentPanel();
		}
		return instance;
	}

	private ArgumentPanel() {
		super();
		setLayout(new FlowLayout());
	}

	public void setConstructor(Constructor<?> constructor) {
		ArgumentPanel.constructor = constructor;
		Class<?>[] parameterTypes = constructor.getParameterTypes();
		removeAll();
		for (final Class<?> type : parameterTypes) {
			JLabel typeName = new JLabel(type.getSimpleName());
			add(typeName);
			//Primitive型に対してはスピナーで入力受付
			if (type.isPrimitive()) {
				JSpinner valueSpnipper = new JSpinner();
				valueSpnipper.setSize(3, 1);
				add(new JSpinner());
			}
		}
	}

}
