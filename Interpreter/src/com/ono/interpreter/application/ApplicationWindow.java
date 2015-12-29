package com.ono.interpreter.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.text.FlowView;

import com.ono.interpreter.application.panel.ArgumentPanel;
import com.ono.interpreter.application.panel.ConstructorPanel;
import com.ono.interpreter.application.uiparts.button.CallConstractorButton;
import com.ono.interpreter.application.uiparts.button.MakeObjectButton;
import com.ono.interpreter.application.uiparts.list.ConstructorList;
import com.ono.interpreter.application.uiparts.menu.menubar.MyMenuBar;
import com.ono.interpreter.application.uiparts.table.ArgumentsTable;
import com.ono.interpreter.application.uiparts.textarea.ConstractorSearchableForm;
import com.ono.interpreter.application.uiparts.textarea.ParameterInputForm;

public class ApplicationWindow extends JFrame {

	private static int WIDTH = 1000;
	private static int HEIGHT = 1000;
	
	public ApplicationWindow() {
		super();
		init(); //ウィンドウの初期処理はこの中で行う
		setWindowComponent(); //GUIパーツはこの中でセットする
	}
	
	private void init() {
		setBounds(100, 100, WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setWindowComponent() {
		setMenuBar(new MyMenuBar());
		
		//panelの設定
		JPanel panel = new ConstructorPanel();
		//コンストラクタの引数を入力するパネル
		JPanel argumentPanel = ArgumentPanel.getInstance();
				
		Container contentPane = getContentPane();
		contentPane.add(panel, BorderLayout.NORTH);
		contentPane.add(argumentPanel, BorderLayout.CENTER);
	}
}
