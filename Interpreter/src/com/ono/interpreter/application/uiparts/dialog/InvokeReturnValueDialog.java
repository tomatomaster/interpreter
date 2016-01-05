package com.ono.interpreter.application.uiparts.dialog;

import java.awt.Label;



import javax.swing.JDialog;
import javax.swing.JFrame;

public class InvokeReturnValueDialog extends JDialog {
	private static String	TITLE	= "RETURN VALUE";
	private static int		WIDTH	= 400;
	private static int		HEIGHT	= 100;

	public InvokeReturnValueDialog(Object result) {
		super(new JFrame(), TITLE);
		final int default_x = 400;
		final int default_y = 300;
		setBounds(default_x, default_y, WIDTH, HEIGHT);
		Label messageLabel = new Label(result.toString());

		add(messageLabel);
		setVisible(true);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
