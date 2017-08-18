package com.lovecws.mumu.data.car.entity;

import com.lovecws.mumu.common.core.entity.PersistentEntity;

/**
 * 系统汽车实体
 * @author Administrator
 *
 */
public class DataCarModel extends PersistentEntity {

	private static final long serialVersionUID = 8732863936827734445L;
	
	private Integer id;// id
	private String name;// 名称
	private String initial;// 首字母
	
	private Integer parentId;// 父id
	private String price;// 价格
	private String yearType;// 生产年份
	
	private String productionState;// 生产状况
	private String saleState;// 销售状况
	private Integer sizeType;// 汽车类型（SUV、小型车等）
	
	private String maxhorsepower;// 最大马力
	private String gearbox;// 变速箱
	private Integer depth;// 深度
	
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getYearType() {
		return yearType;
	}
	public void setYearType(String yearType) {
		this.yearType = yearType;
	}
	public String getProductionState() {
		return productionState;
	}
	public void setProductionState(String productionState) {
		this.productionState = productionState;
	}
	public String getSaleState() {
		return saleState;
	}
	public void setSaleState(String saleState) {
		this.saleState = saleState;
	}
	
	public Integer getSizeType() {
		return sizeType;
	}
	public void setSizeType(Integer sizeType) {
		this.sizeType = sizeType;
	}
	public String getMaxhorsepower() {
		return maxhorsepower;
	}
	public void setMaxhorsepower(String maxhorsepower) {
		this.maxhorsepower = maxhorsepower;
	}
	public String getGearbox() {
		return gearbox;
	}
	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}

}
