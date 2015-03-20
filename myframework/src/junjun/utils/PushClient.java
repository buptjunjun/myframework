package junjun.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.params.CookiePolicy;

import com.google.gson.Gson;

public class PushClient
{
	public static final int GET = 0;
	public static final int POST = 1;
	
	private static MultiThreadedHttpConnectionManager connectionManager = null;
	private static int maxConPerHost = 20;
	private static int conTimeOutMs = 2000;
	private static int maxTotalCon = 100;
	
	static {
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
		System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire.header", "debug");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");
		
		connectionManager = new MultiThreadedHttpConnectionManager();;
		HttpConnectionManagerParams params = connectionManager.getParams();
		params.setDefaultMaxConnectionsPerHost(maxConPerHost);
		params.setConnectionTimeout(conTimeOutMs);
		params.setMaxTotalConnections(maxTotalCon);
	}
	//private int soTimeOutMs = ;
	//private int maxSize;
	
	
	private PushClient() {
		
		//params.setSoTimeout(soTimeOutMs);
	}
	
	public static PushClient getInstance()
	{
		return new PushClient();
	}
	
	private PostMethod getPostMethod(String url, Map<String, String> params,String charset)
	{
		L.logger.info("request:");
		L.logger.info("post url:"+url);
		
		PostMethod  postMethod = new UTF8PostMethod(url);
		if(params!=null)
		{
			for(Map.Entry<String, String> entry : params.entrySet())
			{
				String key = entry.getKey();
				String value = entry.getValue();
				postMethod.addParameter(key, value);			
			}
		}
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		return postMethod;	
	}
	
	
	private GetMethod getGetMethod(String url, Map<String, String> params,String charset)
	{
		
		L.logger.info("request:");
		L.logger.info("request url:"+url);

		if(params!=null)
		{
			for(Map.Entry<String, String> entry : params.entrySet())
			{
				String key = entry.getKey();
				String value = entry.getValue();
				
				if(url.indexOf("?") == -1)
					url+="?"+key+"="+value;
				else
					url+="&"+key+"="+value+"";			
			}
		}
		
		GetMethod  getMethod = new GetMethod(url);
		return getMethod;	
	}
	
	private static HttpClient getHttpClient() 
	{		
		HttpClientParams clientParams = new HttpClientParams();
		// 忽略cookie 避免 Cookie rejected 警告
		clientParams.setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		HttpClient client = new HttpClient(clientParams,connectionManager);
		client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		return client;	
	}
	
	
	public ResponseData push(String url, Map<String, String> params, int method)
	{
		HttpMethod httpMethod = null;
		ResponseData resdata = new ResponseData();
		
		if(method == GET)
		{
			httpMethod = getGetMethod(url, params, "utf-8");
		}
		else if(method == POST)
		{
			httpMethod = getPostMethod(url, params, "utf-8");
		}
		else 
		{
			return resdata;
		}
		
		
		HttpClient client = getHttpClient();
		try {
			client.executeMethod(httpMethod);
			int statusCode = client.executeMethod(httpMethod);
			
			if (statusCode != HttpStatus.SC_OK) 
			{  	          
				L.logger.warn("Method failed: " + httpMethod.getStatusLine());  
	        }  
			String response = httpMethod.getResponseBodyAsString();
			
			resdata.setHttpStatus(statusCode);
			resdata.setData(response);
			return resdata;
			
		} catch (HttpException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return resdata;
		
	}
	
	
	private static class UTF8PostMethod extends PostMethod 
	{ 
		public UTF8PostMethod(String url) { 
		super(url); 
		} 

		@Override 
		public String getRequestCharSet() { 
		//return super.getRequestCharSet(); 
		return "UTF-8"; 
		} 
	}
	
	public static void main(String [] args)
	{
		
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "stdout");
		//String url = "http://talent.baicizhan.com/services/labels";
		String url = "http://112.124.4.53/words/push_talent_data_test";
//		HttpClient client = getHttpClient();
//		GetMethod getMethod = getGetMethod(url, null, "utf8");
//		try {
//			int statusCode = client.executeMethod(getMethod);
//			if (statusCode != HttpStatus.SC_OK) 
//			{  	          
//				System.err.println("Method failed: " + getMethod.getStatusLine());  
//	        }  
//	           
//	         /*获得返回的结果*/  
//	         byte[] responseBody = getMethod.getResponseBody();  
//	         System.out.println("getMethod:");
//	         System.out.println(statusCode+":"+new String(responseBody));  
//	       
//		} catch (HttpException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//		
//		
//		PostMethod postMethod = getPostMethod(url, null, "utf8");
//		try {
//			int statusCode = client.executeMethod(postMethod);
//			if (statusCode != HttpStatus.SC_OK) 
//			{  	          
//				System.err.println("Method failed: " + postMethod.getStatusLine());  
//	        }  
//	           
//	         /*获得返回的结果*/  
//	         byte[] responseBody = postMethod.getResponseBody();  
//	         System.out.println("postMethod:");
//	         System.out.println(statusCode+":"+new String(responseBody));  
//		} catch (HttpException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}	
		Map<String, String> params = new HashMap<String,String>();
		params.put("topic_word", "from junjun");
		params.put("sequence",  "{type:1, question:-1, score:0},{type:4, question:3, score:1}=-=-=");
		
		String gson = new Gson().toJson(params);
		Map<String, String> params4server = new HashMap<String,String>();
		params4server.put("data", gson);
		
	
		PushClient.getInstance().push(url, params4server, PushClient.POST);
		//push(url, null, POST);
	}
}
