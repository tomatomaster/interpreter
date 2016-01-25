package com.ono.interpreter.application.uiparts.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ConstructorListButton extends JButton {

  private final static String NAME = "NULL";
  public ConstructorListButton() {
    super(NAME);
    addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {}
      
    });
  }
  
}
