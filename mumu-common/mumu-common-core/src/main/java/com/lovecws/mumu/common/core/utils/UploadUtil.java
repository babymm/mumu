package com.lovecws.mumu.common.core.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @desc 文件上传
 * @author ganliang
 * @version 2016年8月15日 上午9:20:26
 */
public final class UploadUtil {
	private UploadUtil() {
	}

	private static final Logger logger = Logger.getLogger(UploadUtil.class);

	/** 上传文件缓存大小限制 */
	private static int fileSizeThreshold = 1024 * 1024 * 1;
	/** 上传文件临时目录 */
	private static final String uploadFileDir = "/upload/";

	/** 获取所有文本域 */
	public static final List<?> getFileItemList(HttpServletRequest request, File saveDir) throws FileUploadException {
		if (!saveDir.isDirectory()) {
			saveDir.mkdir();
		}
		List<?> fileItems = null;
		RequestContext requestContext = new ServletRequestContext(request);
		if (FileUpload.isMultipartContent(requestContext)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(saveDir);
			factory.setSizeThreshold(fileSizeThreshold);
			ServletFileUpload upload = new ServletFileUpload(factory);
			fileItems = upload.parseRequest(request);
		}
		return fileItems;
	}

	/** 获取文本域 */
	public static final FileItem[] getFileItem(HttpServletRequest request, File saveDir, String... fieldName)
			throws FileUploadException {
		if (fieldName == null || saveDir == null) {
			return null;
		}
		List<?> fileItemList = getFileItemList(request, saveDir);
		FileItem fileItem = null;
		FileItem[] fileItems = new FileItem[fieldName.length];
		for (int i = 0; i < fieldName.length; i++) {
			for (Iterator<?> iterator = fileItemList.iterator(); iterator.hasNext();) {
				fileItem = (FileItem) iterator.next();
				// 根据名字获得文本域
				if (fieldName[i] != null && fieldName[i].equals(fileItem.getFieldName())) {
					fileItems[i] = fileItem;
					break;
				}
			}
		}
		return fileItems;
	}

	/** 上传文件处理(支持批量)
	 * @throws IOException
	 * @throws IllegalStateException */
	public static List<String> uploadImage(HttpServletRequest request) throws IllegalStateException, IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		List<String> fileNames = InstanceUtil.newArrayList();
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iterator = multiRequest.getFileNames();
			String pathDir = request.getSession().getServletContext()
					.getRealPath(uploadFileDir + DateUtils.currentTime());
			File dirFile = new File(pathDir);
			if (!dirFile.isDirectory()) {
				dirFile.mkdirs();
			}
			while (iterator.hasNext()) {
				String key = iterator.next();
				MultipartFile multipartFile = multiRequest.getFile(key);
				if (multipartFile != null) {
					String name = multipartFile.getOriginalFilename();
					// 默认文件格式为png图片
					if (name.indexOf(".") == -1 && "blob".equals(name)) {
						name = name + ".png";
					}
					String uuid = UUID.randomUUID().toString().replace("-", "");
					int lastIndexOf = name.lastIndexOf(".");
					if(lastIndexOf==-1){
						name=name + ".png";
					}
					lastIndexOf = name.lastIndexOf(".");
					String postFix=name.substring(lastIndexOf).toLowerCase();
					String fileName = uuid + postFix;
					String filePath = pathDir + File.separator + fileName;
					File file = new File(filePath);
					file.setWritable(true, false);

					multipartFile.transferTo(file);
					fileNames.add(file.getAbsolutePath());
				}
			}
		}
		return fileNames;
	}

    /**
     * 上传文件处理(支持批量)
     * @param request
     * @param pathDir 上传文件保存路径
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
	public static List<String> uploadFile(HttpServletRequest request,String pathDir) throws IllegalStateException, IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		List<String> fileNames = InstanceUtil.newArrayList();
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iterator = multiRequest.getFileNames();
			if(pathDir==null|| pathDir.equals("")){
				pathDir = request.getSession().getServletContext().getRealPath(uploadFileDir + DateUtils.currentTime());
			}
            File dirFile = new File(pathDir);
            if (!dirFile.isDirectory()) {
                dirFile.mkdirs();
            }
			while (iterator.hasNext()) {
				String key = iterator.next();
				MultipartFile multipartFile = multiRequest.getFile(key);
				if (multipartFile != null) {
					String uuid = UUID.randomUUID().toString().replace("-", "");
					String name = multipartFile.getOriginalFilename();
					int lastIndexOf = name.lastIndexOf(".");
					String postFix="";
					if(lastIndexOf!=-1){
						postFix = name.substring(lastIndexOf).toLowerCase();
					}
					String fileName = uuid + postFix;
					String filePath = pathDir + File.separator + fileName;
					File file = new File(filePath);
					file.setWritable(true, false);

					multipartFile.transferTo(file);
					fileNames.add(file.getAbsolutePath());
				}
			}
		}
		return fileNames;
	}

    /**
     * 上传文件处理(支持批量)
     * @throws IOException
     * @throws IllegalStateException
     */
    public static List<String> uploadFile(HttpServletRequest request) throws IllegalStateException, IOException {
        return uploadFile(request,null);
    }

	/** 获取上传文件临时目录 */
	public static String getUploadDir(HttpServletRequest request) {
		return request.getServletContext().getRealPath(uploadFileDir);
	}

	/**
	 * 缩放文件大小
	 * @param file
	 */
	public static void scale(File file) {
		try { // 缩放
			BufferedImage bufferedImg = ImageIO.read(file);
			int orgwidth = bufferedImg.getWidth();// 原始宽度
			ImageUtil.scaleWidth(file, 100);
			if (orgwidth > 300) {
				ImageUtil.scaleWidth(file, 300);
			}
			if (orgwidth > 500) {
				ImageUtil.scaleWidth(file, 500);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
