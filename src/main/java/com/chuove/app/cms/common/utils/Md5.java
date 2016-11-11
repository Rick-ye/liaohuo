package com.chuove.app.cms.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
	private static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	public static String encode(String source) {
		return encode(source.getBytes());
	}

	public static String encode(byte[] source) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

		md.update(source);

		byte[] tmp = md.digest();

		char[] str = new char[32];

		int k = 0;
		for (int i = 0; i < 16; i++) {
			byte byte0 = tmp[i];

			str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];

			str[(k++)] = hexDigits[(byte0 & 0xF)];
		}
		return new String(str);
	}
	public static void main(String[] args) {
		System.out.println(Md5.encode("111"));
	}
}
