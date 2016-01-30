package com.ono.interpreter.application.uiparts.menu.menubar;

import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.ono.interpreter.application.uiparts.dialog.InputClassNameDialog;
import com.ono.interpreter.application.uiparts.dialog.MakeArrayObjectDialog;
import com.ono.interpreter.application.uiparts.menu.SettingsMenuItem;

class MyMenu extends JMenu implements ActionListener {

	private final static String MENU_NAME = "MENU";
	private final static List<JMenuItem> menuItems = new ArrayList<>();
	
	{
		menuItems.add(new SettingsMenuItem("MakeObject", new InputClassNameDialog()));
		menuItems.add(new SettingsMenuItem("MakeArray", new MakeArrayObjectDialog()));
	}
	
	public MyMenu() {
		super(MENU_NAME);
		for(final JMenuItem menuItem : menuItems) {
			add(menuItem);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
