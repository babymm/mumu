package com.lovecws.mumu.data.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.data.car.dao.DataCarCarsDao;
import com.lovecws.mumu.data.car.entity.DataCarCars;
import com.lovecws.mumu.data.car.entity.DataCarSubBrand;
import com.lovecws.mumu.data.car.service.DataCarCarsService;
import com.lovecws.mumu.data.car.service.DataCarSubBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=true)
public class DataCarCarsServiceImpl implements DataCarCarsService{
	
	@Autowired
	private DataCarCarsDao carsDao;
	@Autowired
	private DataCarSubBrandService carSubBrandService;
	
	/** =========================app后台接口======================= */
	@Override
	public List<DataCarCars> getSystemCarsByParentId(String parentId) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("parentId", parentId);
		return carsDao.listByColumn(paramMap);
	}

	@Override
	public List<DataCarCars> getHotSystemCars() {
		return carsDao.listBy(new HashMap<String,Object>());
	}

	@Override
	public List<DataCarCars> getSystemCarsBySizetype(String sizetype, PageParam page) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("beginIndex", page.getBeginIndex());
		paramMap.put("numPerPage", page.getNumPerPage());
		paramMap.put("sizetype", sizetype);
		return carsDao.selectList("getSystemCarsBySizetype", paramMap);
	}

	@Override
	public List<DataCarCars> getSystemCarsBySearch(String searchContent, PageParam page) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("beginIndex", page.getBeginIndex());
		paramMap.put("numPerPage", page.getNumPerPage());
		paramMap.put("searchContent", searchContent);
		return carsDao.selectList("getSystemCarsBySearch", paramMap);
	}
	
	/** =========================web后台接口======================= */
	@Override
	public PageBean<DataCarCars> getCars(String parentId, String searchContent, PageParam page) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("parentId", parentId);
		paramMap.put("searchContent", searchContent);
		return carsDao.listPage(page, paramMap);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=false)
	public DataCarCars addSystemCars(DataCarCars carsEntity) {
		Integer parentId = carsEntity.getParentId();
		// 获取父汽车子公司
		DataCarSubBrand carSubBrandEntity = carSubBrandService.getCarSubBrandById(parentId);
		carsEntity.setParentId(parentId);
		carsEntity.setInitial(carSubBrandEntity.getInitial());
		carsEntity.setDepth(3);
		// 添加数据
		carsEntity = carsDao.insert(carsEntity);
		return carsEntity;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=false)
	public boolean updateSystemCars(DataCarCars carsEntity) {
		carsEntity.setDepth(3);
		return carsDao.update(carsEntity) > 0;
	}

	@Override
	public DataCarCars getSystemCarsById(Integer id) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", id);
		return carsDao.getByColumn(paramMap);
	}

}
