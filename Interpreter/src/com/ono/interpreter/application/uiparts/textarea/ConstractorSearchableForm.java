package com.ono.interpreter.application.uiparts.textarea;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class ConstractorSearchableForm extends JTextField {

	private static final long	serialVersionUID	= 1L;
	private static ConstractorSearchableForm constractorSearchForm = null;
	//サイズは文字数に一致
	private final static int WIDTH = 15;
	private final static String INIT_INPUT = "java.lang.String";
	
	//Singleton
	public static ConstractorSearchableForm getInstance() {
		if(constractorSearchForm == null) {
			constractorSearchForm = new ConstractorSearchableForm();
		}
		return constractorSearchForm;
	}
	
	private ConstractorSearchableForm() {
		super(INIT_INPUT, WIDTH);
		setBorder(new EtchedBorder(EtchedBorder.RAISED)); //枠線の追加
	}
}
