package com.ono.interpreter.application;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.text.FlowView;

import com.ono.interpreter.application.uiparts.menu.menubar.MyMenuBar;

public class ApplicationWindow extends JFrame {

	private static int WIDTH = 600;
	private static int HEIGHT = 600;
	
	public ApplicationWindow() {
		super();
		init(); //ウィンドウの初期処理はこの中で行う
		setWindowComponent(); //GUIパーツはこの中でセットする
	}
	
	private void init() {
		setVisible(true);
		setLayout(new FlowLayout());
		setBounds(100, 100, WIDTH, HEIGHT);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void setWindowComponent() {
		setMenuBar(new MyMenuBar());
	}
	
	
	
}
