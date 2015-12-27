package com.ono.interpreter.application.uiparts.menu.menubar;

import java.awt.Menu;
import java.awt.MenuBar;
import java.util.ArrayList;
import java.util.List;

/**
 * メニューバーそのものを管理するクラス
 * @author ono
 *
 */
public class MyMenuBar extends MenuBar {
	//メニューバーに表示されるメニューのリスト
	List<Menu> menuList = new ArrayList<Menu>();

	//メニューバーに追加するメニューはここに書く
	{
		menuList.add(new MyMenu());
	}

	public MyMenuBar() {
		super();
		for(final Menu menu: menuList) {
			add(menu);
		}
	}
}
