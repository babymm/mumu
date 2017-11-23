package com.lovecws.mumu.common.core.log;


import com.lovecws.mumu.common.core.entity.PersistentEntity;

import java.util.Date;

/**
 * @author babymm
 * @version 1.0-SNAPSHOT
 * @Description: 日志组件
 * @date 2017-11-22 10:38
 */
public class MumuLogEntity extends PersistentEntity {
    private String typename;//类名
    private String method;//方法
    private String parameter;//参数
    private String result;//返回结果
    private String usetime;//接口花费时间
    private Date logtime;//日志记录时间
    private String name;//日志名称
    private String operater;//请求方法

    public MumuLogEntity(final String typename, final String method, final String parameter, final String result, final String usetime, final Date logtime, final String name, final String operater) {
        this.typename = typename;
        this.method = method;
        this.parameter = parameter;
        this.result = result;
        this.usetime = usetime;
        this.logtime = logtime;
        this.name = name;
        this.operater = operater;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(final String typename) {
        this.typename = typename;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(final String parameter) {
        this.parameter = parameter;
    }

    public String getResult() {
        return result;
    }

    public void setResult(final String result) {
        this.result = result;
    }

    public String getUsetime() {
        return usetime;
    }

    public void setUsetime(final String usetime) {
        this.usetime = usetime;
    }

    public Date getLogtime() {
        return logtime;
    }

    public void setLogtime(final Date logtime) {
        this.logtime = logtime;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(final String operater) {
        this.operater = operater;
    }
}
