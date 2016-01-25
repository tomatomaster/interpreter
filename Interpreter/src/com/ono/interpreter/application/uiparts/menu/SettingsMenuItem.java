package com.ono.interpreter.application.uiparts.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenuItem;

import com.ono.interpreter.application.uiparts.dialog.InputClassNameDialog;
import com.ono.interpreter.application.uiparts.dialog.SettingDialog;

public class SettingsMenuItem extends JMenuItem {

	/**
	 * Show Setting Dialog
	 */
	public SettingsMenuItem(final String menuName, final JDialog dialog) {
		super(menuName);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == menuName) {
					dialog.setVisible(true);		
				}
			}
		});
	}
	
}
