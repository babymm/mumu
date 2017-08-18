package com.lovecws.mumu.data.car.entity;

import com.lovecws.mumu.common.core.entity.PersistentEntity;

/**
 * 系统车辆商标实体
 * 
 * @author ganliang
 */
public class DataCarBrand extends PersistentEntity {

	private static final long serialVersionUID = -5315457803996255353L;

	private Integer id;// id
	private String name;// 名称

	private String initial;// 首字母
	private Integer parentId;// 父id
	private String logo;// logo地址

	private Integer depth;// 深度
	private String status;// 1、可用；2、不可用

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
