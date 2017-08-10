package com.lovecws.mumu.system.controller.common.export;

import com.lovecws.mumu.common.core.utils.DateUtils;
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

    /**
     * 导出预约列表
     * @param excelType excel格式
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"/excel/appoint", "/excel/appoint/{excelType}"}, method = RequestMethod.GET)
    public void exportAppointExcel(@PathVariable String excelType, HttpServletRequest request,HttpServletResponse response) throws IOException {
        //默认导出xls格式excel
        if (excelType == null || "".equals(excelType)) {
            excelType = "XLS";
        }
        response.reset();
        String modelName = "预约";

        //excel表头数据
        List<String> exportHeaderNames = new ArrayList<String>();
        exportHeaderNames.add("预约用户");
        exportHeaderNames.add("预约电话");
        exportHeaderNames.add("预约IP");
        exportHeaderNames.add("预约产品");
        exportHeaderNames.add("预约来源");
        exportHeaderNames.add("预约时间");
        exportHeaderNames.add("预约信息描述");

        //excel内容
        List<List<Object>> exportData = new ArrayList<List<Object>>();
        // 文件下载
        response.setContentType("application/vnd.ms-excel");
        String filename = modelName + "报表(" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ")";

        //解决ie浏览器下载乱码问题
        String gbkFilename=null;
        String userAgent = request.getHeader("User-Agent");
        if (userAgent!=null&&userAgent.toUpperCase().indexOf("MSIE") > 0) {
            gbkFilename = URLEncoder.encode(filename, "UTF-8");
        } else {
            gbkFilename = new String(filename.getBytes("gbk"), "iso-8859-1");
        }
        response.setHeader("Content-disposition", "attachment;filename=" + gbkFilename + "." + excelType.toLowerCase());
        response.setBufferSize(1024);

    }


    /**
     * 导出投资列表
     * @param excelType excel格式
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"/excel/investment", "/excel/investment/{excelType}"}, method = RequestMethod.GET)
    public void exportInvestmentExcel(@PathVariable String excelType, String startDate, String endDate, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //默认导出xls格式excel
        if (excelType == null || "".equals(excelType)) {
            excelType = "XLS";
        }
        response.reset();
        String modelName = "投资";

        //excel表头数据
        List<String> exportHeaderNames = new ArrayList<String>();
        exportHeaderNames.add("产品名称");
        exportHeaderNames.add("投资用户");
        exportHeaderNames.add("投资金额（万）");
        exportHeaderNames.add("投资时间");
        exportHeaderNames.add("员工姓名");
        exportHeaderNames.add("返点（元）");
        exportHeaderNames.add("投资信息描述");

        //excel内容
        // 文件下载
        response.setContentType("application/vnd.ms-excel");
        String filename = modelName + "报表(" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ")";

        //解决ie浏览器下载乱码问题
        String gbkFilename = null;
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null && userAgent.toUpperCase().indexOf("MSIE") > 0) {
            gbkFilename = URLEncoder.encode(filename, "UTF-8");
        } else {
            gbkFilename = new String(filename.getBytes("gbk"), "iso-8859-1");
        }
        response.setHeader("Content-disposition", "attachment;filename=" + gbkFilename + "." + excelType.toLowerCase());
        response.setBufferSize(1024);

        //获取excel表单
    }
}
