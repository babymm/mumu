package com.lovecws.mumu.system.controller.common.export;

import com.lovecws.mumu.common.core.utils.DateUtils;
import com.lovecws.mumu.common.excel.beans.ExcelGeneraterBean;
import com.lovecws.mumu.common.excel.generater.ExcelGenerater;
import com.lovecws.mumu.system.entity.SysExportModel;
import com.lovecws.mumu.system.service.SysCommonService;
import com.lovecws.mumu.system.service.SysExportModelService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author ganliang
 * @version 2016年9月27日 下午12:17:36
 * @desc 导出控制器
 */
@Controller
@RequestMapping("/common/export")
public class ExportController {

    @Autowired
    private SysCommonService commonService;
    @Autowired
    private SysExportModelService modelService;

    /**
     * 导出
     * @param modelName 模型名称
     * @param excelType excel格式
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = { "/excel/{modelName}","/excel/{modelName}/{excelType}" }, method = RequestMethod.GET)
    public void exportExcel(@PathVariable String modelName,@PathVariable String excelType, HttpServletResponse response) throws IOException {
        //默认导出xls格式excel
        if(excelType==null||"".equals(excelType)){
            excelType="XLS";
        }
        List<SysExportModel> models = modelService.queryExportModelByCondition(modelName);
        // 模型不存在 直接结束
        if (models == null || models.size() == 0) {
            return;
        }
        // 获取导出数据
        SysExportModel model = models.get(0);
        List<List<Object>> exportData = commonService.getAllData(model.getModelName(), model.getEnames(), null);
        List<String> exportHeaderNames = new ArrayList<String>();
        String[] headerNames = model.getCnames().split(",");
        for (String headerName : headerNames) {
            exportHeaderNames.add(headerName);
        }

        response.reset();
        // 文件下载
        response.setContentType("application/vnd.ms-excel");
        String filename = "报表"+modelName+"（"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ "）";

        filename = new String(filename.getBytes("gbk"), "iso-8859-1");
        response.setHeader("Content-disposition", "attachment;filename="+ filename + "."+excelType.toLowerCase());
        response.setBufferSize(1024);

        //获取excel表单
        ExcelGenerater excelGenerater=new ExcelGenerater();
        ExcelGeneraterBean excelGeneraterBean = excelGenerater.create(modelName, exportHeaderNames, exportData);
        Workbook workbook = excelGeneraterBean.getWorkbook();
        //写入数据 到流
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
