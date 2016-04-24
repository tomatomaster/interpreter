package com.ono.interpreter.application.uiparts.dialog;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import com.ono.interpreter.application.frame.ConstructorFrame;
import com.ono.interpreter.application.uiparts.button.InvokeButton;
import com.ono.interpreter.application.uiparts.list.ConstructorList;
import com.ono.interpreter.application.uiparts.list.MethodList;
import com.ono.interpreter.application.uiparts.list.ParameterList;
import com.ono.interpreter.application.uiparts.textarea.ObjectNameTextField;
import com.ono.interpreter.service.ClassContents;
import com.ono.interpreter.service.ConstructorService;
import com.ono.interpreter.service.ObjectFactoryService;
import com.ono.interpreter.service.ObjectPool;

public class ObjectPoolViewerDialog extends JDialog {

  List<Object> objects = new ArrayList<>();
  ObjectList objectList = new ObjectList();
  final private static String TITLE = "Objects";
  final private static int WIDTH = 300;
  final private static int HEIGHT = 200;
  final private static int x = 400;
  final private static int y = 400;

  public ObjectPoolViewerDialog() {
    super(new JFrame(), TITLE);
    initComponents();
  }

  /**
   * Menuから呼び出される時にオブジェクトを取得
   */
  @Override
  public void setVisible(boolean b) {
    if (Boolean.TRUE.equals(b)) {
      objects.clear();
      // ObjetPool内で管理されているオブジェクトを全て取得する
      Set<String> keySet = ObjectPool.getInstance().getObjectPool().keySet();
      System.out.println("KeySet = " + keySet);
      for (final String key : keySet) {
        Object obj = ObjectPool.getInstance().getObjectPool().get(key);
        System.out.println(obj.getClass().getSimpleName());
        objects.add(obj);
      }
      // ダイアログにコンポーネントをセットする
      objectList.setListData(keySet.toArray());
    }
    super.setVisible(b);
  }

  /**
   * Dialogの設定
   */
  private void initComponents() {
    setBounds(x, y, WIDTH, HEIGHT);
    add(objectList);
  }

  /**
   * Objectを保存しているObjectPoolから取得したオブジェクトのリストを表示するリスト
   * 
   * @author ono
   *
   */
  static class ObjectList extends JList<Object> {

    Class<?> selectedArrayClass;
    
    ObjectList() {
      super();
      // ===========MouseListener===========
      addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          if (e.getClickCount() == 2) {
            String selectedObjectName = (String) getSelectedValue();
            Object selectedObject =
                ObjectPool.getInstance().getObjectPool().get(selectedObjectName);
            if (selectedObject == null) {
              // List要素がNullならばインスタンス生成ダイアログを表示させる
              return;
            } else if (selectedObject.getClass().isArray()) {
              // ArrayViewerの表示
              new ArrayObjectViewer(selectedObject);
            }
            ObjectFactoryService.currentObj = selectedObject;
            updateList(selectedObject);
          }
        }

        /**
         * セットされたオブジェクトを編集するために オブジェクトを編集するために使用するコンポーネントにオブジェクトをセットする
         * 
         * @param selectedObject
         */
        private void updateList(Object selectedObject) {
          // Method Listの更新
          MethodList methodList = MethodList.getInstance();
          methodList.getInstance().setList(ClassContents.getMethods(selectedObject.getClass()));
          // Prameter Listの更新
          ParameterList parameterList = ParameterList.getInstance();
          parameterList.setListData(ClassContents.getFields(selectedObject.getClass()));
          // Invoke Buttonの更新
          InvokeButton.setObject(selectedObject);
        }
      });
    }
  }

  /**
   * 配列のオブジェクトの中身を表示するViewer
   * 
   * @author ono
   *
   */
   private static class ArrayObjectViewer extends JDialog {

    final private int WIDTH = 250;
    private static int height; // 要素の数に応じて可変
    final private static int LIST_ELM_SIZE = 22;// リスト要素表示時の高さ
    static final JFrame frame = new JFrame();
    Object array;
    ObjectList list = new ObjectList();
    Class<?> clazzType;

    public ArrayObjectViewer(Object array) {
      super(frame);
      this.array = array;//ObjectViewerで選択された配列のオブジェクト
      clazzType = array.getClass();
      // ここに配列のオブジェクトを表示させる処理をかく
      add(list);
      init();
      setBounds(100, 100, WIDTH, height);
      setVisible(true);
    }

    private void init() {
      List<String> keys = new ArrayList<String>();
      StringBuilder sb = new StringBuilder();

      int arraySize = Array.getLength(array);
      height = arraySize * LIST_ELM_SIZE;
      for (int j = 0; j < arraySize; j++) {
        if (Array.get(array, j) != null) {
          Object _array = Array.get(array, j);
          keys.add("[" + j + "]" + _array.getClass().toString() + "@" + _array.hashCode());
        } else {
          keys.add("[" + j + "]" + "null");
        }
      }
      list.setListData(keys.toArray());
    }
  }
}
