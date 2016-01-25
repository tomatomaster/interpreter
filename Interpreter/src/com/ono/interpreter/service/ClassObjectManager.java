package com.ono.interpreter.service;

import java.lang.reflect.Field;

import com.ono.interpreter.application.uiparts.dialog.ExceptionDialog;

/**
 * クラスオブジェクトを編集するクラス
 * 
 * @author ono
 *
 */
public class ClassObjectManager {

  Object classObject;

  public ClassObjectManager() {}

  public ClassObjectManager(String classObjectName) {
    ObjectPool objPool = ObjectPool.getInstance();
    classObject = objPool.getObject(classObjectName);
  }
  
  /**
   * コンストラクタで指定したオブジェクトのフィールドの値を書き換える
   * @param field
   * @param value
   * @return
   */
  public boolean changeFieldValue(Field field, Object value) {
    field.setAccessible(true);
    try {
      field.set(classObject, value);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      new ExceptionDialog(e.getMessage());
      return false;
    }
    return true;
  }
}
