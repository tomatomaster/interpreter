package com.ono.interpreter.application.panel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.ono.interpreter.application.ApplicationWindow;
import com.ono.interpreter.application.uiparts.button.CallConstractorButton;
import com.ono.interpreter.application.uiparts.button.InvokeButton;
import com.ono.interpreter.application.uiparts.button.MakeObjectButton;
import com.ono.interpreter.application.uiparts.button.SelectConstructorButton;
import com.ono.interpreter.application.uiparts.list.ConstructorList;
import com.ono.interpreter.application.uiparts.list.MethodList;
import com.ono.interpreter.application.uiparts.list.ParameterList;
import com.ono.interpreter.application.uiparts.textarea.ConstractorSearchableForm;
import com.ono.interpreter.application.util.GridBagLayoutUtil;


public class ConstructorPanel extends JPanel {
	GridBagLayout		layout	= new GridBagLayout();
	GridBagConstraints	gbc		= new GridBagConstraints();

	private static final int WIDTH = ApplicationWindow.getWIDTH()/3;
	private static final int HEIGTH= ApplicationWindow.getHEIGHT();
	
	public ConstructorPanel() {
		init();
		setComponents();
	}
	
	/**
	 * 初期処理
	 */
	private void init() {
		setLayout(layout);
		setSize(WIDTH, HEIGTH);
	}
	
	/**
	 * コンポーネントの貼り付け
	 */
	private void setComponents() {
		// コンストラクタを指定するテキストフィールド
		Component searchableForm = ConstractorSearchableForm.getInstance();
		GridBagLayoutUtil.setGbcLayout(0, 0, gbc, searchableForm, layout, this);
		//入力受付ボタン
		Component constructorButton = new CallConstractorButton(ConstractorSearchableForm.getInstance());
		GridBagLayoutUtil.setGbcLayout(1, 0, gbc, constructorButton, layout, this);
		// 検索結果を表示するリストフィールド
		Component constructorList = ConstructorList.getComponentModel();
		GridBagLayoutUtil.setGbcLayout(0, 1, gbc, constructorList, layout, this);
		// パラメータを入力するダイアログを表示するボタン
		Component setParameterButton = SelectConstructorButton.getInstance();
		GridBagLayoutUtil.setGbcLayout(0, 3, gbc, setParameterButton, layout, this);
		// オブジェクトを生成するボタン
		Component makeObjectButton = new MakeObjectButton();
		GridBagLayoutUtil.setGbcLayout(1, 3, gbc, makeObjectButton, layout, this);
		//生成したオブジェクトのメソッド一覧を表示するリスト
		Component methodList = MethodList.getComponentModel();
		GridBagLayoutUtil.setGbcLayout(0, 4, gbc, methodList, layout, this);
		//Invokeボタン
		Component invokeButton = new InvokeButton();
		GridBagLayoutUtil.setGbcLayout(0, 5, gbc, invokeButton, layout, this);
		//パラメータリスト
		Component paraList = ParameterList.getComponentModel();
		GridBagLayoutUtil.setGbcLayout(0, 6, gbc, paraList, layout, this);
	}
}
