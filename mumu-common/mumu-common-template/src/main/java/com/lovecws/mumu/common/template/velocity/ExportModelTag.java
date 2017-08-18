package com.lovecws.mumu.common.template.velocity;

import com.lovecws.mumu.common.core.utils.WebApplicationContextUtil;
import com.lovecws.mumu.system.entity.SysExportModel;
import com.lovecws.mumu.system.service.SysExportModelService;
import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.config.ValidScope;

import java.util.List;

@DefaultKey("model")
@ValidScope(Scope.APPLICATION)
public class ExportModelTag {

	/**
	 * 判断是否存在model
	 * @param modelName 模型名称
	 * @return
	 */
	public boolean has(String modelName){
		if(modelName==null||"".equals(modelName)){
			return false;
		}
		//获取导出模型服务接口
		SysExportModelService modelService = WebApplicationContextUtil.getBean(SysExportModelService.class);
		//根据模型名称查询模型
		List<SysExportModel> models = modelService.queryExportModelByCondition(modelName);
		if(models!=null&&models.size()>0){
			return true;
		}
		return false;
	}
}
