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
import com.ono.interpreter.application.uiparts.textarea.ObjectNameField;
import com.ono.interpreter.application.util.GridBagLayoutUtil;
import com.ono.interpreter.service.ObjectFactoryService;

public class ArgumentPanel extends JPanel {

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
    spinners = new ArrayList<>();
    // コンポーネントの配置位置を指定する
    int componentIndent = 0;
    for (int i = 0; i < parameterTypes.length; i++) {
      Class<?> type = parameterTypes[i];

      // ラベルの貼り付け
      JLabel typeName = new JLabel(type.getSimpleName());
      GridBagLayoutUtil.setGbcLayout(0, componentIndent, gbc, typeName, layout, this);

      // Primitive型に対してはスピナーで入力受付
      if (type.isPrimitive()) {
        final NativeParameterSpiner valueSpnipper = new NativeParameterSpiner(i);
        spinners.add(valueSpnipper);
        // TODO 値の上限値下限値を設定する
        valueSpnipper.setPreferredSize(new Dimension(80, 20));
        GridBagLayoutUtil.setGbcLayout(1, componentIndent, gbc, valueSpnipper, layout, this);
      } else {
        // テキストフィールド表示してオブジェクト名からオブジェクトとってくるようにする？
        GridBagLayoutUtil.setGbcLayout(1, componentIndent, gbc, new JButton("test"), layout, this);
      }
      componentIndent++;
    }

    JTextField objectNameField = ObjectNameField.getInstance();
    GridBagLayoutUtil.setGbcLayout(1, componentIndent++, gbc, objectNameField, layout, this);
    JButton setPrameterButton = new MakeObjectButton();
    setPrameterButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (NativeParameterSpiner spinner : spinners) {
          spinner.getValue();
          spinner.getIndex();
        }
      }
    });
    GridBagLayoutUtil.setGbcLayout(1, componentIndent, gbc, setPrameterButton, layout, this);
  }
}
