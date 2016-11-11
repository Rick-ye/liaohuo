package com.chuove.app.cms.common.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	private static final Pattern numberPattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");

	public static boolean isEmpty(String str) {
		return (str == null) || (str.trim().length() == 0);
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isBlank(String str) {
		return isEmpty(str);
	}

	public static String concat(String... str) {
		if ((str == null) || (str.length == 0)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (String s : str) {
			sb.append(s);
		}
		return sb.toString();
	}

	public static String concat(String separator, String... str) {
		if ((str == null) || (str.length == 0)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (String s : str) {
			sb.append(separator).append(s);
		}
		return sb.substring(1);
	}

	public static String startWithUpper(String str) {
		if ((str == null) || (str.trim().length() == 0)) {
			return str;
		}
		char[] ch = str.toCharArray();
		ch[0] = Character.toUpperCase(ch[0]);
		return new String(ch);
	}

	public static String startWithLower(String str) {
		if ((str == null) || (str.trim().length() == 0)) {
			return str;
		}
		char[] ch = str.toCharArray();
		ch[0] = Character.toLowerCase(ch[0]);
		return new String(ch);
	}

	public static String fillHighBit(String str, char fillChar, int length) {
		int l = length - (str == null ? 0 : str.length());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < l; i++) {
			sb.append(fillChar);
		}
		return str;
	}

	public static String fillLowBit(String str, char fillChar, int length) {
		StringBuilder sb = new StringBuilder(str);
		for (int i = 0; i < length - str.length(); i++) {
			sb.append(fillChar);
		}
		return sb.toString();
	}

	public static boolean isNumber(String str) {
		return numberPattern.matcher(str).matches();
	}

	public static String toHexString(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder("");
		if ((bytes == null) || (bytes.length <= 0)) {
			return null;
		}
		for (int i = 0; i < bytes.length; i++) {
			int v = bytes[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public static byte[] hexStringToBytes(String hexString) {
		if ((hexString == null) || (hexString.equals(""))) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = ((byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[(pos + 1)])));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static String cutBefore(String str, String sign) {
		int index = str.indexOf(sign);
		if (index == 0) {
			return "";
		}
		if (index < 0) {
			return "";
		}
		return str.substring(0, index);
	}

	public static String cutAfter(String str, String sign) {
		int index = str.indexOf(sign);
		if (index < 0) {
			return "";
		}
		return str.substring(index + sign.length());
	}

	public static String cutBeforeLast(String str, String sign) {
		int index = str.lastIndexOf(sign);
		if (index == 0) {
			return "";
		}
		if (index < 0) {
			return "";
		}
		return str.substring(0, index);
	}

	public static String cutAfterLast(String str, String sign) {
		int index = str.lastIndexOf(sign);
		if (index < 0) {
			return "";
		}
		return str.substring(index + sign.length());
	}

	private static final String MOBILEEXPRESS = "^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|70)\\d{8}$";

	public static boolean isPhoneNumber(String number) {
		Pattern p = Pattern.compile(MOBILEEXPRESS);
		Matcher m = p.matcher(number);
		return m.matches();
	}
	public static String randomString(int length)
	  {
		Random rand = new Random();
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < length; i++)
	    {
	      int type = rand.nextInt(3);
	      switch (type)
	      {
	      case 0: 
	        sb.append(rand.nextInt(10));
	        break;
	      case 1: 
	        sb.append((char)(rand.nextInt(26) + 65));
	        break;
	      case 2: 
	        sb.append((char)(rand.nextInt(26) + 97));
	      }
	    }
	    return sb.toString();
	  }
	public static void main(String[] args) {
		String str = "[{\"pid\":0},{\"pid\":0},{\"pid\":0},{\"pid\":0}]";
		System.out.println(str.replaceAll("pid", "pId"));
	}
}
