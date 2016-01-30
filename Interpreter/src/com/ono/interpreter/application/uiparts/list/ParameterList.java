package com.ono.interpreter.application.uiparts.list;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.Objects;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;

import com.ono.interpreter.application.uiparts.dialog.FiledValueModifierDialog;
import com.ono.interpreter.application.uiparts.textarea.ConstructorPrameterInputField;
import com.ono.interpreter.application.util.GridBagLayoutUtil;
import com.ono.interpreter.service.MyModifyField;
import com.ono.interpreter.service.ObjectPool;

/**
 * 今は使用していない
 * 
 * @author ono
 *
 */
public class ParameterList extends JList<Field> {
  private static final long serialVersionUID = 1L;
  private static ParameterList instance = null;
  private static Object clazz = null;

  // Dimensionの値
  private static final int WIDTH = 650;
  private static final int HEIGHT = 200;

  // Singleton
  public static ParameterList getInstance() {
    if (instance == null) {
      instance = new ParameterList();
    }
    return instance;
  }

  public static Component getComponentModel() {
    ParameterList instance = getInstance();
    JScrollPane scrollPane = new JScrollPane(instance);
    scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    return scrollPane;
  }

  public void setClassObject(Object clazz) {
    this.clazz = clazz;
  }
  
  private ParameterList() {
    super();
    // 複数のリスト選択を禁止
    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    // ダブルクリックでパラメータ編集
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          Field selectedFiled = getSelectedValue();
          Objects.requireNonNull(selectedFiled);
          new FiledValueModifierDialog(selectedFiled).setVisible(true);
        }
      }
    });
  }
  
  class ParameterEditDialog extends JDialog {
    Field targetField;
    public ParameterEditDialog(Field field) {
      targetField = field;
      setBounds(20, 10 , 100, 100);
      //Fieldの型を取得する
      Class<?> type = targetField.getType();
      if(type.isPrimitive()) {
        JSpinner valueSpnipper = new JSpinner();
        valueSpnipper.setPreferredSize(new Dimension(80, 20));
        GridBagLayoutUtil.setGbcLayout(0, 0, new GridBagConstraints(), valueSpnipper, new GridBagLayout(), this);
      }
      addKeyListener(new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyReleased(KeyEvent e) {}
        @Override
        public void keyPressed(KeyEvent e) {
          if(e.equals(KeyEvent.VK_ENTER)) {
            
          }
        }
      });
    }
  }
}
