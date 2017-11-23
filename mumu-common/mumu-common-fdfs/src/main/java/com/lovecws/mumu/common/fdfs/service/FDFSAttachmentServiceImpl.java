package com.lovecws.mumu.common.fdfs.service;

import com.lovecws.mumu.common.fdfs.bean.FDFSFile;
import com.lovecws.mumu.common.fdfs.config.FDFSConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 分布式文件系统fastdfs
 * @author ganliang
 */
public class FDFSAttachmentServiceImpl implements FDFSAttachmentService {

	private static final Logger log = Logger.getLogger(FDFSAttachmentServiceImpl.class);

	private static TrackerClient trackerClient;
	private static TrackerServer trackerServer;

	private static StorageServer storageServer;
	private static StorageClient storageClient;

	@Override
	public String[] upload(String[] filePaths) {
		File[] files=new File[filePaths.length];
		for (int i = 0; i < filePaths.length; i++) {
			files[i]=new File(filePaths[i]);
		}
		return upload(files);
	}
	
	@Override
	public String[] upload(List<String> filePaths) {
		File[] files=new File[filePaths.size()];
		for (int i = 0; i < filePaths.size(); i++) {
			files[i]=new File(filePaths.get(i));
		}
		return upload(files);
	}
	
	@Override
	public String[] upload(File[] files) {
		if (files == null || files.length < 1) {
			throw null;
		}
		String[] urls = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			urls[i] = this.upload(files[i]);
		}
		return urls;
	}
	
	@Override
	public String upload(String fileName) {
		return upload(new File(fileName));
	}

	@Override
	public String upload(File file) {
		FDFSFile fdfsFile=null;
		try {
			byte[] content = FileUtils.readFileToByteArray(file);
			String extension = FilenameUtils.getExtension(file.getName());
			fdfsFile = new FDFSFile(file.getName(), content, extension);
			return this.upload(fdfsFile);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public String uploadWithUrl(File file) {
		String upload = upload(file);
		if(upload==null){
			return null;
		}
		return webPath+"/"+upload;
	}

	@Override
	public String upload(FDFSFile fdfsFile) {
		// 设置文件的宽、高、作者
		NameValuePair[] meta_list=null;
		if(metaData){
			meta_list = new NameValuePair[3];
			meta_list[0] = new NameValuePair("width", fdfsFile.getWidth());
			meta_list[1] = new NameValuePair("heigth", fdfsFile.getHeight());
			meta_list[2] = new NameValuePair("author", fdfsFile.getAuthor());
		}

		try {
			String[] upload_file = storageClient.upload_file(fdfsFile.getContent(), fdfsFile.getExt(), meta_list);
			return upload_file[0] + FDFSConfig.SEPARATOR + upload_file[1];
		} catch (IOException|MyException e) {
			try{
				//检测trackerServer 是否连通 如果没连通 则重新连接
				boolean isActive = ProtoCommon.activeTest(trackerServer.getSocket());
				if(!isActive){
					trackerClient = new TrackerClient();
					trackerServer = trackerClient.getConnection();
					storageClient = new StorageClient(trackerServer, storageServer);
				}
				String[] upload_file = storageClient.upload_file(fdfsFile.getContent(), fdfsFile.getExt(), meta_list);
				return upload_file[0] + FDFSConfig.SEPARATOR + upload_file[1];
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public byte[] download(String groupName, String remoteFileName)  {
		try {
			return storageClient.download_file(groupName, remoteFileName);
		} catch (IOException|MyException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(String groupName, String remoteFileName) {
		try {
			int delete_file = storageClient.delete_file(groupName, remoteFileName);
			return delete_file == 0;
		} catch (IOException | MyException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public FileInfo getFileInfo(String groupName, String remoteFileName)  {
		try {
			return storageClient.get_file_info(groupName, remoteFileName);
		} catch (IOException | MyException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 初始化fastdfs连接
	 */
	public void init() {
		try {
			if(configPath==null){
				throw new IllegalArgumentException("fastdfs配置文件不存在");
			}
			if(!configPath.startsWith("/")){
				configPath="/"+configPath;
			}
			//String path = FDFSAttachmentServiceImpl.class.getResource(configPath).getPath();
			/*if(path==null){
				throw new IllegalArgumentException("fastdfs配置文件 not found");
			}*/
			/*String classPath = new File(FDFSAttachmentServiceImplTest.class.getResource("/").getFile())
					.getCanonicalPath();
			String fdfsClientConfigFilePath = classPath + File.separator + FDFSConfig.CLIENT_CONFIG_FILE;*/
			//path = path.replace("!", "");
			log.info("读取fdfs配置文件:" + configPath);

			ClientGlobal.init(configPath,tracker_server);

			trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();

			//测试trackerServer是否连通
			ProtoCommon.activeTest(trackerServer.getSocket());

			storageClient = new StorageClient(trackerServer, storageServer);
			log.info("fastdfs分布式文件系统初始化完毕");
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} catch (MyException e) {
			log.error(e);
		}
	}

	@Override
	public void destory() {
		try {
			if (storageServer != null) {
				storageServer.close();
			}
			if (trackerServer != null) {
				trackerServer.close();
			}
		} catch (IOException e) {
			log.error(e);
		}
	}

	private String configPath;//fastdfs 客户端配置文件
	private String webPath;//web服务器地址
	private boolean metaData=false;//是否保存文件属性信息
	private String tracker_server;
	public String getConfigPath() {
		return configPath;
	}
	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}
    
	@SuppressWarnings("unused")
	private FDFSAttachmentServiceImpl() {
		super();
	}
	public FDFSAttachmentServiceImpl(String configPath) {
		this.configPath = configPath;
	}

	public String getWebPath() {
		return webPath;
	}

	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}

	public boolean isMetaData() {
		return metaData;
	}

	public void setMetaData(boolean metaData) {
		this.metaData = metaData;
	}

	public String getTracker_server() {
		return tracker_server;
	}

	public void setTracker_server(String tracker_server) {
		this.tracker_server = tracker_server;
	}
}
