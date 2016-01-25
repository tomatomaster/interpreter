package com.ono.interpreter.service;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.ono.interpreter.application.uiparts.dialog.ExceptionDialog;

public class ConstructorService {

  /**
   * パラメータで指定したコンストラクタークラスを返す。
   * @param className
   * @return
   * @throws ClassNotFoundException
   */
  public static Constructor<?>[] getConstractor(String className) throws ClassNotFoundException {
    Class<?> clazz = null;
    clazz = Class.forName(className);

    Constructor<?>[] constractors = clazz.getDeclaredConstructors();
    return constractors;
  }

  public static void showConstractors(Constructor<?>[] constructors) {
    System.out.println("SearchConstructor");
    for (final Constructor constructor : constructors) {
      if (!constructor.isAccessible()) {
        constructor.setAccessible(true);
      }
      String constName = constructor.toGenericString();
      System.out.println(constName);
    }
  }
}
