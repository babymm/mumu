package com.lovecws.mumu.data.car.entity;

import com.lovecws.mumu.common.core.entity.PersistentEntity;

/**
 * 系统车辆子公司实体
 * @author Administrator
 *
 */
public class DataCarSubBrand extends PersistentEntity {

	private static final long serialVersionUID = -7118071305783961330L;
	
	private Integer id;// id
	private String name;// 名称
	private String initial;// 首字母
	
	private Integer parentId;// 父id
	private Integer depth;// 深度
	private String status;// 状态（1、可用；2、不可用）
	
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
