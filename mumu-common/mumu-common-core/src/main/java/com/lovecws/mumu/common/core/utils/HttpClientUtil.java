package com.lovecws.mumu.common.core.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;

public class HttpClientUtil {
	private static final Logger log = Logger.getLogger(HttpClientUtil.class);

	/**
	 * httpClient get 获取资源
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		log.info(url);
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet http = new HttpGet(url);
			CloseableHttpResponse response = httpClient.execute(http);
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * httpClient post 获取资源
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, Map<String, Object> params) {
		log.info(url);
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost httpPost = new HttpPost(url);
			if (params != null && params.size() > 0) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				Set<String> keySet = params.keySet();
				for (String key : keySet) {
					Object object = params.get(key);
					nvps.add(new BasicNameValuePair(key, object==null?null:object.toString()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * httpClient delete 删除资源
	 * @param url
	 * @return
	 */
	public static String delete(String url) {
		log.info(url);
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpDelete httpDelete = new HttpDelete(url);
			CloseableHttpResponse response = httpClient.execute(httpDelete);
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * httpClient 代理 delete 删除资源
	 * @param url
	 * @return
	 */
	public static String proxyDelete(String url, Map<String, Object> params) {
		if (params == null) {
			params = new HashMap<String, Object>();
		}
		params.put("_method", "delete");
		return post(url, params);
	}

	/**
	 * httpClient 代理 put 添加资源
	 * @param url
	 * @return
	 */
	public static String proxyPut(String url, Map<String, Object> params) {
		if (params == null) {
			params = new HashMap<String, Object>();
		}
		params.put("_method", "put");
		return post(url, params);
	}


	/**
	 * 文件上传
	 * @param url 上传路径
	 * @param file 上传文件
	 * @return
	 */
	public static String upload(String url, File file) {
		List<File> files=new ArrayList<File>();
		files.add(file);
		return uploads(url, files, null);
	}
	
	/**
	 * 文件上传
	 * @param url
	 * @return
	 */
	public static String upload(String url,Map<String, Object> paramMap) {
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost httpPost = new HttpPost(url);
			
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			// 封装参数
			if (paramMap != null && paramMap.size() > 0) {
				for (Entry<String, Object> entry : paramMap.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					if(value instanceof File){
						File file=(File)value;
						//builder.addBinaryBody( key, file, ContentType.MULTIPART_FORM_DATA, file.getName());
						builder.addPart(key, new FileBody(file));
					}else{
						if(value!=null){
							builder.addTextBody(key,value.toString(),ContentType.create("application/x-www-form-urlencoded", "UTF-8"));
						}
					}
				}
			}
			builder.setCharset(Charset.forName("utf-8"));
			builder.setContentType(ContentType.MULTIPART_FORM_DATA);
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 文件上传
	 * @param url
	 * @return
	 */
	public static String uploads(String url, List<File> files, Map<String, Object> paramMap) {
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost httpPost = new HttpPost(url);

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			// 封装文件
			if (files != null && files.size() > 0) {
				for (File file : files) {
					builder.addBinaryBody(UUID.randomUUID().toString(), file, ContentType.MULTIPART_FORM_DATA, file.getName());
				}
			}
			// 封装参数
			if (paramMap != null && paramMap.size() > 0) {
				for (Entry<String, Object> entry : paramMap.entrySet()) {
					builder.addTextBody(entry.getKey(), entry.getValue().toString(),
							ContentType.create("application/x-www-form-urlencoded", "UTF-8"));
				}
			}
			builder.setCharset(Charset.forName("utf-8"));
			builder.setContentType(ContentType.MULTIPART_FORM_DATA);
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 检测连接
	 * @param url
	 * @return
	 */
	public static boolean ping(String url){
		log.info(url);
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet http = new HttpGet(url);
			CloseableHttpResponse response = httpClient.execute(http);
			int statusCode = response.getStatusLine().getStatusCode();
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			return statusCode==200&&responseString!=null&&responseString.length()>0;
		} catch (Exception e) {
			log.error(e);
		}
		return false;
	}
	
	public static void main(String[] args) {
		boolean ping = ping("http://img.xgo-img.com.cn/pics/3154/630/473/3153909.jpg");
		System.out.println(ping);
	}
}
