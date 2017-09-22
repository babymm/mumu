package com.lovecws.mumu.common.core.dao;

import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MapperScanApplication {

	private static final Logger log=Logger.getLogger(MapperScanApplication.class);
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	private String configLocation;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}

	public void init() throws IOException, SAXException, ParserConfigurationException, ClassNotFoundException {
		log.info("解析"+configLocation);
		// 解析xml
		InputStream inputStream=MapperScanApplication.class.getClassLoader().getResourceAsStream(configLocation);
		
		Configuration configuration = sqlSessionFactory.getConfiguration();

		if(inputStream==null){
			return;
		}
		XPathParser xPathParser = new XPathParser(inputStream);
		XNode root = xPathParser.evalNode("/configuration");

		handleSetting(configuration, root);

		handleTypeAlias(configuration, root);

		handleMappers(configuration, root);
	}

	/**
	 * 加载配置文件属性
	 * 
	 * @param configuration
	 * @param root
	 */
	private void handleSetting(Configuration configuration, XNode root) {
		XNode settingsNode = root.evalNode("settings");
		log.info("加載配置信息..........................");
		if(settingsNode!=null){
			Properties props = settingsNode.getChildrenAsProperties();
			
			Boolean cacheEnabled = booleanValueOf(props.getProperty("cacheEnabled"), true);
			configuration.setCacheEnabled(cacheEnabled);
			log.info("cacheEnabled:"+cacheEnabled);
		}
	}
	protected Boolean booleanValueOf(String value, Boolean defaultValue) {
		return value == null ? defaultValue : Boolean.valueOf(value);
	}

	/**
	 * 加载别名
	 * @param configuration
	 */
	private void handleTypeAlias(Configuration configuration, XNode root) {
		log.info("加載別名信息..........................");
		TypeAliasRegistry typeAliasRegistry = configuration.getTypeAliasRegistry();
		XNode parent = root.evalNode("typeAliases");
		if(parent!=null){
			for (XNode child : parent.getChildren()) {
				if ("package".equals(child.getName())) {
					String typeAliasPackage = child.getStringAttribute("name");
					configuration.getTypeAliasRegistry().registerAliases(typeAliasPackage);
					log.info("package:"+typeAliasPackage);
				} else {
					String alias = child.getStringAttribute("alias");
					String type = child.getStringAttribute("type");
					try {
						Class<?> clazz = Resources.classForName(type);
						if (alias == null) {
							typeAliasRegistry.registerAlias(clazz);
						} else {
							typeAliasRegistry.registerAlias(alias, clazz);
						}
						log.info("alias:"+alias+"   type:"+clazz);
					} catch (ClassNotFoundException e) {
						throw new BuilderException("Error registering typeAlias for '" + alias + "'. Cause: " + e, e);
					}
				}
			}
		}
	}

	/**
	 * 加载mapper
	 * @param configuration
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void handleMappers(Configuration configuration, XNode root) throws IOException, ClassNotFoundException {
		log.info("加載Mapper信息..........................");
		XNode parent = root.evalNode("mappers");
		if (parent != null) {
			for (XNode child : parent.getChildren()) {
				if ("package".equals(child.getName())) {
					String mapperPackage = child.getStringAttribute("name");
					configuration.addMappers(mapperPackage);
					log.info("package:"+mapperPackage);
				} else {
					String resource = child.getStringAttribute("resource");
					String url = child.getStringAttribute("url");
					String mapperClass = child.getStringAttribute("class");
					if (resource != null && url == null && mapperClass == null) {
						log.info("resource:"+resource);
						ErrorContext.instance().resource(resource);
						InputStream inputStream = Resources.getResourceAsStream(resource);
						XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource,
								configuration.getSqlFragments());
						mapperParser.parse();
					} else if (resource == null && url != null && mapperClass == null) {
						log.info("url:"+url);
						ErrorContext.instance().resource(url);
						InputStream inputStream = Resources.getUrlAsStream(url);
						XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, url,
								configuration.getSqlFragments());
						mapperParser.parse();
					} else if (resource == null && url == null && mapperClass != null) {
						log.info("mapperClass:"+mapperClass);
						Class<?> mapperInterface = Resources.classForName(mapperClass);
						configuration.addMapper(mapperInterface);
					} else {
						throw new BuilderException(
								"A mapper element may only specify a url, resource or class, but not more than one.");
					}
				}
			}
		}
	}
}
