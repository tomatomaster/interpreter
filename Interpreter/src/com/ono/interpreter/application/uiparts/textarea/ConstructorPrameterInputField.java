package com.ono.interpreter.application.uiparts.textarea;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JTextField;

import com.ono.interpreter.application.uiparts.dialog.ExceptionDialog;
import com.ono.interpreter.service.ObjectPool;

/**
 * TextFieldに入力された文字列をパースしてオブジェクトを生成する。
 * EX)[String aa,int 12,Instance String]と入力した場合
 * Object[] = {"aa", 12, String型のインスタンス} が生成される。
 * @author ono
 *
 */
public class ConstructorPrameterInputField extends JTextField {

  private final static String DEFAULT_INPUT = "int 3, String test, Instance ObjectName";
  private final static int WIDTH = 20;

  public ConstructorPrameterInputField() {
    super(DEFAULT_INPUT , WIDTH);
  }
  
  /**
   * TextFiledに入力されたテキストを基にObjectの配列を生成し呼び出し側に返す。
   * @return
   */
  public Object[] getArguments() {
    List<Object> objects = new ArrayList<Object>();
    for(final String parameter : getParameters()) {
      objects.add(convertValue(parameter));
    }
    return objects.toArray();
  }
  
  /**
   * テキストフィールドないを','で分割してリストで返す。 ex) yamada, hisasi -> {"yamada", hisasi"}
   * 
   * @param textField
   * @return
   */
  public List<String> getParameters() {
    String inputText = this.getText();
    System.out.println("Input:" + inputText);
    List<String> list = new ArrayList<>();

    if (inputText == null)
      return list;

    StringTokenizer token = new StringTokenizer(inputText, ",");
    while (token.hasMoreTokens()) {
      list.add(token.nextToken());
    }
    System.out.println("after tokenized" + list);
    return list;
  }

  /**
   * 分割されたTokenを元にObjectを生成する。 String test -> type = "String" & value = "test"
   * 
   * @param valueWithType
   * @return
   */
  private Object convertValue(String valueWithType) {
    String[] token = valueWithType.split(" ");
    System.out.println("after split to type:" + token[0] + " value:" + token[1]);
    String type;
    String value;
    if (token[0] != null && token[1] != null) {
      type = token[0];
      value = token[1];
    } else {
      throw new IllegalArgumentException();
    }
    return convert(type, value);
  }

  /**
   * String型のvalueを指定されたtypeに変換する。
   * Instance Stringで過去に生成したString型のインスタンスを取得可能
   * @param type
   * @param value
   * @return
   */
  private Object convert(String type, String value) {
    // Integer
    if (type.equals("int")) {
      return Integer.valueOf(value);
    }
    // Double
    else if (type.equals("double")) {
      return Double.valueOf(value);
    }
    // Float
    else if (type.equals("float")) {
      return Float.valueOf(value);
    }
    // Short
    else if (type.equals("short")) {
      return Short.valueOf(value);
    }
    // Char
    else if (type.equals("char")) {
      return (char) Integer.valueOf(value).intValue();
    }
    // Byte
    else if (type.equals("byte")) {
      return Byte.valueOf(value);
    }
    // Boolean
    else if (type.equals("boolean")) {
      return Boolean.valueOf(value);
    }
    // Object
    else if (type.equals("Instance")) {
      return ObjectPool.getInstance().getObject(value);
    } 
    // String
    else if (type.equals("String")) {
      return value;
    }
    // Error
    else {
      new ExceptionDialog("Something Wrong!");
      return null;
    }
  }
}
