package com.ono.interpreter.application.frame;

import javax.swing.JFrame;

import com.ono.interpreter.application.uiparts.list.ConstructorList;

public class ConstructorFrame extends JFrame {

  private static final int WIDTH = 500;
  private static final int HEIGHT = 400;

  public ConstructorFrame() {
    init();
  }

  void init() {
    setBounds(400, 400, WIDTH, HEIGHT);
    addComponets();
  }

  private void addComponets() {
    add(ConstructorList.getComponentModel());
  }
}
