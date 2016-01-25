package com.ono.interpreter.application.uiparts;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NativeParameterSpiner extends JSpinner {

  private int argumentIndex;
  private Object value;
  
  public NativeParameterSpiner(int argumentIndex) {
    this.argumentIndex = argumentIndex;
    addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
       value = getValue();
      }
    });
  }
  
  public Object getValue() {
    return value;
  }
  
  public int getIndex() {
    return argumentIndex;
  }
}
