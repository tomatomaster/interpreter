package com.ono.interpreter.application.uiparts.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JButton;

import com.ono.interpreter.application.uiparts.dialog.ExceptionDialog;
import com.ono.interpreter.application.uiparts.list.ConstructorList;
import com.ono.interpreter.application.uiparts.list.MethodList;
import com.ono.interpreter.application.uiparts.list.ParameterList;
import com.ono.interpreter.service.ClassContents;
import com.ono.interpreter.service.ObjectFactoryService;
import com.ono.interpreter.service.ObjectPool;

public class MakeObjectButton extends JButton {

	private static final String NAME = "MakeObject"; 
	public MakeObjectButton() {
		super(NAME);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//1. コンストラクターリストからコンストラクターを取得
					Constructor<?> constructor = (Constructor<?>) ConstructorList.getInstance().getSelectedValue();
					//1.1取得したコンストラクターをオブジェクトファクトリーにセット
					ObjectFactoryService.setConstructor(constructor);
					
					//2.オブジェクトファクトリーからオブジェクトの生成
					Object instance = ObjectFactoryService.makeObject();
					//2.1オブジェクトプールに生成したオブジェクトをセット
					ObjectPool.getInstance().setObject(instance);
					
					//3.生成したオブジェクトのメソッドを表示
					Method[] instanceMethods = ClassContents.getMethods(instance.getClass());
					MethodList.getInstance().setListData(instanceMethods);
					//4.生成したオブジェクトのフィールドを表示
					Field[] instanceFields = ClassContents.getFields(instance.getClass());
					ParameterList.getInstance().setListData(instanceFields);
					//5.InvokeButtonに生成したオブジェクトを渡す
					InvokeButton.setObject(instance);
					//ClassContents.printFields(instance);
					//ClassContents.showContents(instance.getClass());
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e1) {
					new ExceptionDialog(e1.toString());
					e1.printStackTrace();
				}
			}
		});
	}
}
