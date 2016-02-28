package com.ono.interpreter.application.uiparts.textarea;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Constructor;
import java.util.Objects;

import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.ono.interpreter.application.frame.ConstructorFrame;
import com.ono.interpreter.application.uiparts.dialog.ExceptionDialog;
import com.ono.interpreter.application.uiparts.list.ConstructorList;
import com.ono.interpreter.service.ConstructorService;

public class ObjectNameField extends JTextField {

  private final static int WIDTH = 20;
  private final static String DEFAULT_INPUT = "instanceName";
  private static String objectName;
  
  public ObjectNameField() {
    super(DEFAULT_INPUT, WIDTH);
  }
  
  /**
   * テキストフィールドにセットされたオブジェクト名を取得する。
   * @return
   */
  public String getObjectName() {
    return getText();
  }
}
