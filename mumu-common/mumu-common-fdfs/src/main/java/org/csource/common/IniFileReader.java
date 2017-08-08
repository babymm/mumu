/**
* Copyright (C) 2008 Happy Fish / YuQing
*
* FastDFS Java Client may be copied only under the terms of the GNU Lesser
* General Public License (LGPL).
* Please visit the FastDFS Home Page http://www.csource.org/ for more detail.
**/

package org.csource.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import java.util.Set;

/**
 * ini file reader / parser
 * 
 * @author Happy Fish / YuQing
 * @version Version 1.0
 */
public class IniFileReader {
	private Map<String, Object> paramMap;
	private String conf_filename;

	private static final Logger log = Logger.getLogger(IniFileReader.class);

	/**
	 * @param conf_filename
	 *            config filename
	 */
	public IniFileReader(String conf_filename) throws FileNotFoundException, IOException {
		this.conf_filename = conf_filename;
		loadFromFile(conf_filename);
	}

	/**
	 * get the config filename
	 * 
	 * @return config filename
	 */
	public String getConfFilename() {
		return this.conf_filename;
	}

	/**
	 * get string value from config file
	 * 
	 * @param name
	 *            item name in config file
	 * @return string value
	 */
	@SuppressWarnings("rawtypes")
	public String getStrValue(String name) {
		Object obj;
		obj = this.paramMap.get(name);
		if (obj == null) {
			return null;
		}
		if (obj instanceof String) {
			return (String) obj;
		}
		return (String) ((ArrayList) obj).get(0);
	}

	/**
	 * get int value from config file
	 * 
	 * @param name
	 *            item name in config file
	 * @param default_value
	 *            the default value
	 * @return int value
	 */
	public int getIntValue(String name, int default_value) {
		String szValue = this.getStrValue(name);
		if (szValue == null) {
			return default_value;
		}

		return Integer.parseInt(szValue);
	}

	/**
	 * get boolean value from config file
	 * 
	 * @param name
	 *            item name in config file
	 * @param default_value
	 *            the default value
	 * @return boolean value
	 */
	public boolean getBoolValue(String name, boolean default_value) {
		String szValue = this.getStrValue(name);
		if (szValue == null) {
			return default_value;
		}

		return szValue.equalsIgnoreCase("yes") || szValue.equalsIgnoreCase("on") || szValue.equalsIgnoreCase("true")
				|| szValue.equals("1");
	}

	/**
	 * get all values from config file
	 * 
	 * @param name
	 *            item name in config file
	 * @return string values (array)
	 */
	@SuppressWarnings("rawtypes")
	public String[] getValues(String name) {
		Object obj;
		String[] values;

		obj = this.paramMap.get(name);
		if (obj == null) {
			return null;
		}

		if (obj instanceof String) {
			values = new String[1];
			values[0] = (String) obj;
			return values;
		}

		Object[] objs = ((ArrayList) obj).toArray();
		values = new String[objs.length];
		System.arraycopy(objs, 0, values, 0, objs.length);
		return values;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadFromFile(String conf_filename) throws FileNotFoundException, IOException {
		// FileReader fReader=null;
		BufferedReader buffReader;
		String line;
		String[] parts;
		String name;
		String value;
		Object obj;
		ArrayList valueList;

		// fReader = new FileReader(conf_filename);
		InputStream resourceAsStream = this.getClass().getResourceAsStream(conf_filename);
		InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
		buffReader = new BufferedReader(inputStreamReader);
		this.paramMap = new HashMap<String, Object>();

		try {
			while ((line = buffReader.readLine()) != null) {
				line = line.trim();
				if (line.length() == 0 || line.charAt(0) == '#') {
					continue;
				}

				parts = line.split("=", 2);
				if (parts.length != 2) {
					continue;
				}

				name = parts[0].trim();
				value = parts[1].trim();

				obj = this.paramMap.get(name);
				if (obj == null) {
					this.paramMap.put(name, value);
				} else if (obj instanceof String) {
					valueList = new ArrayList();
					valueList.add(obj);
					valueList.add(value);
					this.paramMap.put(name, valueList);
				} else {
					valueList = (ArrayList) obj;
					valueList.add(value);
				}
			}
			
			log.info("加载fdfs配置文件................................");
			Set<Entry<String, Object>> entrySet = paramMap.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				log.info(entry.getKey() + " " + entry.getValue());
			}
		} finally {
			// fReader.close();
			buffReader.close();
			inputStreamReader.close();
			resourceAsStream.close();
		}
	}
}
