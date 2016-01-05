package com.ono.interpreter.application.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.PrimitiveType;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import com.ono.interpreter.application.uiparts.button.MakeObjectButton;
import com.ono.interpreter.application.util.GridBagLayoutUtil;
import com.ono.interpreter.service.ObjectFactoryService;

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
		
		int componentIndent = 0; //コンポーネントの配置位置を指定する
		for (final Class<?> type : parameterTypes) {
			// ラベルの貼り付け
			JLabel typeName = new JLabel(type.getSimpleName());
			GridBagLayoutUtil.setGbcLayout(0, componentIndent, gbc, typeName, layout, this);
			// Primitive型に対してはスピナーで入力受付
			if (type.isPrimitive()) {
				JSpinner valueSpnipper = new JSpinner();
				//TODO 値の上限値下限値を設定する
				valueSpnipper.setPreferredSize(new Dimension(80,20));
				GridBagLayoutUtil.setGbcLayout(1, componentIndent, gbc, valueSpnipper, layout, this);
			}
			componentIndent++;
		}
		
		JButton setPrameterButton = new JButton("Set");
		setPrameterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			  
			}
		});
		
		GridBagLayoutUtil.setGbcLayout(1, componentIndent, gbc, setPrameterButton, layout, this);
	}

}
