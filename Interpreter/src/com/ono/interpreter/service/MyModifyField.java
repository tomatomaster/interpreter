package com.ono.interpreter.service;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Objects;

import com.ono.interpreter.application.uiparts.dialog.ExceptionDialog;

public class MyModifyField {

  
  public static void changeFieldValue(Field filed, Object value) {
    
    Objects.requireNonNull(filed, "Selected Filed is null");
    
    if(!filed.isAccessible()) {
      filed.setAccessible(true);
    }
    
    Object targetInstance = ObjectFactoryService.currentObj;
    System.out.println("Filed: " + filed + " Value: " + value + " Instance: " + targetInstance);
    Objects.requireNonNull(targetInstance);
    try {
      filed.set(targetInstance, value);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      new ExceptionDialog(e.getMessage());
      e.printStackTrace();
    }
  }
  
}
