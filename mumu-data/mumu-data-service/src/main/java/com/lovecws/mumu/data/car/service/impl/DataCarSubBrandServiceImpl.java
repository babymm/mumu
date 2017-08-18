package com.lovecws.mumu.data.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.data.car.dao.DataCarSubBrandDao;
import com.lovecws.mumu.data.car.entity.DataCarBrand;
import com.lovecws.mumu.data.car.entity.DataCarSubBrand;
import com.lovecws.mumu.data.car.service.DataCarBrandService;
import com.lovecws.mumu.data.car.service.DataCarSubBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class DataCarSubBrandServiceImpl implements DataCarSubBrandService {

	@Autowired
	private DataCarSubBrandDao carSubBrandDao;
	@Autowired
	private DataCarBrandService carBrandService;

	/** =========================app后台接口======================= */
	@Override
	public List<DataCarSubBrand> getSystemCarSubBrandByParentId(String parentId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentId", parentId);
		paramMap.put("status", PublicEnum.NORMAL.value());
		return carSubBrandDao.listByColumn(paramMap);
	}

	/** =========================web后台接口======================= */
	@Override
	public PageBean<DataCarSubBrand> getCarSubBrandByParentId(String parentId, String searchContent,
															  PageParam page) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentId", parentId);
		paramMap.put("searchContent", searchContent);
		paramMap.put("status", PublicEnum.NORMAL.value());
		return carSubBrandDao.listPage(page, paramMap);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public DataCarSubBrand addSystemCarSubBrand(DataCarSubBrand carSubBrandEntity) {
		Integer parentId = carSubBrandEntity.getParentId();
		// 获取父品牌
		DataCarBrand carBrandEntity = carBrandService.getSystemCarBrandById(parentId);
		carSubBrandEntity.setParentId(parentId);
		carSubBrandEntity.setInitial(carBrandEntity.getInitial());
		carSubBrandEntity.setDepth(2);
		carSubBrandEntity.setStatus(PublicEnum.NORMAL.value());
		// 添加数据
		carSubBrandEntity = carSubBrandDao.insert(carSubBrandEntity);
		return carSubBrandEntity;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public boolean updateSystemCarSubBrand(DataCarSubBrand carSubBrandEntity) {
		carSubBrandEntity.setDepth(2);
		return carSubBrandDao.update(carSubBrandEntity) > 0;
	}

	@Override
	public DataCarSubBrand getCarSubBrandById(Integer id) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", id);
		return carSubBrandDao.getByColumn(paramMap);
	}

}
