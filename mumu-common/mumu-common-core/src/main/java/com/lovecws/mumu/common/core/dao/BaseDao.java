package com.lovecws.mumu.common.core.dao;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;


/**
 * 数据访问层基础支撑接口.
 * @company：广州领课网络科技有限公司（龙果学院 www.roncoo.com）.
 */
public interface BaseDao<T> {

    /**
     * 函数功能说明 ：单条插入数据
     * @参数：@param entity
     * @参数：@return
     * @return：int
     * @throws
     */
    public T insert(T entity);

    /**
     * 函数功能说明 ： 批量插入数据.
     * @参数：@param list
     * @参数：@return
     * @return：int
     * @throws
     */
    public List<T>  insert(List<T> list);

    /**
     * 函数功能说明 ： 批量插入数据.
     * @参数：@param list
     * @参数：@param batchSize
     * @参数：@return
     * @return：int
     * @throws
     */
    public List<T> insert(List<T> list, int batchSize);
    
    /**
     * 函数功能说明 ：根据id单条更新数据.
     * @参数：@param entity
     * @参数：@return
     * @return：int
     * @throws
     */
    public int update(T entity);
    
    /**
     * 函数功能说明 ：字段更新数据
     * @参数：@param entity
     * @参数：@return
     * @return：int
     * @throws
     */
    public int updateByColumn(T entity);

    /**
     * 函数功能说明 ： 根据id批量更新数据.
     * @参数：@param list
     * @参数：@return
     * @return：int
     * @throws
     */
    public int update(List<T> list);

    /**
     * 函数功能说明 ： 根据column批量更新数据.
     * @参数：@param paramMap
     * @参数：@return
     * @return：int
     * @throws
     */
    public int update(Map<String, Object> paramMap);

    /**
     * 函数功能说明 ： 根据id查询数据.
     * @参数：@param id
     * @参数：@return
     * @return：T
     * @throws
     */
    public T getById(String id);

    /**
     * 函数功能说明 ： 根据column查询数据.
     * @参数：@param paramMap
     * @参数：@return
     * @return：T
     * @throws
     */
    public T getByColumn(Map<String, Object> paramMap);

    /**
     * 根据条件查询 listBy: <br/>
     * @param paramMap
     * @return 返回实体
     */
    public T getBy(Map<String, Object> paramMap);
    
    /**
     * 根据条件查询列表数据.
     */
    public List<T> listBy(Map<String, Object> paramMap);

    /**
     * 函数功能说明 ： 根据column查询列表数据. 
     * @参数：@param paramMap
     * @参数：@return
     * @return：List<T>
     * @throws
     */
    public List<T> listByColumn(Map<String, Object> paramMap);
    
    /**
     * 函数功能说明 ： 根据column查询列表数据. 
     * @参数：@param paramMap
     * @参数：@return
     * @return：List<T>
     * @throws
     */
    public List<T> selectList(String statement, Map<String, Object> paramMap);

    
    /**
     * 函数功能说明 ： 根据column查询记录数
     * @参数：@param paramMap
     * @参数：@return
     * @return：Long
     * @throws
     */
    Long getCountByColumn(Map<String, Object> paramMap);

    /**
     * 函数功能说明 ： 根据id删除数据. 修改者名字：
     * @参数：@param id
     * @参数：@return
     * @return：int
     * @throws
     */
    public int delete(String id);

    /**
     * 函数功能说明 ： 根据id批量删除数据. 修改者名字： 
     * @参数：@param list
     * @参数：@return
     * @return：int
     * @throws
     */
    public int delete(List<T> list);

    /**
     * 函数功能说明 ： 根据column批量删除数据. 
     * @参数：@param paramMap
     * @参数：@return
     * @return：int
     * @throws
     */
    public int delete(Map<String, Object> paramMap);

    /**
     * 函数功能说明 ： 分页查询数据 
     * @参数：@param pageParam
     * @参数：@param paramMap
     * @参数：@return
     * @return：PageBean
     * @throws
     */
	public PageBean<T> listPage(PageParam pageParam, Map<String, Object> paramMap);
    
    public SqlSessionTemplate getSessionTemplate();

    public SqlSession getSqlSession();

    public int listPageCount(Map<String, Object> paramMap);

    public List<T> listSimplePage(PageParam pageParam, Map<String, Object> paramMap);

}
