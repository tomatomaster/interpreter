package com.ono.interpreter.application.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.PrimitiveType;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ono.interpreter.application.uiparts.NativeParameterSpiner;
import com.ono.interpreter.application.uiparts.button.MakeObjectButton;
import com.ono.interpreter.application.uiparts.button.ObjectTypeArgSettingButton;
import com.ono.interpreter.application.uiparts.list.ConstructorList;
import com.ono.interpreter.application.uiparts.textarea.ConstructorPrameterInputField;
import com.ono.interpreter.application.uiparts.textarea.ObjectNameField;
import com.ono.interpreter.application.util.GridBagLayoutUtil;
import com.ono.interpreter.service.ObjectFactoryService;

public class ArgumentPanel extends JPanel {

  private static final String INSTRUCTION_SENTENCE1 = "生成したインスタンスを利用したい場合はInstance [instanceName]";
  private static final String INSTRUCTION_SENTENCE2 = "配列にオブジェクトを挿入したい場合はインスタンス名を[Array].[Index]と入力する."; 
      

  private static ArgumentPanel instance = null;
  public static Constructor<?> constructor;
  private static GridBagLayout layout = new GridBagLayout();
  private static List<NativeParameterSpiner> spinners;

  GridBagConstraints gbc = new GridBagConstraints();

  public static ArgumentPanel getInstance() {
    if (instance == null) {
      instance = new ArgumentPanel();
    }
    return instance;
  }

  private ArgumentPanel() {
    super();
    setLayout(layout);
  }

  /**
   * パラメータをセットしたいコンストラクターを指定する
   * 
   * @param constructor
   */
  public void setConstructor(Constructor<?> constructor) {
    // 前回addしたコンポーネントをクリアする
    removeAll();
    ArgumentPanel.constructor = constructor;
    // パラメータの型を取得する
    Class<?>[] parameterTypes = constructor.getParameterTypes();



    // コンポーネントの配置位置を指定する
    int componentIndent = 0;
    // パラメータを入力するフィールド
    final ConstructorPrameterInputField argumentField = new ConstructorPrameterInputField();
    argumentField.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ObjectNameField.class) {
          System.out.println("MakeObject!!");
          Object[] args = argumentField.getArguments();
          System.out.println(args);
          ObjectFactoryService.setArgs(args);
        }
      }
    });
    GridBagLayoutUtil.setGbcLayout(1, componentIndent++, gbc, new JLabel(INSTRUCTION_SENTENCE1), layout, this);
    GridBagLayoutUtil.setGbcLayout(1, componentIndent++, gbc, new JLabel(INSTRUCTION_SENTENCE2), layout, this);
    GridBagLayoutUtil.setGbcLayout(1, componentIndent++, gbc, argumentField, layout, this);

    // オブジェクト名を入力するフィールド
    ObjectNameField objectNameField = new ObjectNameField();
    GridBagLayoutUtil.setGbcLayout(1, componentIndent++, gbc, objectNameField, layout, this);

    // オブジェクト生成ボタン
    JButton setPrameterButton = new MakeObjectButton(argumentField, objectNameField);
    GridBagLayoutUtil.setGbcLayout(1, componentIndent, gbc, setPrameterButton, layout, this);
  }
}
