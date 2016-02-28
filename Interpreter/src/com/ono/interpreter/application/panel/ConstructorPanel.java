package com.ono.interpreter.application.panel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.ono.interpreter.application.ApplicationWindow;
import com.ono.interpreter.application.uiparts.button.CallConstractorButton;
import com.ono.interpreter.application.uiparts.button.InvokeButton;
import com.ono.interpreter.application.uiparts.button.MakeObjectButton;
import com.ono.interpreter.application.uiparts.button.SelectConstructorButton;
import com.ono.interpreter.application.uiparts.list.ConstructorList;
import com.ono.interpreter.application.uiparts.list.MethodList;
import com.ono.interpreter.application.uiparts.list.ParameterList;
import com.ono.interpreter.application.uiparts.textarea.ConstractorSearchableForm;
import com.ono.interpreter.application.uiparts.textarea.ConstructorPrameterInputField;
import com.ono.interpreter.application.uiparts.textarea.ObjectNameField;
import com.ono.interpreter.application.util.GridBagLayoutUtil;


public class ConstructorPanel extends JPanel {
  GridBagLayout layout = new GridBagLayout();
  GridBagConstraints gbc = new GridBagConstraints();

  private static final int WIDTH = ApplicationWindow.getWIDTH() / 3;
  private static final int HEIGTH = ApplicationWindow.getHEIGHT();

  public ConstructorPanel() {
    init();
    setComponents();
  }

  /**
   * 初期処理
   */
  private void init() {
    setLayout(layout);
    setSize(WIDTH, HEIGTH);
  }

  /**
   * コンポーネントの貼り付け
   */
  private void setComponents() {
    // 生成したオブジェクトのメソッド一覧を表示するリスト
    GridBagLayoutUtil.setGbcLayout(0, 1, gbc, new JLabel("Methods"), layout, this);
    Component methodList = MethodList.getComponentModel();
    GridBagLayoutUtil.setGbcLayout(0, 2, gbc, methodList, layout, this);
    // パラメータリスト
    GridBagLayoutUtil.setGbcLayout(0, 3, gbc, new JLabel("Fields"), layout, this);
    Component paraList = ParameterList.getComponentModel();
    GridBagLayoutUtil.setGbcLayout(0, 4, gbc, paraList, layout, this);
  }
  
  
}
