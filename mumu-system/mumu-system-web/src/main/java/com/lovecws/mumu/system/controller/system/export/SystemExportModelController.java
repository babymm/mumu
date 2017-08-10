package com.lovecws.mumu.system.controller.system.export;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.response.ResponseEntity;
import com.lovecws.mumu.system.entity.SysDBField;
import com.lovecws.mumu.system.entity.SysDBTable;
import com.lovecws.mumu.system.entity.SysExportModel;
import com.lovecws.mumu.system.service.SysCommonService;
import com.lovecws.mumu.system.service.SysExportModelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典模型
 * @author lovecws
 */
@Controller
@RequestMapping("/system/export")
public class SystemExportModelController {

	private static final Logger log=Logger.getLogger(SystemExportModelController.class);
	@Autowired
	private SysCommonService commonService;
	@Autowired
	private SysExportModelService exportModelService;
	
	/**
	 * 导出列表
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String exports(){
		return "system/export/index";
	}
	
	/**
	 * 导出列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/page"})
	public Map<String,Object> exportPage(String modelName,int beginIndex,int pageSize){
		//分页查询
		PageBean<SysExportModel> pageBean=exportModelService.listPage(modelName,beginIndex/pageSize+1,pageSize);
		Map<String,Object> page=new HashMap<String,Object>();
		page.put("total", pageBean.getTotalCount());
		page.put("rows", pageBean.getRecordList());
		return page;
	}
	
	/**
	 * 添加导出模型
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public String exportAdd(HttpServletRequest request){
		//获取所有的表
		List<SysDBTable> tables = commonService.getAllTable();
		//获取所有的导出模型
		List<SysExportModel> models=exportModelService.queryExportModelByCondition(null);
		List<SysDBTable> extraTables=new ArrayList<SysDBTable>();
		for (SysDBTable dbTable : tables) {
			boolean exists=false;
			for (SysExportModel sysExportModel : models) {
				if(sysExportModel.getModelName().equals(dbTable.getTableName())){
					exists=true;
					break;
				}
			}
			if(!exists){
				extraTables.add(dbTable);
			}
		}
		request.setAttribute("tables", extraTables);
		return "system/export/add";
	}
	
	/**
	 * 选择表下的字段列表
	 * @param tableName 表名
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/select"},method=RequestMethod.GET)
	public List<SysDBField> exportSelect(String tableName){
		List<SysDBField> fields=null;
		if(tableName==null||"".equals(tableName)){
			//获取所有的表
			List<SysDBTable> tables = commonService.getAllTable();
			if(tables!=null&&tables.size()>0){
				fields = commonService.getAllField(tables.get(0).getTableName());
			}
		}else{
			fields = commonService.getAllField(tableName);
		}
		return fields;
	}
	
	/**
	 * 保存导出的模型
	 * @param modelName 模型名称
	 * @param cnames 中文字段集合
	 * @param enames 英文字段集合
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseEntity saveExportModel(String modelName, String cnames, String enames){
		SysExportModel exportModel=new SysExportModel();
		exportModel.setCnames(cnames);
		exportModel.setEnames(enames);
		exportModel.setModelName(modelName);
		try {
			exportModelService.addExportModel(exportModel);
			return new ResponseEntity(200,"导出模型保存成功",null);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "保存模型出现异常", null);
		}
	}
	
	/**
	 * 编辑模型详情
	 * @return
	 */
	@RequestMapping(value="/view/{modelId}",method=RequestMethod.GET)
	public String view(@PathVariable String modelId){
		return "system/export/view";
	}
	
	/**
	 * 获取导出设置详情
	 * @param modelId 模型id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/viewSelect/{modelId}"},method=RequestMethod.GET)
	public List<SysDBField> exportViewSelect(@PathVariable String modelId,HttpServletRequest request){
		//查询导出模型
		SysExportModel exportModel = exportModelService.getSysExportModelById(modelId);
		if(exportModel==null){
			throw new IllegalArgumentException();
		}
		//获取模型下的所有字段
		List<SysDBField> fields = commonService.getAllField(exportModel.getModelName());
		
		List<SysDBField> viewFields=new ArrayList<SysDBField>();
		String[] enames = exportModel.getEnames().split(",");
		String[] cnames = exportModel.getCnames().split(",");
		for (SysDBField dbField : fields) {
			for (int i = 0; i < enames.length; i++) {
				if(enames[i].equals(dbField.getFieldName())){
					dbField.setRemarks(cnames[i]);
					viewFields.add(dbField);
					break;
				}
			}
		}
		return viewFields;
	}
	
	/**
	 * 编辑导出模型
	 * @return
	 */
	@RequestMapping(value="/edit/{modelId}",method=RequestMethod.GET)
	public String exportEdit(@PathVariable String modelId){
		return "system/export/edit";
	}
	
	/**
	 * 编辑导出模型
	 * @param modelId 模型id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/editSelect/{modelId}"},method=RequestMethod.GET)
	public List<Map<String,Object>> exportEditSelect(@PathVariable String modelId,HttpServletRequest request){
		//查询导出模型
		SysExportModel exportModel = exportModelService.getSysExportModelById(modelId);
		if(exportModel==null){
			throw new IllegalArgumentException();
		}
		//获取模型下的所有字段
		List<SysDBField> fields = commonService.getAllField(exportModel.getModelName());
		
		List<Map<String,Object>> maps=new ArrayList<Map<String,Object>>();
		String[] enames = exportModel.getEnames().split(",");
		String[] cnames = exportModel.getCnames().split(",");
		for (SysDBField dbField : fields) {
			boolean state=false;
			for (int i = 0; i < enames.length; i++) {
				if(enames[i].equals(dbField.getFieldName())){
					dbField.setRemarks(cnames[i]);
					state=true;
					break;
				}
			}
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("state", state);
			map.put("fieldName", dbField.getFieldName());
			map.put("remarks", dbField.getRemarks());
			map.put("fieldType", dbField.getFieldType());
			map.put("fieldSize", dbField.getFieldSize());
			maps.add(map);
		}
		return maps;
	}
	
	/**
	 * 保存编辑的模型
	 * @param modelId 模型id
	 * @param cnames 中文字段集合
	 * @param enames 英文字段集合
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit",method=RequestMethod.PUT)
	public ResponseEntity updateExportModel(int modelId,String cnames,String enames,HttpServletRequest request){
		SysExportModel exportModel=new SysExportModel();
		exportModel.setCnames(cnames);
		exportModel.setEnames(enames);
		exportModel.setModelId(modelId);
		try {
			exportModelService.updateSysExportModel(exportModel);
			return new ResponseEntity(200, "导出模型编辑成功", null);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "更新模型出现异常", null);
		}
	}
	
	/**
	 * 删除模型
	 * @param modelId 模型od
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete/{modelId}",method=RequestMethod.DELETE)
	public ResponseEntity deleteExportModel(@PathVariable String modelId){
		try {
			exportModelService.deleteById(modelId);
			return new ResponseEntity(200, "导出模型删除成功", null);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "删除模型出现异常", null);
		}
	}
}
