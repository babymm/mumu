package com.lovecws.mumu.data.car.service;

import com.lovecws.mumu.data.car.entity.DataCarAttribute;

import java.util.List;
import java.util.Map;

public interface DataCarAttributeService {
	/** ========app后台接口======= */
	/**
	 * 添加汽车配置信息
	 */
	public void addSystemCarAttr(DataCarAttribute carAttribute);

	/**
	 * 车型是否已经存在
	 * 
	 * @param id
	 * @return
	 */
	public boolean idIsExist(String id);
	
	/**
	 * 获取车系所属的所有车型的图片及图片数量（外观、中控、座椅、细节）
	 * @param parentId
	 * @return
	 */
	public List<DataCarAttribute> getSystemCarAttrImages(String parentId);
	
	/** ========web后台接口======= */
	/**
	 * 获取车型的配置信息
	 * 
	 * @param id
	 * @param from
	 *            basic：基本信息，body：车体，engine：发动机，gearbox：变速箱，chassisbrake：底盘制动，safe：安全配置，wheel：车轮，drivingauxiliary：行车辅助，doormirror：门窗/后视镜，light：灯光，internalconfig：内部配置，seat：车座，entcom：娱乐通讯，aircondrefrigerator：空调/冰箱，actualtest：实际测试，facade：外观图片，interior：内饰图片，space：空间图片，diagram：图解图片，official：官方图片
	 * @return
	 */
	public Map<String, Object> getSystemCarAttrById(String id, String from);

	/**
	 * 修改车型配置信息
	 * 
	 * @param systemCarAttrEntity
	 * @return
	 */
	public boolean updateSystemCarAttr(DataCarAttribute systemCarAttrEntity);
	
	/**
	 * 删除车型配置信息
	 * @param id
	 * @return
	 */
	public boolean deleteSystemCarAttr(String id);

	public DataCarAttribute getDataCarAttributeById(String id);

}
