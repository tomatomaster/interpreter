package com.ono.interpreter.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JWindow;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.text.FlowView;

import com.ono.interpreter.application.panel.ArgumentPanel;
import com.ono.interpreter.application.panel.ConstructorPanel;
import com.ono.interpreter.application.uiparts.menu.menubar.MyMenuBar;
import com.ono.interpreter.application.uiparts.tree.ObjectTree;
import com.ono.interpreter.application.util.GridBagLayoutUtil;


public class ApplicationWindow extends JFrame {

	private static GridBagConstraints gbc = new GridBagConstraints();
	GridBagLayout		layout	= new GridBagLayout();
	private static int WIDTH = 1000;
	private static int HEIGHT = 1000;
	
	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public ApplicationWindow() {
		super();
		init(); //ウィンドウの初期処理はこの中で行う
		setWindowComponent(); //GUIパーツはこの中でセットする
	}
	
	private void init() {
		setBounds(100, 100, WIDTH, HEIGHT);
		setLayout(layout);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setWindowComponent() {
		setJMenuBar(new MyMenuBar());
		
		//Frameにコンポーネントを貼り付ける
		//ContentPaneはコンテンツを貼り付ける板と考えていい
		Container contentPane = getContentPane();
		//オブジェクトツリーの表示
		Component objectTree = new ObjectTree().getComponentModel();
		GridBagLayoutUtil.setGbcLayout(0, 0, gbc, objectTree, layout, contentPane);
		//コンストラクタパネルの貼り付け
		Component constructorPanel = new ConstructorPanel();
		GridBagLayoutUtil.setGbcLayout(1, 0, gbc, constructorPanel, layout, contentPane);
	}
}
