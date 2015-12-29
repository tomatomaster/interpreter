package com.ono.interpreter.application.panel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.ono.interpreter.application.uiparts.button.CallConstractorButton;
import com.ono.interpreter.application.uiparts.button.SelectConstructorButton;
import com.ono.interpreter.application.uiparts.list.ConstructorList;
import com.ono.interpreter.application.uiparts.textarea.ConstractorSearchableForm;
import com.ono.interpreter.application.util.GridBagLayoutUtil;


public class ConstructorPanel extends JPanel {
	GridBagLayout		layout	= new GridBagLayout();
	GridBagConstraints	gbc		= new GridBagConstraints();

	public ConstructorPanel() {
		init();
		setComponents();
	}
	
	/**
	 * 初期処理
	 */
	private void init() {
		setLayout(layout);
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
		Component constructorList = ConstructorList.getInstance();
		GridBagLayoutUtil.setGbcLayout(0, 1, gbc, constructorList, layout, this);
		// オブジェクトを作成するボタン
		Component makeButton = SelectConstructorButton.getInstance();
		GridBagLayoutUtil.setGbcLayout(0, 3, gbc, makeButton, layout, this);
	}
}
