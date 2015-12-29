package com.ono.interpreter.application.panel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;


import com.ono.interpreter.application.uiparts.button.CallConstractorButton;
import com.ono.interpreter.application.uiparts.button.MakeObjectButton;
import com.ono.interpreter.application.uiparts.list.ConstructorList;
import com.ono.interpreter.application.uiparts.textarea.ConstractorSearchableForm;


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
		setGbcLayout(0, 0, gbc, searchableForm, layout);
		//入力受付ボタン
		Component constructorButton = new CallConstractorButton(
				ConstractorSearchableForm.getInstance());
		setGbcLayout(1, 0, gbc, constructorButton, layout);
		// 検索結果を表示するリストフィールド
		Component constructorList = ConstructorList.getInstance();
		setGbcLayout(0, 1, gbc, constructorList, layout);
		// オブジェクトを作成するボタン
		Component makeButton = MakeObjectButton.getInstance();
		setGbcLayout(0, 3, gbc, makeButton, layout);
	}

	private void setGbcLayout(int x, int y, GridBagConstraints gbc,
			Component comp, GridBagLayout gbl) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbl.setConstraints(comp, gbc);
		add(comp);
	}
}
