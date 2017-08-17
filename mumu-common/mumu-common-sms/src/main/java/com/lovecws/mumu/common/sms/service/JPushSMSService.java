package com.lovecws.mumu.common.sms.service;

import com.google.gson.Gson;
import com.lovecws.mumu.common.sms.exception.SMSException;
import com.lovecws.mumu.common.sms.util.Base64Coder;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;

/**
 * jpush短信服务
 * @author ganliang
 */
public class JPushSMSService {

	private static final String APPLICATION_JSON = "application/json";
	private static final Logger log = Logger.getLogger(JPushSMSService.class);
	private Gson gson=new Gson();
	/**
	 * 发送sms消息
	 * @param mobile 手机号码
	 * @return 返回消息id
	 * @throws SMSException 
	 */
	@SuppressWarnings("unchecked")
	public String sendSMS(String mobile) throws SMSException {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost httpPost = new HttpPost(smsUri);
			initHttpPost(httpPost);
			//将手机号 模板号发送到极光短信服务器
			httpPost.setEntity(new StringEntity("{\"mobile\":\""+mobile+"\",\"temp_id\":" + templateId + "}", "UTF-8"));
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			String result = EntityUtils.toString(httpResponse.getEntity());
			log.info("result " + result);
			//获取状态码
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			//请求成功
			if(statusCode==200){
				//将返回结果转化为json
				Map<String,String> jsonMap = gson.fromJson(result, Map.class);
				return jsonMap.get("msg_id");
			}else{
				throw new SMSException("短信发送异常,mobile="+mobile);
			}
		} catch (ClientProtocolException e) {
			throw new SMSException(e);
		} catch (IOException e) {
			throw new SMSException(e);
		}
	}

	/**
	 * 发送sms消息
	 *
	 * @param mobile       手机号码
	 * @param templateId   模板id
	 * @param templatePara 模板参数
	 * @return 返回消息id
	 * @throws SMSException
	 */
	@SuppressWarnings("unchecked")
	public String sendSMSWithTemplate(String mobile, String templateId, Map<String, String> templatePara) throws SMSException {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost httpPost = new HttpPost(smsUri);
			initHttpPost(httpPost);
			//将手机号 模板号发送到极光短信服务器
			httpPost.setEntity(new StringEntity("{\"mobile\":\"" + mobile + "\",\"temp_id\":" + templateId + ",\"temp_para\":" + gson.toJson(templatePara) + "}", "UTF-8"));
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			String result = EntityUtils.toString(httpResponse.getEntity());
			log.info("result " + result);
			//获取状态码
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			//请求成功
			if (statusCode == 200) {
				//将返回结果转化为json
				Map<String, String> jsonMap = gson.fromJson(result, Map.class);
				return jsonMap.get("msg_id");
			} else {
				throw new SMSException("短信发送异常,mobile=" + mobile);
			}
		} catch (ClientProtocolException e) {
			throw new SMSException(e);
		} catch (IOException e) {
			throw new SMSException(e);
		}
	}

	/**
	 * 验证sms消息
	 * @param msgId 发送消息的id
	 * @param code 手机收到的验证码
	 * @return {"is_valid":true}
	 * @throws SMSException 
	 */
	@SuppressWarnings("unchecked")
	public boolean validateMessage(String msgId, String code) throws SMSException {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(smsUri + "/" + msgId + "/valid");
		initHttpPost(httpPost);
		try {
			httpPost.setEntity(new StringEntity("{\"code\":\"" + code + "\"}", "UTF-8"));
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			String result = EntityUtils.toString(httpResponse.getEntity());
			log.info("result " + result);
			
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if(statusCode==200){
				//将返回结果转化为json
				Map<String,Boolean> jsonMap = gson.fromJson(result, Map.class);
				return jsonMap.get("is_valid");
			}
			return false;
		} catch (ClientProtocolException e) {
			throw new SMSException(e);
		} catch (IOException e) {
			throw new SMSException(e);
		}
	}

	/**
	 * 初始化httpPost 对httpPost添加请求头信息
	 * @param httpPost
	 */
	private void initHttpPost(HttpPost httpPost) {
		// 添加验证信息
		httpPost.addHeader("Authorization", "Basic " + Base64Coder.encodeString(appKey + ":" + masterSecret));
		httpPost.addHeader("Accept-Charset", "UTF-8");
		httpPost.addHeader("Charset", "UTF-8");
		httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
	}
	
	private String appKey;
	private String masterSecret;
	private String smsUri;
	private int templateId;
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}
	public void setSmsUri(String smsUri) {
		this.smsUri = smsUri;
	}
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	@Override
	public String toString() {
		return "JPushSMSService [appKey=" + appKey + ", masterSecret=" + masterSecret + ", smsUri=" + smsUri
				+ ", templateId=" + templateId + "]";
	}
}
