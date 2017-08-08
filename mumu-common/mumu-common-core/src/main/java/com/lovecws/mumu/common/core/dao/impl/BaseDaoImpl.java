package com.lovecws.mumu.common.core.dao.impl;

import com.lovecws.mumu.common.core.dao.BaseDao;
import com.lovecws.mumu.common.core.exception.BizException;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc 据访问层基础支撑类 
 * @author ganliang
 * @version 2016年8月10日 上午9:38:31
 * @param <T>
 */
public abstract class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

    protected static final Log LOG = LogFactory.getLog(BaseDaoImpl.class);

    /**
     * 插入
     */
    public static final String SQL_INSERT = "insert";
    public static final String SQL_BATCH_INSERT = "batchInsert";
    
    /**
     * 更新
     */
    public static final String SQL_UPDATE_BY_ID = "updateByPrimaryKey";
    public static final String SQL_UPDATE_BY_COLUMN = "updateByColumn";
    public static final String SQL_BATCH_UPDATE_BY_IDS = "batchUpdateByIds";
    public static final String SQL_BATCH_UPDATE_BY_COLUMN = "batchUpdateByColumn";
    
    /**
     * 获取实体集合
     */
    private static final String SQL_GET_BY = "getBy";
    public static final String SQL_SELECT_BY_ID = "selectByPrimaryKey";
    private static final String SQL_GET_BY_COLUMN = "getByColumn";
    public static final String SQL_LIST_BY_COLUMN = "listByColumn";
    
    /**
     * 获取数量
     */
    public static final String SQL_COUNT_BY_COLUMN = "getCountByColumn";
    
    /**
     * 删除
     */
    public static final String SQL_DELETE_BY_ID = "deleteByPrimaryKey";
    public static final String SQL_BATCH_DELETE_BY_IDS = "batchDeleteByIds";
    public static final String SQL_BATCH_DELETE_BY_COLUMN = "batchDeleteByColumn";
    
    public static final String SQL_LIST_PAGE = "listPage";
    public static final String SQL_LIST_BY = "listBy";
    public static final String SQL_LIST_PAGE_COUNT = "listPageCount";
    public static final String SQL_COUNT_BY_PAGE_PARAM = "countByPageParam"; // 根据当前分页参数进行统计


    private static final  int BATCH_SQL_SIZE=20;
    /**
     * 注入SqlSessionTemplate实例(要求Spring中进行SqlSessionTemplate的配置).
     * 可以调用sessionTemplate完成数据库操作.
     */
    private SqlSessionTemplate sessionTemplate;

    public SqlSessionTemplate getSessionTemplate() {
        return sessionTemplate;
    }

    @Resource
    public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
        this.sessionTemplate = sessionTemplate;
    }
    
    @Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
    
    public SqlSession getSqlSession() {
        return super.getSqlSession();
    }

    /**
     * 单条插入数据.
     */
    public T insert(T entity) {
        int result = sessionTemplate.insert(getStatement(SQL_INSERT), entity);
        if (result <= 0) {

            throw BizException.DB_INSERT_RESULT_0.newInstance("数据库操作,insert返回0.{%s}", getStatement(SQL_INSERT));
        }
        return entity;
    }

    /**
     * 批量插入数据.
     */
    public List<T> insert(List<T> list) {
        if (list.isEmpty() || list.size() <= 0) {
            return null;
        }
        int result=0,total_size=list.size();
        if(total_size<=BATCH_SQL_SIZE){
        	result = sessionTemplate.insert(getStatement(SQL_BATCH_INSERT), list);
        	 if (result <= 0) {
                 throw BizException.DB_INSERT_RESULT_0.newInstance("数据库操作,batchInsert返回0.{%s}", getStatement(SQL_BATCH_INSERT));
             }
        }else{
        	int batch_count=total_size/BATCH_SQL_SIZE;
        	for (int i = 0; i < batch_count; i++) {
        		result = sessionTemplate.insert(getStatement(SQL_BATCH_INSERT), list.subList(i*BATCH_SQL_SIZE, (i+1)*BATCH_SQL_SIZE));
        		if (result <= 0) {
                    throw BizException.DB_INSERT_RESULT_0.newInstance("数据库操作,batchInsert返回0.{%s}", getStatement(SQL_BATCH_INSERT));
                }
			}
        	//有剩余
        	if(total_size%BATCH_SQL_SIZE!=0){
        		result = sessionTemplate.insert(getStatement(SQL_BATCH_INSERT), list.subList(batch_count*BATCH_SQL_SIZE, total_size));
        		if (result <= 0) {
                    throw BizException.DB_INSERT_RESULT_0.newInstance("数据库操作,batchInsert返回0.{%s}", getStatement(SQL_BATCH_INSERT));
                }
        	}
        }
        return list;
    }
    
    /**
     * 批量插入数据.
     */
    @Override
    public List<T> insert(List<T> list,int batchSize) {
    	if (list.isEmpty() || list.size() <= 0) {
    		return null;
    	}
    	int result=0,total_size=list.size();
    	if(total_size<=batchSize){
    		result = sessionTemplate.insert(getStatement(SQL_BATCH_INSERT), list);
    		if (result <= 0) {
    			throw BizException.DB_INSERT_RESULT_0.newInstance("数据库操作,batchInsert返回0.{%s}", getStatement(SQL_BATCH_INSERT));
    		}
    	}else{
    		int batch_count=total_size/batchSize;
    		for (int i = 0; i < batch_count; i++) {
    			result = sessionTemplate.insert(getStatement(SQL_BATCH_INSERT), list.subList(i*batchSize, (i+1)*batchSize));
    			if (result <= 0) {
    				throw BizException.DB_INSERT_RESULT_0.newInstance("数据库操作,batchInsert返回0.{%s}", getStatement(SQL_BATCH_INSERT));
    			}
    		}
    		//有剩余
    		if(total_size%batchSize!=0){
    			result = sessionTemplate.insert(getStatement(SQL_BATCH_INSERT), list.subList(batch_count*batchSize, total_size));
    			if (result <= 0) {
    				throw BizException.DB_INSERT_RESULT_0.newInstance("数据库操作,batchInsert返回0.{%s}", getStatement(SQL_BATCH_INSERT));
    			}
    		}
    	}
    	return list;
    }

    /**
     * 根据id单条更新数据.
     */
    public int update(T entity) {
        int result = sessionTemplate.update(getStatement(SQL_UPDATE_BY_ID), entity);
        if (result <= 0) {
            throw BizException.DB_UPDATE_RESULT_0.newInstance("数据库操作,updateByPrimaryKey返回0.{%s}", getStatement(SQL_UPDATE_BY_ID));
        }
        return result;
    }

    @Override
    public int updateByColumn(T entity) {
    	int result = sessionTemplate.update(getStatement(SQL_UPDATE_BY_COLUMN), entity);
        if (result <= 0) {
            throw BizException.DB_UPDATE_RESULT_0.newInstance("数据库操作,updateByPrimaryKey返回0.{%s}", getStatement(SQL_UPDATE_BY_ID));
        }
        return result;
    }
    
    /**
     * 根据id批量更新数据.
     */
    public int update(List<T> list) {
        if (list.isEmpty() || list.size() <= 0) {
            return 0;
        }
        int result = sessionTemplate.update(getStatement(SQL_BATCH_UPDATE_BY_IDS), list);
        if (result <= 0) {
            throw BizException.DB_UPDATE_RESULT_0.newInstance("数据库操作,batchUpdateByIds返回0.{%s}", getStatement(SQL_BATCH_UPDATE_BY_IDS));
        }
        return result;
    }

    /**
     * 根据column批量更新数据.
     */
    public int update(Map<String, Object> paramMap) {
        if (paramMap == null) {
            return 0;
        }
        int result = sessionTemplate.update(getStatement(SQL_BATCH_UPDATE_BY_COLUMN), paramMap);
        if (result <= 0) {
            throw BizException.DB_UPDATE_RESULT_0.newInstance("数据库操作,batchUpdateByColumn返回0.{%s}", getStatement(SQL_BATCH_UPDATE_BY_COLUMN));
        }
        return result;
    }

    /**
     * 根据id查询数据.
     */
    public T getById(String id) {
        return sessionTemplate.selectOne(getStatement(SQL_SELECT_BY_ID), id);
    }

    /**
     * 根据column查询数据.
     */
    public T getByColumn(Map<String, Object> paramMap) {
        if (paramMap == null) {
            return null;
        }
        return sessionTemplate.selectOne(getStatement(SQL_GET_BY_COLUMN), paramMap);
    }

    /**
     * 根据条件查询 getBy: selectOne <br/>
     * @param paramMap
     * @return
     */
    public T getBy(Map<String, Object> paramMap) {
        if (paramMap == null) {
            return null;
        }
        return sessionTemplate.selectOne(getStatement(SQL_GET_BY), paramMap);
    }
    
    /**
     * 根据条件查询列表数据.
     */
    public List<T> listBy(Map<String, Object> paramMap) {
        if (paramMap == null) {
            return null;
        }
        return sessionTemplate.selectList(getStatement(SQL_LIST_BY), paramMap);
    }

    /**
     * 根据column查询列表数据.
     */
    public List<T> listByColumn(Map<String, Object> paramMap) {
        if (paramMap == null) {
            return null;
        }
        return sessionTemplate.selectList(getStatement(SQL_LIST_BY_COLUMN), paramMap);
    }

    @Override
    public List<T> selectList(String statement, Map<String, Object> paramMap) {
    	if (paramMap == null) {
            return null;
        }
    	return sessionTemplate.selectList(getStatement(statement), paramMap);
    }
    
    /**
     * 根据column查询记录数.
     */
    @Override
    public Long getCountByColumn(Map<String, Object> paramMap) {
        if (paramMap == null) {
            return null;
        }
        return sessionTemplate.selectOne(getStatement(SQL_COUNT_BY_COLUMN), paramMap);
    }

    /**
     * 根据id删除数据.
     */
    @Override
    public int delete(String id) {
        return (int) sessionTemplate.delete(getStatement(SQL_DELETE_BY_ID), id);
    }

    /**
     * 根据id批量删除数据.
     */
    @Override
    public int delete(List<T> list) {
        if (list.isEmpty() || list.size() <= 0) {
            return 0;
        } else {
            return (int) sessionTemplate.delete(getStatement(SQL_BATCH_DELETE_BY_IDS), list);
        }
    }

    /**
     * 根据column批量删除数据.
     */
    @Override
    public int delete(Map<String, Object> paramMap) {
        if (paramMap == null) {
            return 0;
        } else {
            return (int) sessionTemplate.delete(getStatement(SQL_BATCH_DELETE_BY_COLUMN), paramMap);
        }
    }
    
    @Override
    public int listPageCount(Map<String, Object> paramMap){
    	if (paramMap == null) {
            return 0;
        }
    	Long totalCount = sessionTemplate.selectOne(getStatement(SQL_LIST_PAGE_COUNT), paramMap);
    	if(totalCount==null){
    		return 0;
    	}
    	return totalCount.intValue();
    }
    
    @Override
    public List<T> listSimplePage(PageParam pageParam,Map<String, Object> paramMap){
    	if (paramMap == null) {
            paramMap = new HashMap<String, Object>();
        }
    	// 根据页面传来的分页参数构造SQL分页参数
        paramMap.put("beginIndex", (pageParam.getCurrentPage() - 1) * pageParam.getNumPerPage());
        paramMap.put("numPerPage", pageParam.getNumPerPage());

        //pageFirst pageSize
        paramMap.put("pageFirst", (pageParam.getCurrentPage() - 1) * pageParam.getNumPerPage());
        paramMap.put("pageSize", pageParam.getNumPerPage());
    	return  sessionTemplate.selectList(getStatement(SQL_LIST_PAGE), paramMap);
    }
    
    /**
     * 分页查询数据 .
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public PageBean<T> listPage(PageParam pageParam, Map<String, Object> paramMap) {
        if (paramMap == null) {
            paramMap = new HashMap<String, Object>();
        }

        // 统计总记录数
        Long totalCount = sessionTemplate.selectOne(getStatement(SQL_LIST_PAGE_COUNT), paramMap);

        // 校验当前页数
        int currentPage = PageBean.checkCurrentPage(totalCount.intValue(), pageParam.getNumPerPage(), pageParam.getCurrentPage());
        pageParam.setCurrentPage(currentPage); // 为当前页重新设值
        // 校验页面输入的每页记录数numPerPage是否合法
        int numPerPage = PageBean.checkNumPerPage(pageParam.getNumPerPage()); // 校验每页记录数
        pageParam.setNumPerPage(numPerPage); // 重新设值

        // 根据页面传来的分页参数构造SQL分页参数
        paramMap.put("beginIndex", (pageParam.getCurrentPage() - 1) * pageParam.getNumPerPage());
        paramMap.put("numPerPage", pageParam.getNumPerPage());
        paramMap.put("startRowNum", (pageParam.getCurrentPage() - 1) * pageParam.getNumPerPage());
        paramMap.put("endRowNum", pageParam.getCurrentPage() * pageParam.getNumPerPage());

        //pageFirst pageSize
        paramMap.put("pageFirst", (pageParam.getCurrentPage() - 1) * pageParam.getNumPerPage());
        paramMap.put("pageSize", pageParam.getNumPerPage());

        // 获取分页数据集
        List<Object> list = sessionTemplate.selectList(getStatement(SQL_LIST_PAGE), paramMap);
        if(pageParam.getCurrentPage()>1){
        	if(list==null||list.size()==0){
        		pageParam.setCurrentPage(pageParam.getCurrentPage()-1);
                //pageFirst pageSize
                paramMap.put("pageFirst", (pageParam.getCurrentPage() - 1) * pageParam.getNumPerPage());
                paramMap.put("pageSize", pageParam.getNumPerPage());

        		paramMap.put("beginIndex", (pageParam.getCurrentPage() - 1) * pageParam.getNumPerPage());
                paramMap.put("numPerPage", pageParam.getNumPerPage());
                paramMap.put("startRowNum", (pageParam.getCurrentPage() - 1) * pageParam.getNumPerPage());
                paramMap.put("endRowNum", pageParam.getCurrentPage() * pageParam.getNumPerPage());
                list=sessionTemplate.selectList(getStatement(SQL_LIST_PAGE), paramMap);
            }
        }
        
        Object isCount = paramMap.get("isCount"); // 是否统计当前分页条件下的数据：1:是，其他为否
        if (isCount != null && "1".equals(isCount.toString())) {
            Map<String, Object> countResultMap = sessionTemplate.selectOne(getStatement(SQL_COUNT_BY_PAGE_PARAM), paramMap);
            return new PageBean(pageParam.getCurrentPage(), pageParam.getNumPerPage(), totalCount.intValue(), list, countResultMap);
        } else {
            // 构造分页对象
            return new PageBean(pageParam.getCurrentPage(), pageParam.getNumPerPage(), totalCount.intValue(), list);
        }
    }

    /**
     * 函数功能说明 ： 获取Mapper命名空间
     * @参数：@param sqlId
     * @参数：@return
     * @return：String
     * @throws
     */
    public String getStatement(String sqlId) {
        String name = this.getClass().getName();
        // 单线程用StringBuilder，确保速度；多线程用StringBuffer,确保安全
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(".").append(sqlId);
        return sb.toString();
    }
    
    /**
     * 函数功能说明 ： 获取Mapper命名空间
     * @参数：@param sqlId
     * @参数：@return
     * @return：String
     * @throws
     */
    public String getServiceStatement(String sqlId) {
    	//获取限制堆栈
    	StackTraceElement stack[] = Thread.currentThread().getStackTrace();  
    	//获取service全限定名称
    	String name = stack[1].getClassName();
    	// 单线程用StringBuilder，确保速度；多线程用StringBuffer,确保安全
    	StringBuilder sb = new StringBuilder();
    	sb.append(name).append(".").append(sqlId);
    	return sb.toString();
    }
}
