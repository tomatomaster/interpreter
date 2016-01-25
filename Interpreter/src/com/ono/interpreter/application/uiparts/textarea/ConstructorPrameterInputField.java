package com.ono.interpreter.application.uiparts.textarea;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JTextField;

import com.ono.interpreter.application.uiparts.dialog.ExceptionDialog;
import com.ono.interpreter.service.ObjectPool;

public class ConstructorPrameterInputField extends JTextField {


  /**
   * テキストフィールドないを','で分割してリストで返す。 ex) yamada, hisasi -> {"yamada", hisasi"}
   * 
   * @param textField
   * @return
   */
  private List<String> getParameters(JTextField textField) {
    String inputText = textField.getText();
    List<String> list = new ArrayList<>();

    if (inputText == null)
      return list;

    StringTokenizer token = new StringTokenizer(inputText, ",");
    while (token.hasMoreTokens()) {
      list.add(token.nextToken());
    }
    return list;
  }

  /**
   * 分割されたTokenを元にObjectを生成する。
   * String test -> type = "String" & value = "test"
   * @param valueWithType
   * @return
   */
  Object convertValue(String valueWithType) {
    StringTokenizer token = new StringTokenizer(valueWithType, " ");
    String type;
    String value;
    type = token.nextToken();
    value = token.nextToken();
    return convert(type, value);
  }

  /**
   * String型のvalueを指定されたtypeに変換する。
   * @param type
   * @param value
   * @return
   */
  private Object convert(String type, String value) {
    //Integer
    if(type.equals("int")) {
      return Integer.valueOf(value);
    }
    //Double
    else if(type.equals("double")) {
      return Double.valueOf(value);
    }
    //Float
    else if(type.equals("float")) {
      return Float.valueOf(value);
    }
    //Short
    else if(type.equals("short")) {
      return Short.valueOf(value);
    }
    //Char
    else if(type.equals("char")) {
      return (char) Integer.valueOf(value).intValue();
    }
    //Byte
    else if(type.equals("byte")) {
      return Byte.valueOf(value);
    }
    //Boolean
    else if(type.equals("boolean")) {
      return Boolean.valueOf(value);
    }
    //Object
    else if(type.equals("Instance")) {
      return ObjectPool.getInstance().getObject(value);
    }
    //Error
    else {
      new ExceptionDialog("Something Wrong!");
      return null;
    }
  }



}
