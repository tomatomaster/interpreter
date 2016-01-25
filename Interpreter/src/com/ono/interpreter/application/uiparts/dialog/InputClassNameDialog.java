package com.ono.interpreter.application.uiparts.dialog;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.ono.interpreter.application.uiparts.textarea.ObjectNameTextField;

/**
 * 
 * @author ono
 *
 */
public class InputClassNameDialog extends JDialog {

  final private static String TITLE = "Input ClassName";
  final private static int WIDTH = 400;
  final private static int HEIGHT = 100;
  final private static int def_x = 400;
  final private static int def_f = 400;


  public InputClassNameDialog() {
    super(new JFrame(), TITLE);
    init();
  }

  /**
   * initialize setting
   */
  private void init() {

    setBounds(def_x, def_f, WIDTH, HEIGHT);
    setLayout(new BorderLayout());
    add(new JLabel("作成するクラス名を入力して下さい。", JLabel.CENTER), BorderLayout.CENTER);
    add(ObjectNameTextField.getInstance(), BorderLayout.PAGE_END);
    addListener();
  }

  /**
   * set an Action on windowClosing
   */
  private void addListener() {
    // Close Dialog when press x button
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        dispose();
      }
    });
  }
}
