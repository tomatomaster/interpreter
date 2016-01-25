package com.ono.interpreter.application.uiparts.menu.menubar;

import java.awt.Menu;
import java.awt.MenuBar;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * メニューバーそのものを管理するクラス
 * @author ono
 *
 */
public class MyMenuBar extends JMenuBar {
	//メニューバーに表示されるメニューのリスト
	List<JMenu> menuList = new ArrayList<JMenu>();

	//メニューバーに追加するメニューはここに書く
	{
		menuList.add(new MyMenu());
	}

	public MyMenuBar() {
		super();
		for(final JMenu menu: menuList) {
			add(menu);
		}
	}
}
