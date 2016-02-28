package com.ono.interpreter.application.uiparts.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeListener;

import com.ono.interpreter.service.ObjectFactoryService;
import com.ono.interpreter.service.ObjectPool;

import lombok.Data;
import lombok.Setter;

public class MakeArrayObjectDialog extends JDialog {

  final private static String TITLE = "Make Array Object";
  final private static int WIDTH = 400;
  final private static int HEIGHT = 200;
  final private static int x = 400;
  final private static int y = 400;

  public MakeArrayObjectDialog() {
    super(new JFrame(), TITLE);
    init();
  }

  private void init() {
    setBounds(x, y, WIDTH, HEIGHT);
    setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

    ClassNameTextFiled classNameField = new ClassNameTextFiled();
    ObjectNameTextFiled objectNameField = new ObjectNameTextFiled();
    JSpinner arraySizeSpinner =
        new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE - 8, 1));
    MakeArrayObjectButton button = new MakeArrayObjectButton();

    button.setClassNameField(classNameField);
    button.setArraySizeSpinner(arraySizeSpinner);
    button.setObjectNameField(objectNameField);

    add(classNameField);
    add(arraySizeSpinner);
    add(objectNameField);
    add(button);
  }

  /**
   * 配列型のオブジェクトを作成するクラス名を指定するフィールド
   * 
   * @author ono
   *
   */
  private class ClassNameTextFiled extends JTextField {
    final private static String DEFAULT_INPUT = "java.lang.String";
    final private static int WIDTH = 20;

    public ClassNameTextFiled() {
      super(DEFAULT_INPUT, WIDTH);
      setBorder(new EtchedBorder(EtchedBorder.RAISED));
    }
  }

  /**
   * 配列型のオブジェクトを作成するクラス名を指定するフィールド
   * 
   * @author ono
   *
   */
  private class ObjectNameTextFiled extends JTextField {
    final private static String DEFAULT_INPUT = "args";
    final private static int WIDTH = 20;

    public ObjectNameTextFiled() {
      super(DEFAULT_INPUT, WIDTH);
      setBorder(new EtchedBorder(EtchedBorder.RAISED));
    }
  }

  /**
   * 配列オブジェクト生成を実行するボタン
   * 
   * @author ono
   *
   */
  private class MakeArrayObjectButton extends JButton {
    final private static String NAME = "Make Array Object";
    @Setter
    JTextField classNameField;
    @Setter
    JTextField objectNameField;
    @Setter
    JSpinner arraySizeSpinner;

    public MakeArrayObjectButton() {
      super(NAME);
      addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
            String className = classNameField.getText();
            String objectName = objectNameField.getText();
            int arraySize = (int) arraySizeSpinner.getValue();
            Object arrayInstance = 
                ObjectFactoryService.makeArrayObject(Class.forName(className), arraySize);
            ObjectPool.getInstance().setObject(arrayInstance, objectName);
          } catch (Exception e1) {
            new ExceptionDialog(e1.toString());
          }
        }
      });
    }

  }
}
