package com.ono.interpreter.application.uiparts.dialog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class ExceptionDialog extends JDialog {

	private static String TITLE = "ERROR";
	private static int WIDTH = 400;
	private static int HEIGHT= 100;
	
	public ExceptionDialog(String message) {
		super(new JFrame(), TITLE);
		final int default_x = 400;
		final int default_y = 300;
		setBounds(default_x, default_y, WIDTH, HEIGHT);
		Label messageLabel = new Label("ERROR :" + message);
				
		add(messageLabel);
		setVisible(true);
		
		// Close Dialog when press x button
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
}
