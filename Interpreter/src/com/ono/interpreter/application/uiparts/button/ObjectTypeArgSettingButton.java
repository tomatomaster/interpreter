package com.ono.interpreter.application.uiparts.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;

import javax.swing.Icon;
import javax.swing.JButton;

import com.ono.interpreter.application.panel.ArgumentPanel;

public class ObjectTypeArgSettingButton extends JButton {

  private static String NAME = "NULL";

  public ObjectTypeArgSettingButton(final Constructor<?> constructor) {
    super(NAME);
    addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        ArgumentPanel.getInstance().setConstructor(constructor);
        ArgumentPanel.getInstance().setVisible(true);
      }
    });
  }
}
