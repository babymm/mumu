package com.lovecws.mumu.common.fdfs.service;

import com.lovecws.mumu.common.fdfs.bean.FDFSFile;
import org.csource.fastdfs.FileInfo;

import java.io.File;
import java.util.List;

public interface FDFSAttachmentService {
	
	/**
	 * 保存多个文件
	 * @param files 文件数组
	 * @return
	 * @
	 */
	public String[] upload(File[] files) ;
	
	/**
	 * 保存多个文件
	 * @param files 文件数组
	 * @return
	 * @
	 */
	public String[] upload(String[] files) ;
	
	/**
	 * 保存多个文件
	 * @param files 文件数组
	 * @return
	 * @
	 */
	public String[] upload(List<String> files) ;
	
	/**
	 * 文件上传
	 * @param file 文件
	 * @return
	 * @ 
	 */
	public String upload(File file) ;

	/**
	 * 文件上传 返回值携带web路径
	 * @param file 文件
	 * @return
	 * @
	 */
	public String uploadWithUrl(File file);
	
	/**
	 * fdfs文件上传
	 * @param filePath
	 * @return
	 * @
	 */
	public String upload(String filePath) ;
	
	
	/**
	 * fdfs文件上传
	 * @param fdfsFile
	 * @return
	 * @
	 */
	public String upload(FDFSFile fdfsFile) ;
	
	/**
	 * 下载文件
	 * @param groupName 组名
	 * @param remoteFileName 文件名
	 * @ 
	 */
	public byte[] download(String groupName, String remoteFileName) ;

	/**
	 * 删除文件
	 * @param groupName 组名
	 * @param remoteFileName 文件名
	 * @return
	 * @ 
	 */
	public boolean delete(String groupName, String remoteFileName) ;
	
	/**
	 * 获取文件的信息
	 * @param groupName
	 * @param remoteFileName
	 * @return
	 * @
	 */
	public FileInfo getFileInfo(String groupName, String remoteFileName) ;

	/**
	 * 初始化 fastdfs
	 */
	public void init();
	
	/**
	 * 释放fastdfs资源 
	 */
	public void destory();
}
