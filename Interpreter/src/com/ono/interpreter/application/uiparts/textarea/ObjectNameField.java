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

  private static ObjectNameField instance = null;
  private final static int WIDTH = 20;
  private final static String DEFAULT_INPUT = "objectName";
  private static String objectName;

  // Singleton
  public static ObjectNameField getInstance() {
    if (instance == null) {
      instance = new ObjectNameField();
    }
    return instance;
  }
  
  /**
   * テキストフィールドにセットされたオブジェクト名を取得する。
   * @return
   */
  public static String getObjectName() {
    return objectName;
  }

  /**
   * Enterボタンが押されたタイミングで、コンストラクタ生成パネルを呼ぶ。
   */
  private ObjectNameField() {
    super(DEFAULT_INPUT, WIDTH);
    setBorder(new EtchedBorder(EtchedBorder.RAISED));
    addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {}

      @Override
      public void keyReleased(KeyEvent e) {}

      @Override
      public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER) {
          objectName = getText();
        }
      }
    });
  }
}
