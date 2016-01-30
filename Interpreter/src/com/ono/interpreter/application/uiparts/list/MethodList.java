package com.ono.interpreter.application.uiparts.list;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.ono.interpreter.application.uiparts.button.InvokeButton;
import com.ono.interpreter.application.uiparts.dialog.FiledValueModifierDialog;
import com.ono.interpreter.application.uiparts.textarea.ConstructorPrameterInputField;

public class MethodList extends JList<Method> {
  private static final long serialVersionUID = 1L;
  private static MethodList instance = null;

  // Dimensionの値
  private static final int WIDTH = 650;
  private static final int HEIGHT = 200;

  // Singleton
  public static MethodList getInstance() {
    if (instance == null) {
      instance = new MethodList();
    }
    return instance;
  }

  public static Component getComponentModel() {
    MethodList instance = getInstance();
    JScrollPane scrollPane = new JScrollPane(instance);
    scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    return scrollPane;
  }

  private MethodList() {
    super();
    // 複数のリスト選択を禁止
    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    // ダブルクリックでパラメータ編集
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          Method selectedMethod = getSelectedValue();
          Objects.requireNonNull(selectedMethod);
          new InvokeMethodDialog(selectedMethod).setVisible(true);
        }
      }
    });
  }

  /**
   * リストに表示するオブジェクトをセットする
   * 
   * @param obj
   */
  public void setList(Method[] obj) {
    setListData(Objects.requireNonNull(obj));
  }

  /**
   * 
   * @author ono
   *
   */
  private class InvokeMethodDialog extends JDialog {
    Method method;
    final private static int WIDTH = 400;
    final private static int HEIGHT = 400;
    final private static int X = 400;
    final private static int Y = 400;

    public InvokeMethodDialog(Method method) {
      this.method = method;
      init();
    }

    private void init() {
      setBounds(X, Y, WIDTH, HEIGHT);
      ConstructorPrameterInputField paramField = new ConstructorPrameterInputField();
      InvokeButton invokeButton = new InvokeButton();
      invokeButton.setParameterField(paramField);
      setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
      add(paramField);
      add(invokeButton);
    }
  }
}
