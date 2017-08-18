package com.lovecws.mumu.data.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.data.car.dao.DataCarModelDao;
import com.lovecws.mumu.data.car.entity.DataCarAttribute;
import com.lovecws.mumu.data.car.entity.DataCarCars;
import com.lovecws.mumu.data.car.entity.DataCarModel;
import com.lovecws.mumu.data.car.service.DataCarAttributeService;
import com.lovecws.mumu.data.car.service.DataCarCarsService;
import com.lovecws.mumu.data.car.service.DataCarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class DataCarModelServiceImpl implements DataCarModelService {

	@Autowired
	private DataCarModelDao carModelDao;
	@Autowired
	private DataCarCarsService carsService;
	@Autowired
	private DataCarAttributeService carAttrServcie;

	/** ========app后台接口======= */
	@Override
	public List<DataCarModel> getSystemCarModelByParentId(String parentId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentId", parentId);
		return carModelDao.listByColumn(paramMap);
	}

	@Override
	public List<DataCarModel> getAllSystemCarModel() {
		return carModelDao.listBy(new HashMap<String, Object>());
	}

	/** ========web后台接口======= */
	@Override
	public PageBean<DataCarModel> getSystemCarModel(String parentId, String searchContent, PageParam page) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentId", parentId);
		paramMap.put("searchContent", searchContent);
		return carModelDao.listPage(page, paramMap);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public DataCarModel addSystemCarModel(DataCarModel systemCarModel) {
		Integer parentId = systemCarModel.getParentId();
		// 获取汽车车系
		DataCarCars systemCars = carsService.getSystemCarsById(parentId);
		systemCarModel.setParentId(parentId);
		systemCarModel.setInitial(systemCars.getInitial());
		systemCarModel.setDepth(4);
		// 添加数据
		systemCarModel = carModelDao.insert(systemCarModel);
		// 增加了汽车车型，就需要添加汽车车型的配置信息(基础信息，basic等详细信息暂无)
		DataCarAttribute systemCarAttr = new DataCarAttribute();
		systemCarAttr.setId(systemCarModel.getId());
		carAttrServcie.addSystemCarAttr(systemCarAttr);
		return systemCarModel;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public boolean updateSystemCarModel(DataCarModel systemCarModel) {
		systemCarModel.setDepth(4);
		// 因为车型的配置信息，由车型信息下发，所以更改车型信息时，同时更改车型的配置信息(只更改基础信息)
		// 先判断是否存在配置信息，如果不存在，则不修改
		Integer carModelId = systemCarModel.getId();
		boolean isExist = carAttrServcie.idIsExist(String.valueOf(carModelId));
		if (isExist) {
			DataCarAttribute systemCarAttr = carModelToAttr(systemCarModel);
			systemCarAttr.setId(carModelId);
			carAttrServcie.updateSystemCarAttr(systemCarAttr);
		}
		return carModelDao.update(systemCarModel) > 0;
	}

	@Override
	public DataCarModel getSystemCarModelById(Integer id) {
		return carModelDao.getById(String.valueOf(id));
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public boolean deleteSystemCarModel(String id) {
		// 当删除汽车车型数据时，同事删除车型的配置信息
		carAttrServcie.deleteSystemCarAttr(id);
		return carModelDao.delete(id) > 0;
	}

	/**
	 * 由汽车车型实体转成汽车车型配置信息实体
	 * 
	 * @param systemCarModel
	 * @return
	 */
	private DataCarAttribute carModelToAttr(DataCarModel systemCarModel) {
		DataCarAttribute systemCarAttr = new DataCarAttribute();
		systemCarAttr.setName(systemCarModel.getName());
		systemCarAttr.setPrice(systemCarModel.getPrice());
		systemCarAttr.setYeartype(systemCarModel.getYearType());
		systemCarAttr.setProductionstate(systemCarModel.getProductionState());
		systemCarAttr.setSalestate(systemCarModel.getSaleState());
		String sizeType = "其它";
		if (systemCarModel.getSizeType() != null) {
			if (systemCarModel.getSizeType() == 1) {
				sizeType = "微型车";
			} else if (systemCarModel.getSizeType() == 2) {
				sizeType = "小型车";
			} else if (systemCarModel.getSizeType() == 3) {
				sizeType = "紧凑型车";
			} else if (systemCarModel.getSizeType() == 4) {
				sizeType = "中型车";
			} else if (systemCarModel.getSizeType() == 5) {
				sizeType = "中大型车";
			} else if (systemCarModel.getSizeType() == 6) {
				sizeType = "豪华车";
			} else if (systemCarModel.getSizeType() == 7) {
				sizeType = "MPV";
			} else if (systemCarModel.getSizeType() == 8) {
				sizeType = "SUV";
			} else if (systemCarModel.getSizeType() == 9) {
				sizeType = "跑车";
			} else if (systemCarModel.getSizeType() == 10) {
				sizeType = "皮卡";
			} else if (systemCarModel.getSizeType() == 11) {
				sizeType = "卡车";
			} else if (systemCarModel.getSizeType() == 12) {
				sizeType = "面包车";
			} else if (systemCarModel.getSizeType() == 13) {
				sizeType = "客车";
			}
		}
		systemCarAttr.setSizetype(sizeType);
		return systemCarAttr;
	}

}
