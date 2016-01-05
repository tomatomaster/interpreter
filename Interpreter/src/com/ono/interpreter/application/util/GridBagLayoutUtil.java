package com.ono.interpreter.application.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * GridBagLayoutを補助するUtilityクラス
 * @author ono
 *
 */
public class GridBagLayoutUtil {
	
	/**
	 * 指定したグリッドの座標にコンポーネントを貼り付ける
	 * @param x グリッドのx座標
	 * @param y グリッドのy座標
	 * @param gbc グリッドバッグコンテナ
	 * @param comp 貼り付けるコンポーネント
	 * @param gbl　グリッドバッグレイアウトオブジェクト
	 * @param contena 貼り付けるコンテナ
	 */
	public static void setGbcLayout(int x, int y, GridBagConstraints gbc,
			Component comp, GridBagLayout layout, Container contena ) {
		gbc.gridx = x;
		gbc.gridy = y;
		layout.setConstraints(comp, gbc);
		contena.add(comp);
	}
}
