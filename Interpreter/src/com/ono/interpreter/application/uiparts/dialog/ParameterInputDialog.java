package com.ono.interpreter.application.uiparts.dialog;

import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.ono.interpreter.application.panel.ArgumentPanel;


public class ParameterInputDialog extends JDialog {

	private static final long	serialVersionUID	= 1L;
	private static String TITLE = "PrameterInput";
	private static int WIDTH = 400;
	private static int HEIGHT= 250;
	
	public ParameterInputDialog() {
		super(new JFrame(), TITLE);
		final int default_x = 400;
		final int default_y = 300;
		setBounds(default_x, default_y, WIDTH, HEIGHT);
		//引数を入力するArgumentPanelをadd
		add(ArgumentPanel.getInstance());
		
		// Close Dialog when press x button
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
}
