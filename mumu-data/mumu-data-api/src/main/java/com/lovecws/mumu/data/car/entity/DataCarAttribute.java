package com.lovecws.mumu.data.car.entity;

import com.lovecws.mumu.common.core.entity.PersistentEntity;

/**
 * 车型的详细数据（配置数据）实体
 * @author Administrator
 *
 */
public class DataCarAttribute extends PersistentEntity {
	
	private static final long serialVersionUID = 4063786928621061251L;
	
	private Integer id;// 车型id
	private String name;// 名称
	private String initial;// 首字母
	
	private Integer parentid;// 上级id
	private String price;// 官方指导价
	private String yeartype;// 年款
	
	private String productionstate;// 生产状态
	private String salestate;// 销售状态
	private String sizetype;// 尺寸类型
	
	private Integer depth;// 深度：1 品牌  2 子公司  3 车型  4 具体车型
	private String basic;// 基本信息
	private String body;// 车体
	
	private String engine;// 发动机
	private String gearbox;// 变速箱
	private String chassisbrake;// 地盘制动
	
	private String safe;// 安全配置
	private String wheel;// 车轮
	private String drivingauxiliary;// 行车辅助
	
	private String doormirror;// 门窗/后视镜
	private String light;// 灯光
	private String internalconfig;// 内部配置
	
	private String seat;// 车座
	private String entcom;// 娱乐通讯
	private String aircondrefrigerator;// 空调/冰箱
	
	private String actualtest;// 实际测试
	private String facadeImage;// 外观图片
	private Integer facadeImageCount;// 外观图片数量
	
	private String centralImage;// 中控图片
	private Integer centralImageCount;// 中控图片数量
	private String seatImage;// 座椅图片
	
	private Integer seatImageCount;// 座椅图片数量
	private String detailImage;// 细节图片
	private Integer detailImageCount;// 细节图片数量
	
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
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getYeartype() {
		return yeartype;
	}
	public void setYeartype(String yeartype) {
		this.yeartype = yeartype;
	}
	public String getProductionstate() {
		return productionstate;
	}
	public void setProductionstate(String productionstate) {
		this.productionstate = productionstate;
	}
	public String getSalestate() {
		return salestate;
	}
	public void setSalestate(String salestate) {
		this.salestate = salestate;
	}
	public String getSizetype() {
		return sizetype;
	}
	public void setSizetype(String sizetype) {
		this.sizetype = sizetype;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public String getBasic() {
		return basic;
	}
	public void setBasic(String basic) {
		this.basic = basic;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public String getGearbox() {
		return gearbox;
	}
	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}
	public String getChassisbrake() {
		return chassisbrake;
	}
	public void setChassisbrake(String chassisbrake) {
		this.chassisbrake = chassisbrake;
	}
	public String getSafe() {
		return safe;
	}
	public void setSafe(String safe) {
		this.safe = safe;
	}
	public String getWheel() {
		return wheel;
	}
	public void setWheel(String wheel) {
		this.wheel = wheel;
	}
	public String getDrivingauxiliary() {
		return drivingauxiliary;
	}
	public void setDrivingauxiliary(String drivingauxiliary) {
		this.drivingauxiliary = drivingauxiliary;
	}
	public String getDoormirror() {
		return doormirror;
	}
	public void setDoormirror(String doormirror) {
		this.doormirror = doormirror;
	}
	public String getLight() {
		return light;
	}
	public void setLight(String light) {
		this.light = light;
	}
	public String getInternalconfig() {
		return internalconfig;
	}
	public void setInternalconfig(String internalconfig) {
		this.internalconfig = internalconfig;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getEntcom() {
		return entcom;
	}
	public void setEntcom(String entcom) {
		this.entcom = entcom;
	}
	public String getAircondrefrigerator() {
		return aircondrefrigerator;
	}
	public void setAircondrefrigerator(String aircondrefrigerator) {
		this.aircondrefrigerator = aircondrefrigerator;
	}
	public String getActualtest() {
		return actualtest;
	}
	public void setActualtest(String actualtest) {
		this.actualtest = actualtest;
	}
	public String getFacadeImage() {
		return facadeImage;
	}
	public void setFacadeImage(String facadeImage) {
		this.facadeImage = facadeImage;
	}
	public Integer getFacadeImageCount() {
		return facadeImageCount;
	}
	public void setFacadeImageCount(Integer facadeImageCount) {
		this.facadeImageCount = facadeImageCount;
	}
	public String getCentralImage() {
		return centralImage;
	}
	public void setCentralImage(String centralImage) {
		this.centralImage = centralImage;
	}
	public Integer getCentralImageCount() {
		return centralImageCount;
	}
	public void setCentralImageCount(Integer centralImageCount) {
		this.centralImageCount = centralImageCount;
	}
	public String getSeatImage() {
		return seatImage;
	}
	public void setSeatImage(String seatImage) {
		this.seatImage = seatImage;
	}
	public Integer getSeatImageCount() {
		return seatImageCount;
	}
	public void setSeatImageCount(Integer seatImageCount) {
		this.seatImageCount = seatImageCount;
	}
	public String getDetailImage() {
		return detailImage;
	}
	public void setDetailImage(String detailImage) {
		this.detailImage = detailImage;
	}
	public Integer getDetailImageCount() {
		return detailImageCount;
	}
	public void setDetailImageCount(Integer detailImageCount) {
		this.detailImageCount = detailImageCount;
	}
	
}
