package com.ono.interpreter.application.uiparts.tree;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.ono.interpreter.application.ApplicationWindow;

public class ObjectTree extends JTree {

	private static final long	serialVersionUID	= -4602451617119474577L;
	private static final String NAME = "ObjectTree";
	private static final int WIDTH = ApplicationWindow.getWIDTH()/7;
	private static final int HEIGHT= ApplicationWindow.getHEIGHT()/2;
	private static Vector<String> treeData = new Vector<String>();
	private static DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("Object");
	
	public ObjectTree() {
		super(treeNode);
	}
	
	public Component getComponentModel() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setView(this);
		scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		return scrollPane;
	}
}
