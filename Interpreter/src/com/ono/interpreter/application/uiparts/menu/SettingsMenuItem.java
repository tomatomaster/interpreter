package com.ono.interpreter.application.uiparts.menu;

import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ono.interpreter.application.uiparts.dialog.SettingDialog;

public class SettingsMenuItem extends MenuItem {

	private final static String MENU_NAME = "Setting";

	/**
	 * Show Setting Dialog
	 */
	public SettingsMenuItem() {
		super(MENU_NAME);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == MENU_NAME) {
					//MENUボタンが押下されたらダイアログを表示させる
					new SettingDialog().setVisible(true);		
				}
			}
		});
	}
	
}
