package com.ono.interpreter.application.uiparts.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JButton;

import com.ono.interpreter.application.uiparts.dialog.ExceptionDialog;
import com.ono.interpreter.application.uiparts.dialog.InvokeReturnValueDialog;
import com.ono.interpreter.application.uiparts.list.MethodList;
import com.ono.interpreter.service.ObjectPool;

public class InvokeButton extends JButton {
	final private static String	NAME	= "Invoke";
	private static Object obj;
	private static Object[] args;
	private static Object returnValue;
	
	public InvokeButton() {
		super(NAME);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Method method = MethodList.getInstance().getSelectedValue();
				if(!method.isAccessible()) {
					method.setAccessible(true);
				}
				try {
					returnValue = method.invoke(obj, args);
					//void でsysoutしてるようなメソッドように標準アウトプット先をダイアログに変更する
					new InvokeReturnValueDialog(returnValue).setVisible(true);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e1) {
					new ExceptionDialog(e1.toString());
					e1.printStackTrace();
				}
			}
		});
	}
	
	public static void setObject(Object obj) {
		InvokeButton.obj = obj;
	}
}
