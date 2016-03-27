package com.ono.interpreter.application.uiparts.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.swing.JButton;

import com.ono.interpreter.application.uiparts.dialog.ExceptionDialog;
import com.ono.interpreter.application.uiparts.list.ConstructorList;
import com.ono.interpreter.application.uiparts.list.MethodList;
import com.ono.interpreter.application.uiparts.list.ParameterList;
import com.ono.interpreter.application.uiparts.textarea.ConstructorPrameterInputField;
import com.ono.interpreter.application.uiparts.textarea.ObjectNameField;
import com.ono.interpreter.service.ClassContents;
import com.ono.interpreter.service.ObjectFactoryService;
import com.ono.interpreter.service.ObjectPool;

public class MakeObjectButton extends JButton {

  private static final String NAME = "オブジェクトの生成";

  public MakeObjectButton(final ConstructorPrameterInputField inputfield,
      final ObjectNameField objectNameField) {
    super(NAME);
    addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          // 0. ConstructorPrameterInputFieldから引数を取得
          Object[] args = inputfield.getArguments();
          ObjectFactoryService.setArgs(args);

          // 1. コンストラクターリストからコンストラクターを取得
          Constructor<?> constructor =
              (Constructor<?>) ConstructorList.getInstance().getSelectedValue();
          // 1.1取得したコンストラクターをオブジェクトファクトリーにセット
          ObjectFactoryService.setConstructor(constructor);

          // 2オブジェクトの生成
          Object instance;
          String objectName = objectNameField.getObjectName();
          // 2.1 arrayObjectかチェック
          boolean isArray = false;
          try {
            isArray = checkObjectState(objectName);
          } catch (IllegalStateException e2) {
            new ExceptionDialog(e2.getMessage());
            return;
          }
          if (isArray) {
            String[] nameAndIndex = objectName.split("\\.");
            String name = nameAndIndex[0];
            int index = Integer.valueOf(nameAndIndex[1]);
            // 対象のarrayObjectを取得して、生成したインスタンスをセットする
            instance = ObjectFactoryService.makeObject();
            Object arrayObject = ObjectPool.getInstance().getObject(name);
            if (arrayObject == null)
              throw new NoSuchElementException();
            Array.set(arrayObject, index, instance);
            ObjectPool.getInstance().setObject(instance, objectName);
          } else {
            instance = ObjectFactoryService.makeObject();
            // 2.1オブジェクトプールに生成したオブジェクトをセット
            ObjectPool.getInstance().setObject(instance, objectName);
          }

          // 3.生成したオブジェクトのメソッドを表示
          Method[] instanceMethods = ClassContents.getMethods(instance.getClass());
          MethodList.getInstance().setListData(instanceMethods);

          // 4.生成したオブジェクトのフィールドを表示
          Field[] instanceFields = ClassContents.getFields(instance.getClass());
          ParameterList.getInstance().setListData(instanceFields);

          // 5.InvokeButtonに生成したオブジェクトを渡す
          InvokeButton.setObject(instance);
        } catch (Exception e1) {
          String errorMsg;
          if (e1.getCause() != null) {
            errorMsg = e1.getCause() + e1.getMessage();
          } else {
            errorMsg = e1.getMessage();
          }
          new ExceptionDialog(errorMsg);
          e1.printStackTrace();
        }
      }

      /**
       * If array instance named test[5] is in ObjectPool, test.2
       * 
       * @param objectName
       * @return
       */
      private boolean checkObjectState(String objectName) {
        String[] nameAndIndex = objectName.split("\\.");
        if (nameAndIndex.length != 2) {
          return false;
        }
        String name = nameAndIndex[0];
        int index = Integer.valueOf(nameAndIndex[1]);
        Object arrayObject = ObjectPool.getInstance().getObject(name);
        
        if(arrayObject == null) {
          throw new IllegalStateException("No such array object");
        }  else if (index >= Array.getLength(arrayObject)) {
          throw new IllegalStateException("Array out of bounds");
        } else if (index < Array.getLength(arrayObject)) {
          return true;
        }
        return false;
      }
    });
  }
}
