package com.lovecws.mumu.system.dao.impl;

import com.lovecws.mumu.system.dao.SysCommonDao;
import com.lovecws.mumu.system.entity.SysDBField;
import com.lovecws.mumu.system.entity.SysDBTable;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommonDaoImpl extends SqlSessionDaoSupport implements SysCommonDao {

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
	 * @return 返回数据库所有的 TABLE
	 */
	@Override
	public List<SysDBTable> getAllTable() {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<SysDBTable> list = new ArrayList<SysDBTable>();
		try {
			connection = sessionTemplate.getConnection();
			DatabaseMetaData metaData = connection.getMetaData();
			// 获取所有类型为 TABLE 的表
			rs = metaData.getTables(null, null, null, new String[] { "TABLE" });
			while (rs.next()) {
				String TABLE_NAME = rs.getString("TABLE_NAME");
				// 去除一些系统表(以SQL_开头)
				if (!TABLE_NAME.startsWith("SQL_")) {
					list.add(new SysDBTable(rs));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (connection != null) {
					//connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @param tableName 表名
	 * @return 获取一个表的所有字段的属性
	 */
	@Override
	public List<SysDBField> getAllField(String tableName) {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<SysDBField> list = new ArrayList<SysDBField>();
		try {
			connection = sessionTemplate.getConnection();
			DatabaseMetaData metaData = connection.getMetaData();
			rs = metaData.getColumns(null, null, tableName, null);
			while (rs.next()) {
				list.add(new SysDBField(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (connection != null) {
					//connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 获取一个表的所有数据
	 * @param tableName 表名
	 * @param fields 字段
	 * @param params 参数
	 * @return
	 */
	@Override
	public List<List<Object>> getAllData(String tableName,String fields,String params) {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<List<Object>> list = new ArrayList<List<Object>>();
		try {
			connection=sessionTemplate.getConnection();
			if(fields==null||"".equals(fields)){ fields=" * ";}
			if(params==null||"".equals(params)){ params="";}
			pst = connection.prepareStatement("SELECT "+fields+" FROM " + tableName + params);
			rs = pst.executeQuery();
			// 获取metadata属性
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				List<Object> map = new ArrayList<Object>();
				// 循环 将一条记录保存到一个map中
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					// TODO 当把 表的数据 blob clob 保存到内存中的时候 可能会报内存溢出一场
					map.add(rs.getString(i));
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (connection != null) {
					//connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
