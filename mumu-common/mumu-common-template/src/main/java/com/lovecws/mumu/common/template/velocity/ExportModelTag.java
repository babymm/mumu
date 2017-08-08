package com.lovecws.mumu.common.template.velocity;

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

		return false;
	}
}
