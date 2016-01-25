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

public class ArrayObjectNameTextField extends JTextField {

  private static ArrayObjectNameTextField instance = null;
  private final static int WIDTH = 20;
  private final static String DEFAULT_INPUT = "objectName";

  // Singleton
  public static ArrayObjectNameTextField getInstance() {
    if (instance == null) {
      instance = new ArrayObjectNameTextField();
    }
    return instance;
  }

  /**
   * Enterボタンが押されたタイミングで、コンストラクタ生成パネルを呼ぶ。
   */
  private ArrayObjectNameTextField() {
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
          //1.ユーザーが入力したクラス名を取得 
          String className = getText();
          //2.指定されたクラス名の持つコンストラクタ一覧を表示する
          getConstractor(className);
          new ConstructorFrame().setVisible(true);
        }
      }
    });
  }
  
  private void getConstractor(String className) {
    Objects.requireNonNull(className);
    Constructor<?>[] constructors = null;
    try {
        //コンストラクタの取得
        constructors = ConstructorService.getConstractor(className);
        //取得したコンストラクタを表示する
        ConstructorList.getInstance().setList(constructors);
    } catch (ClassNotFoundException e) {
        new ExceptionDialog(e.toString());
    }
  }
}
