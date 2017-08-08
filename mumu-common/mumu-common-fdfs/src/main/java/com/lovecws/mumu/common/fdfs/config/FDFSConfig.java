package com.lovecws.mumu.common.fdfs.config;

import java.io.Serializable;

/**
 * @author ganliang
 */
public interface FDFSConfig extends Serializable {

	public static final String FILE_DEFAULT_WIDTH = "120";
	public static final String FILE_DEFAULT_HEIGHT = "120";
	public static final String FILE_DEFAULT_AUTHOR = "lgan";

	public static final String PROTOCOL = "http://";
	public static final String SEPARATOR = "/";

	public static final String TRACKER_NGNIX_PORT = "80";

	public static final String CLIENT_CONFIG_FILE = "fdfs_client.conf";

}