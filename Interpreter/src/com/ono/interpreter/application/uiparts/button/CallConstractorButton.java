package com.ono.interpreter.application.uiparts.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import com.ono.interpreter.application.uiparts.dialog.ExceptionDialog;
import com.ono.interpreter.application.uiparts.dialog.ParameterInputDialog;
import com.ono.interpreter.application.uiparts.list.ConstructorList;
import com.ono.interpreter.service.ConstructorService;
import com.ono.interpreter.service.ObjectFactoryService;

public class CallConstractorButton extends JButton {

	JTextField textField;
	String constructorName = null;
	
	public CallConstractorButton(final JTextField textField) {
		super("INPUT");
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				constructorName = textField.getText();
				getConstractor();
			}
		});
	}
	
	public String getConstractor() {
		if(constructorName == null) {
			return "";
		}
		Constructor<?>[] constructors = null;
		try {
			//コンストラクタの取得
			constructors = ConstructorService.getConstractor(constructorName);
			//取得したコンストラクタを表示する
			ConstructorList.getInstance().setList(constructors);
		} catch (ClassNotFoundException e) {
			new ExceptionDialog(e.toString());
		}
		return constructorName;
	}
}
