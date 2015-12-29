package com.ono.interpreter.application.uiparts.textarea;

import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class ParameterInputForm extends JTextField {
	
	private static ParameterInputForm instance = null;
	private final static int WIDTH = 20;
	private final static String INIT_INPUT = "put parameter(s) here";
	
	//Singleton
	public static ParameterInputForm getInstance() {
		if(instance == null) {
			instance = new ParameterInputForm();
		}
		return instance;
	}
	
	private ParameterInputForm() {
		super(INIT_INPUT, WIDTH);
		setBorder(new EtchedBorder(EtchedBorder.RAISED)); //枠線の追加
	}
}
