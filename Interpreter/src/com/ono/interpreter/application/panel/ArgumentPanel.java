package com.ono.interpreter.application.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.PrimitiveType;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import com.ono.interpreter.application.util.GridBagLayoutUtil;

public class ArgumentPanel extends JPanel {

	private static ArgumentPanel	instance	= null;
	public static Constructor<?>	constructor;
	private static GridBagLayout layout = new GridBagLayout();
	GridBagConstraints	gbc		= new GridBagConstraints();


	public static ArgumentPanel getInstance() {
		if (instance == null) {
			instance = new ArgumentPanel();
		}
		return instance;
	}

	private ArgumentPanel() {
		super();
		setLayout(layout);
	}

	public void setConstructor(Constructor<?> constructor) {
		ArgumentPanel.constructor = constructor;
		Class<?>[] parameterTypes = constructor.getParameterTypes();
		removeAll(); //前回addしたコンポーネントをクリアする
		
		int setY = 0; //コンポーネントの配置位置を指定する
		for (final Class<?> type : parameterTypes) {
			// ラベルの貼り付け
			JLabel typeName = new JLabel(type.getSimpleName());
			GridBagLayoutUtil.setGbcLayout(0, setY, gbc, typeName, layout, this);
			// Primitive型に対してはスピナーで入力受付
			if (type.isPrimitive()) {
				JSpinner valueSpnipper = new JSpinner();
				valueSpnipper.setSize(3, 1);
				GridBagLayoutUtil.setGbcLayout(1, setY, gbc, valueSpnipper, layout, this);
			}
			setY++;
		}
	}

}
