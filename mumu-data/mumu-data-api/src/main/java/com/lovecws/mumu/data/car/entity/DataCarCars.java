package com.lovecws.mumu.data.car.entity;

import com.lovecws.mumu.common.core.entity.PersistentEntity;

/**
 * 系统汽车车系实体
 * @author Administrator
 */
public class DataCarCars extends PersistentEntity {

	private static final long serialVersionUID = -7306134666034781132L;

	private Integer id;// id
	private String name;// 名称
	private String fullName;// 全名
	
	private String initial;// 首字母
	private Integer parentId;// 父id
	private String logo;// logo
	
	private Integer saleState;// 生产状况 
	private Integer sizeType;// 汽车型号（1、微型车，2、小型车，3、紧凑型车，4、中型车，5、中大型车，6、豪华车，7、MPV，8、SUV，9、跑车，10、皮卡，11、卡车，12、面包车，13、客车，14、其它）
	private String price;//车系的价格区间（最低价格-最高价格）
	
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
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	public Integer getSaleState() {
		return saleState;
	}
	public void setSaleState(Integer saleState) {
		this.saleState = saleState;
	}
	public Integer getSizeType() {
		return sizeType;
	}
	public void setSizeType(Integer sizeType) {
		this.sizeType = sizeType;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
}
