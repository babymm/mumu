package com.lovecws.mumu.data.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.data.car.dao.DataCarSizeTypeDao;
import com.lovecws.mumu.data.car.entity.DataCarSizeType;
import com.lovecws.mumu.data.car.service.DataCarSizetypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataCarSizeTypeServiceImpl implements DataCarSizetypeService{
	/** ========app后台接口======= */
	@Autowired
	private DataCarSizeTypeDao carSizetypeDao;

	@Override
	public List<DataCarSizeType> getSystemCarSizetypeByRecomm(String recomm) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("sizetypeStatus", PublicEnum.NORMAL.value());
		paramMap.put("sizetypeRecomm", recomm);
		return carSizetypeDao.listByColumn(paramMap);
	}

	/** ========web后台接口======= */
	@Override
	public List<DataCarSizeType> getAllSystemCarSizeType() {
		return carSizetypeDao.listBy(new HashMap<String,Object>());
	}

}
