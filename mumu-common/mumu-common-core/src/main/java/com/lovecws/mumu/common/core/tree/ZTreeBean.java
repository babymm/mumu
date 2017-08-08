package com.lovecws.mumu.common.core.tree;

import java.io.Serializable;
import java.util.List;

/**
 * ztree树实体对象
 * 
 * @desc
 * @author ganliang
 * @version 2016年9月13日 下午10:34:37
 */
public class ZTreeBean implements Serializable {

	private static final long serialVersionUID = -9218598443228659197L;

	private String id;
	private String pId;
	private String name;

	private boolean open;// true false
	private String url;//
	private String icon;

	private boolean checked;
	private List<ZTreeBean> children;

	public ZTreeBean() {
		super();
	}

	/**
	 *
	 * @param id
	 * @param pId
	 * @param name
	 */
	public ZTreeBean(String id, String pId, String name) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
	}

	/**
	 *
	 * @param id
	 * @param pId
	 * @param name
	 * @param open
	 * @param icon
	 * @param checked
	 */
	public ZTreeBean(String id, String pId, String name, boolean open, String icon, boolean checked) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
		this.icon = icon;
		this.checked = checked;
	}

	/**
	 *
	 * @param id
	 * @param pId
	 * @param name
	 * @param open
	 * @param url
	 * @param icon
	 * @param checked
	 */
	public ZTreeBean(String id, String pId, String name, boolean open, String url, String icon, boolean checked) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
		this.url = url;
		this.icon = icon;
		this.checked = checked;
	}

	/**
	 *
	 * @param id
	 * @param pId
	 * @param name
	 * @param open
	 * @param url
	 * @param icon
	 * @param checked
	 * @param children
	 */
	public ZTreeBean(String id, String pId, String name, boolean open, String url, String icon, boolean checked,
			List<ZTreeBean> children) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
		this.url = url;
		this.icon = icon;
		this.checked = checked;
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<ZTreeBean> getChildren() {
		return children;
	}

	public void setChildren(List<ZTreeBean> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "ZTreeBean [id=" + id + ", pId=" + pId + ", name=" + name + ", open=" + open + ", url=" + url + ", icon="
				+ icon + ", checked=" + checked + ", children=" + children + "]";
	}

}
