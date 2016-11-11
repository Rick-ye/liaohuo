package com.chuove.app.cms.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.GeneratedValue;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class VeriCodeUtil {
private static String URI_GET_USER_INFO="http://yunpian.com/v1/user/get.json";
	
	private static String URI_SEND_SMS="http://yunpian.com/v1/sms/send.json";
	
	private static String URI_TPL_SEND_SMS="http://yunpian.com/v1/sms/tpl_send.json";
	
	private static String URI_SEND_VOICE="http://yunpian.com/v1/voice/send.json";

	private static String ENCODING="UTF-8";

	/**
	 * 获得6位随机的验证码
	 * @return
	 */
	public static String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return "【云片网】您的验证码是"+charValue;
    }
	public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }
	
	/*public static String getVeriCode(int min, int max){
		String randNum = String.valueOf(min + (int)(Math.random()*((max-min)+1)));
		return "【云片网】您的验证码是"+randNum;
	}
	public static String getRandNum(){
		String randNum = String.valueOf((int)(Math.random() * 1000000));
		return randNum;
	}*/
	/**
	 * 取账户信息
	 * @param 
	 * @return json格式字符串
	 */
	public static String getUserInfo(String apikey){
		Map<String, String> params = new HashMap<String, String>();
		params.put("apikey", apikey);
		return post(URI_GET_USER_INFO,params);
	}
	
	/**
	 * 智能匹配模板接口发短信
	 * @param apikey	apikey
	 * @param text 		短信内容
	 * @param mobile	接受的手机号
	 * @return			json格式字符串
	 */
	public static String sendSms(String apikey, String text, String mobile){
		Map<String, String> params = new HashMap<String, String>();
		params.put("apikey", apikey);
		params.put("text", text);
		params.put("mobile", mobile);
		return post(URI_SEND_SMS, params);
	}
	
	/**
	 * 通过模板发短信（不推荐）
	 * @param apikey
	 * @param tpl_id	模板id
	 * @param tpl_value	模板变量值
	 * @param mobile	接受的手机号
	 * @return			json格式字符串
	 */
	public static String tplSendSms(String apikey,long tpl_id,String tpl_value,String mobile){
		Map<String, String> params = new HashMap<String, String>();
	    params.put("apikey", apikey);
	    params.put("tpl_id", String.valueOf(tpl_id));
	    params.put("tpl_value", tpl_value);
	    params.put("mobile", mobile);
	    return post(URI_TPL_SEND_SMS, params);
	}
	
	/**
	* 通过接口发送语音验证码
	* @param apikey apikey
	* @param mobile 接收的手机号
	* @param code   验证码
	* @return
	*/
	public static String sendVoice(String apikey, String mobile, String code) {
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("apikey", apikey);
	    params.put("mobile", mobile);
	    params.put("code", code);
	    return post(URI_SEND_VOICE, params);
	}
	/**
	 * 基于HttpClient 4.3的通用POST方法
	 * @param url 		提交的url
	 * @param params	提交map
	 * @return			提交响应
	 */
	private static String post(String url, Map<String, String> params) {
		CloseableHttpClient client = HttpClients.createDefault();
		String responseText = "";
		CloseableHttpResponse response = null;
		try {
			HttpPost method = new HttpPost(url);
			if(params != null){
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				for(Map.Entry<String, String> param:params.entrySet()){
					NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
					list.add(pair);
				}
				method.setEntity(new UrlEncodedFormEntity(list,ENCODING));
			}
			response = client.execute(method);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				responseText = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return responseText;
	}
	
public static void main(String[] args){
		
	
	/*String str1 = getVeriCode(1,999999);
	System.out.println(str1);
	String str2 = str1.substring(11);
	System.out.println(str2);*/
	
	System.out.println(getRandNum(6));
	
		/*String apikey="ff56bbadf674729b8a1301af49b0a230";
		
		String mobile="15705829883";
		//查看账户信息调用示例
		System.out.println(VeriCodeUtil.getUserInfo(apikey));
		
		//使用智能匹配模板接口发送短信
		String text = "【云片网】您的验证码是716716";
		System.out.println(VeriCodeUtil.sendSms(apikey, text, mobile));*/
		
		//使用接口发语音验证码
		/*String code = "716520";
		System.out.println(JavaSmsApi.sendVoice(apikey, mobile, code));*/
	}
}
