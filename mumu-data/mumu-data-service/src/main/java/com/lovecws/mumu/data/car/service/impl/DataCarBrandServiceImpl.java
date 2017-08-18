package com.lovecws.mumu.data.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.data.car.dao.DataCarBrandDao;
import com.lovecws.mumu.data.car.entity.DataCarBrand;
import com.lovecws.mumu.data.car.service.DataCarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class DataCarBrandServiceImpl implements DataCarBrandService {

	@Autowired
	private DataCarBrandDao carBrandDao;

	/** ========app后台接口======= */
	@Override
	public List<DataCarBrand> getAllSystemCarBrands() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", PublicEnum.NORMAL.value());
		return carBrandDao.listByColumn(paramMap);
	}

	@Override
	public List<DataCarBrand> getHotSystemCarBrands() {
		return carBrandDao.listBy(new HashMap<String, Object>());
	}

	/** ========web后台接口======= */
	@Override
	public PageBean<DataCarBrand> getSystemCarBrands(String searchContent, PageParam page) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("searchContent", searchContent);
		paramMap.put("status", PublicEnum.NORMAL.value());
		return carBrandDao.listPage(page, paramMap);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public DataCarBrand addSystemCarBrand(DataCarBrand systemCarBrand) {
		systemCarBrand.setParentId(0);
		systemCarBrand.setStatus(PublicEnum.NORMAL.value());
		systemCarBrand.setDepth(1);
		// 添加数据
		systemCarBrand = carBrandDao.insert(systemCarBrand);
		return systemCarBrand;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public boolean udpateSystemCarBrand(DataCarBrand systemCarBrand) {
		systemCarBrand.setParentId(0);
		systemCarBrand.setDepth(1);
		return carBrandDao.update(systemCarBrand) > 0;
	}

	public DataCarBrand getSystemCarBrandById(Integer id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return carBrandDao.getByColumn(paramMap);
	}

}
