package com.ono.interpreter.application.panel;

import javax.swing.JTable;

public class ConstructorArgumentTable extends JTable {

  private String[] columnNames = {"TYPE", "VALUE"};
  
  public ConstructorArgumentTable() {
    super(String[][], columnNames);
  }
}
