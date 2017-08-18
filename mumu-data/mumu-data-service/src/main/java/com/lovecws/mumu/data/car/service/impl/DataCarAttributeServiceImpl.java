package com.lovecws.mumu.data.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lovecws.mumu.data.car.dao.DataCarAttributeDao;
import com.lovecws.mumu.data.car.entity.DataCarAttribute;
import com.lovecws.mumu.data.car.entity.DataCarModel;
import com.lovecws.mumu.data.car.service.DataCarAttributeService;
import com.lovecws.mumu.data.car.service.DataCarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class DataCarAttributeServiceImpl implements DataCarAttributeService {

	@Autowired
	private DataCarAttributeDao carAttributeDao;
	@Autowired
	private DataCarModelService carModelServcie;

	/** ========app后台接口======= */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addSystemCarAttr(DataCarAttribute DataCarAttribute) {
		Integer id = DataCarAttribute.getId();
		// 获取汽车车型
		DataCarModel carModel = carModelServcie.getSystemCarModelById(id);
		DataCarAttribute.setId(id);
		if (carModel != null) {
			DataCarAttribute.setName(carModel.getName());
			DataCarAttribute.setInitial(carModel.getInitial());
			DataCarAttribute.setParentid(carModel.getParentId());
			DataCarAttribute.setPrice(carModel.getPrice());
			DataCarAttribute.setYeartype(carModel.getYearType());
			DataCarAttribute.setProductionstate(carModel.getProductionState());
			DataCarAttribute.setSalestate(carModel.getSaleState());
			if (carModel.getSizeType() != null) {
				String sizeType = "其它";
				if (carModel.getSizeType() == 1) {
					sizeType = "微型车";
				} else if (carModel.getSizeType() == 2) {
					sizeType = "小型车";
				} else if (carModel.getSizeType() == 3) {
					sizeType = "紧凑型车";
				} else if (carModel.getSizeType() == 4) {
					sizeType = "中型车";
				} else if (carModel.getSizeType() == 5) {
					sizeType = "中大型车";
				} else if (carModel.getSizeType() == 6) {
					sizeType = "豪华车";
				} else if (carModel.getSizeType() == 7) {
					sizeType = "MPV";
				} else if (carModel.getSizeType() == 8) {
					sizeType = "SUV";
				} else if (carModel.getSizeType() == 9) {
					sizeType = "跑车";
				} else if (carModel.getSizeType() == 10) {
					sizeType = "皮卡";
				} else if (carModel.getSizeType() == 11) {
					sizeType = "卡车";
				} else if (carModel.getSizeType() == 12) {
					sizeType = "面包车";
				} else if (carModel.getSizeType() == 13) {
					sizeType = "客车";
				}
				DataCarAttribute.setSizetype(sizeType);
			}
		}
		DataCarAttribute.setDepth(4);
		// 添加数据到sys_car_attr表中
		carAttributeDao.insert(DataCarAttribute);
	}

	@Override
	public DataCarAttribute getDataCarAttributeById(String id) {
		return carAttributeDao.getById(id);
	}

	@Override
	public boolean idIsExist(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return carAttributeDao.getCountByColumn(paramMap) > 0;
	}

	@Override
	public List<DataCarAttribute> getSystemCarAttrImages(String parentId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentId", parentId);
		return carAttributeDao.listByColumn(paramMap);
	}

	/** ========web后台接口======= */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getSystemCarAttrById(String id, String from) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		DataCarAttribute systemCarAttr = new DataCarAttribute();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 根据传入的内容，判断是获取哪一条信息
		if (from == null || "".equals(from) || from.equals("basic")) {
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getbasic", paramMap);
			String basicString = systemCarAttr.getBasic();
			resultMap = JSON.parseObject(basicString, Map.class);
			resultMap.put("name", systemCarAttr.getName());
			resultMap.put("price", systemCarAttr.getPrice());
		} else if (from.equals("body")) {
			// 车体
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getbody", paramMap);
			String bodyString = systemCarAttr.getBody();
			resultMap = JSON.parseObject(bodyString);
		} else if (from.equals("engine")) {
			// 发动机
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getengine", paramMap);
			String engineString = systemCarAttr.getEngine();
			resultMap = JSON.parseObject(engineString);
		} else if (from.equals("gearbox")) {
			// 变速箱
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getgearbox", paramMap);
			String gearboxString = systemCarAttr.getGearbox();
			resultMap = JSON.parseObject(gearboxString);
		} else if (from.equals("chassisbrake")) {
			// 底盘制动
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getchassisbrake", paramMap);
			String chassisbrake = systemCarAttr.getChassisbrake();
			resultMap = JSON.parseObject(chassisbrake);
		} else if (from.equals("safe")) {
			// 安全配置
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getsafe", paramMap);
			String safe = systemCarAttr.getSafe();
			resultMap = JSON.parseObject(safe);
		} else if (from.equals("wheel")) {
			// 车轮
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getwheel", paramMap);
			String wheel = systemCarAttr.getWheel();
			resultMap = JSON.parseObject(wheel);
		} else if (from.equals("drivingauxiliary")) {
			// 行车辅助
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getdrivingauxiliary", paramMap);
			String drivingauxiliary = systemCarAttr.getDrivingauxiliary();
			resultMap = JSON.parseObject(drivingauxiliary);
		} else if (from.equals("doormirror")) {
			// 门窗/后视镜
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getdoormirror", paramMap);
			String doormirror = systemCarAttr.getDoormirror();
			resultMap = JSON.parseObject(doormirror);
		} else if (from.equals("light")) {
			// 灯光
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getlight", paramMap);
			String light = systemCarAttr.getLight();
			resultMap = JSON.parseObject(light);
		} else if (from.equals("internalconfig")) {
			// 内部配置
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getinternalconfig", paramMap);
			String internalconfig = systemCarAttr.getInternalconfig();
			resultMap = JSON.parseObject(internalconfig);
		} else if (from.equals("seat")) {
			// 车座
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getseat", paramMap);
			String seat = systemCarAttr.getSeat();
			resultMap = JSON.parseObject(seat);
		} else if (from.equals("entcom")) {
			// 娱乐通讯
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getentcom", paramMap);
			String entcom = systemCarAttr.getEntcom();
			resultMap = JSON.parseObject(entcom);
		} else if (from.equals("aircondrefrigerator")) {
			// 空调/冰箱
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getaircondrefrigerator", paramMap);
			String aircondrefrigerator = systemCarAttr.getAircondrefrigerator();
			resultMap = JSON.parseObject(aircondrefrigerator);
		} else if (from.equals("actualtest")) {
			// 实际测试
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getactualtest", paramMap);
			String actualtest = systemCarAttr.getActualtest();
			resultMap = JSON.parseObject(actualtest);
		} else if (from.equals("facadeImage")) {
			// 外观图片
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getfacadeImage", paramMap);
			String facadeImage = "";
			Integer facadeImageCount = 0;
			if (systemCarAttr != null) {
				facadeImage = systemCarAttr.getFacadeImage();
				facadeImageCount = systemCarAttr.getFacadeImageCount();
			}
			resultMap.put("image", facadeImage);
			resultMap.put("imageCount", String.valueOf(facadeImageCount));
		} else if (from.equals("centralImage")) {
			// 中控图片
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getcentralImage", paramMap);
			String centralImage = "";
			Integer centralImageCount = 0;
			if (systemCarAttr != null) {
				centralImage = systemCarAttr.getCentralImage();
				centralImageCount = systemCarAttr.getCentralImageCount();
			}
			resultMap.put("image", centralImage);
			resultMap.put("imageCount", String.valueOf(centralImageCount));
		} else if (from.equals("seatImage")) {
			// 座椅图片
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getseatImage", paramMap);
			String seatImage = "";
			Integer seatImageCount = 0;
			if (systemCarAttr != null) {
				seatImage = systemCarAttr.getSeatImage();
				seatImageCount = systemCarAttr.getSeatImageCount();
			}
			resultMap.put("image", seatImage);
			resultMap.put("imageCount", String.valueOf(seatImageCount));
		} else if (from.equals("detailImage")) {
			// 细节图片
			systemCarAttr = carAttributeDao.getSessionTemplate().selectOne("getdetailImage", paramMap);
			String detailImage = "";
			Integer detailImageCount = 0;
			if (systemCarAttr != null) {
				detailImage = systemCarAttr.getDetailImage();
				detailImageCount = systemCarAttr.getDetailImageCount();
			}
			resultMap.put("image", detailImage);
			resultMap.put("imageCount", String.valueOf(detailImageCount));
		}
		return resultMap;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public boolean updateSystemCarAttr(DataCarAttribute DataCarAttribute) {
		// 对图片的长度进行判断
		String facadeImage = DataCarAttribute.getFacadeImage();
		String centralImage = DataCarAttribute.getCentralImage();
		String seatImage = DataCarAttribute.getSeatImage();
		String detailImage = DataCarAttribute.getDetailImage();
		if (facadeImage!=null && !"".equals(facadeImage)){
			DataCarAttribute.setFacadeImageCount(facadeImage.split(",").length);
		}
		if (centralImage!=null && !"".equals(centralImage)){
			DataCarAttribute.setCentralImageCount(centralImage.split(",").length);
		}
		if (seatImage!=null && !"".equals(seatImage)){
			DataCarAttribute.setSeatImageCount(seatImage.split(",").length);
		}
		if (detailImage!=null && !"".equals(detailImage)){
			DataCarAttribute.setDetailImageCount(detailImage.split(",").length);
		}
		return carAttributeDao.update(DataCarAttribute) > 0;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public boolean deleteSystemCarAttr(String id) {
		return carAttributeDao.delete(id) > 0;
	}

}
