package com.ono.interpreter.application.uiparts.dialog;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Field;

import javax.swing.BoxLayout;
import javax.swing.JDialog;

import com.ono.interpreter.application.uiparts.textarea.ConstructorPrameterInputField;
import com.ono.interpreter.service.MyModifyField;
import com.ono.interpreter.service.ObjectFactoryService;

public class FiledValueModifierDialog extends JDialog {

  Field field;

  public FiledValueModifierDialog(Field field) {
    this.field = field;
    if (!field.isAccessible()) {
      field.setAccessible(true);
    }
    setBounds(400, 400, 380, 60);
    initComponents();
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  private void initComponents() {
    try {
      String defaultValue = field.get(ObjectFactoryService.currentObj).toString();
      final ConstructorPrameterInputField inputField = new ConstructorPrameterInputField();
      inputField.setText(defaultValue);
      // Enter keyが押されたタイミングでフィールドの値をオブジェクトに書き込む
      inputField.addKeyListener(new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
          if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Object[] args = inputField.getArguments();
            if (args.length != 1) { // フィールドの値だけを編集するので、１以上の長さは受け付けない。
              new ExceptionDialog("Error");
            }
            MyModifyField.changeFieldValue(field, args[0]);
            FiledValueModifierDialog.this.dispose();
          }
        }

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
          if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Object[] args = inputField.getArguments();
            if (args.length != 1) { // フィールドの値だけを編集するので、１以上の長さは受け付けない。
              new ExceptionDialog("Error");
            }
            MyModifyField.changeFieldValue(field, args[0]);
            FiledValueModifierDialog.this.dispose();
          }
        }
      });
      add(inputField);
    } catch (Exception e) {
      new ExceptionDialog(e.getMessage());
    }
  }
}
