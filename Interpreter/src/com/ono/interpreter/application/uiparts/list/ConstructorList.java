package com.ono.interpreter.application.uiparts.list;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.util.Objects;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.ono.interpreter.application.panel.ArgumentPanel;
import com.ono.interpreter.application.uiparts.dialog.ParameterInputDialog;
import com.ono.interpreter.service.ObjectFactoryService;


public class ConstructorList extends JList<Object> {

	private static final long	serialVersionUID	= 1L;
	private static ConstructorList instance = null;

	//Dimensionの値
	private static final int WIDTH = 650;
	private static final int HEIGHT = 200;
	
	//Singleton
	public static ConstructorList getInstance() {
		if(instance == null) {
			instance = new ConstructorList();
		}
		return instance;
	}
	
	public static Component getComponentModel() {
		ConstructorList instance = getInstance();
		JScrollPane scrollPane = new JScrollPane(instance);
		scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		return scrollPane;
	}
	
	private ConstructorList() {
		super();
		//複数のリスト選択を禁止
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//リスト要素を選択した時の挙動
		addMouseListener(new MouseAdapter() {
		  @Override
		  public void mouseClicked(MouseEvent e) {
		    if(e.getClickCount() == 2) {
	           //1.1コンストラクタリストから選択したコンストラクタを取得する
	            Constructor<?> constructor = (Constructor<?>) ConstructorList.getInstance().getList();
	            //1.2コンストラクタの引数を編集するArgumentPanelにコンストラクタをセットする
	            ArgumentPanel.getInstance().setConstructor(constructor);
	            //2オブジェクト生成を行うObjectFactoryServiceにコンストラクタをセット
	            ObjectFactoryService.setConstructor(constructor);
	            //3コンストラクタダイアログを表示する
	            new ParameterInputDialog().setVisible(true);
		    }
		  }
		});
	}
	
	/**
	 * リストに表示するオブジェクトをセットする
	 * @param obj
	 */
	public void setList(Object[] obj) {
		setListData(Objects.requireNonNull(obj));
	}
	
	/**
	 * 選択されているリストを呼び出し側に返す
	 * @return
	 */
	public Object getList() {
		Object selectedValue = Objects.requireNonNull(getSelectedValue());
		return selectedValue;
	}
}
