package com.lovecws.mumu.common.core.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单树
 * 
 * @desc
 * @author ganliang
 * @version 2016年9月29日 下午11:15:32
 */
public class MenuTree implements Serializable {

	private static final long serialVersionUID = 8783871948674115858L;

	private Integer id; // id
	private Integer pid; // 父id
	private String icon; // 图标样式
	private String src; // 菜单链接
	private String text; // 菜单文本内容

	private List<MenuTree> nodes;// 子节点

	public MenuTree() {
		super();
	}

	public MenuTree(Integer id, Integer pid, String icon, String src, String text) {
		super();
		this.id = id;
		this.pid = pid;
		this.icon = icon;
		this.src = src;
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<MenuTree> getNodes() {
		return nodes;
	}

	public void setNodes(List<MenuTree> nodes) {
		this.nodes = nodes;
	}
	
	//将树
	public static List<MenuTree> tree(List<MenuTree> tree){
		if(tree==null){
			throw new IllegalArgumentException();
		}
		List<MenuTree> newTree=new ArrayList<MenuTree>();
		//遍历
		for (MenuTree menuTree : tree) {
			//找到顶级节点
			if(menuTree.getPid()==0){
				//找到子节点
				List<MenuTree> childrens=getChildrens(tree,menuTree.getId());
				menuTree.setNodes(childrens);
				newTree.add(menuTree);
			}
		}
		return newTree;
	}

	//找到子节点
	private static List<MenuTree> getChildrens(List<MenuTree> tree,Integer id) {
		List<MenuTree> children=new ArrayList<MenuTree>();
		for (MenuTree menuTree : tree) {
			if(menuTree.getPid()==id){
				children.add(menuTree);
			}
		}
		return children;
	}

}
