package com.hc.sys.common.util.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * HTTP的POST方式发送请求
 * HTTP POST数据到指定的URL
 * 
 * @author tongpuxin
 */
public class HttpPostUtil {

	/**
	 * 通过http发送post请求
	 * @param url		URL
	 * @param params	参数
	 * @param charset	字符集
	 * @return 请求结果
	 */
	public static String post(String url, HashMap<String, String> params, String charset) {
		String result = null;
		try {
			HttpClient httpclient = new HttpClient();// 方法调用
			PostMethod post = new PostMethod(url);// 接口地址
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);// 格式转换
			if(params != null && !params.isEmpty()){
				Set<String> keys = params.keySet();
				for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
					String name = iterator.next();
					post.addParameter(name, params.get(name));
				}
			}
			httpclient.executeMethod(post);
			// 得到结果
			result = new String(post.getResponseBody(), charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 通过http发送post请求，采用GBK编码
	 * @param url		URL
	 * @param params	参数
	 * @return 请求结果
	 */
	public static String postGbk(String url, HashMap<String, String> params) {
		String charset = "gbk";
		return post(url, params, charset);
	}
	
	/**
	 * 通过http发送post请求，采用UTF8编码
	 * @param url		URL
	 * @param params	参数
	 * @return 请求结果
	 */
	public static String postUtf8(String url, HashMap<String, String> params) {
		String charset = "utf-8";
		return post(url, params, charset);
	}
	
	/**
	 * 通过http请求post数据
	 * @param url		post数据的URL
	 * @param postData	要post过去的数据
	 * @param contentType	内容类型(Content-Type)
	 * @return 请求结果
	 */
	public static String postData(String url, String postData, String contentType) {
    	try {
			HttpPost httpPost = new HttpPost(url);
			StringEntity postEntity = new StringEntity(postData);
			httpPost.setHeader("Accept", contentType);
			httpPost.addHeader("Content-Type", contentType);
			httpPost.setEntity(postEntity);
			CloseableHttpClient httpClient = HttpClients.createDefault();// 方法调用
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "UTF-8");
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 通过http请求post XML格式的数据
	 * @param url		post数据的URL
	 * @param postData	要post过去的数据
	 * @return 请求结果
	 */
	public static String postDataXML(String url, String postData) {
		String contentType = "text/xml";
		return postData(url, postData, contentType);
	}
	
	/**
	 * 通过http请求post JSON格式的数据
	 * @param url		post数据的URL
	 * @param postData	要post过去的数据
	 * @return 请求结果
	 */
	public static String postDataJSON(String url, String postData) {
		String contentType = "application/json";
		return postData(url, postData, contentType);
	}
}
