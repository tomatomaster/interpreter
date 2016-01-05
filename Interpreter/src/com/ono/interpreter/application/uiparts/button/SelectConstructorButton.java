package com.ono.interpreter.application.uiparts.button;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JDialog;

import com.ono.interpreter.application.panel.ArgumentPanel;
import com.ono.interpreter.application.uiparts.dialog.ExceptionDialog;
import com.ono.interpreter.application.uiparts.dialog.ParameterInputDialog;
import com.ono.interpreter.application.uiparts.list.ConstructorList;
import com.ono.interpreter.service.ClassContents;
import com.ono.interpreter.service.ObjectFactoryService;

/**
 * オブジェクトを生成するトリガーになるボタン
 * 
 * @author ono
 *
 */
public class SelectConstructorButton extends JButton {

	private static SelectConstructorButton	instance	= null;
	private static final String		NAME		= "SetParameter";

	private static Object			obj			= null;

	/*
	 * Singleton
	 */
	public static SelectConstructorButton getInstance() {
		if (instance == null) {
			instance = new SelectConstructorButton();
		}
		return instance;
	}

	private SelectConstructorButton() {
		super(NAME);
		final JDialog dialog = new ParameterInputDialog();
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Constructor<?> constructor = (Constructor<?>) ConstructorList.getInstance().getList();
				// コンストラクタパネルの設定
				ArgumentPanel.getInstance().setConstructor(constructor);
				ObjectFactoryService.setConstructor(constructor);
				dialog.setVisible(true);
			}
		});
	}

	/**
	 * 生成したインスタンスを管理するためのSetter
	 * 
	 * @param obj
	 */
	private void setObject(Object obj) {
		this.obj = Objects.requireNonNull(obj);
	}

	/**
	 * 外部からインスタンスを取得するためのgetter
	 * 
	 * @return
	 */
	public Object getObject() {
		return this.obj;
	}
}
