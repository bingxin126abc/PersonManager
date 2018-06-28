package com.java1234.util;

import java.security.MessageDigest;

public class WebTool {
	public static final char P_CHAR[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z' };

	public static String getMD5Encoding(String s) {
		if (s == null)
			s = "";
		byte strTemp[] = s.getBytes();
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte md[] = mdTemp.digest();
			int len = 0;
			if (md != null)
				len = md.length;
			char str[] = new char[len * 2];
			int k = 0;
			for (int i = 0; md != null && i < len; i++) {
				byte byte0 = md[i];
				str[k++] = P_CHAR[byte0 >> 4 & 15];
				str[k++] = P_CHAR[byte0 & 15];
			}

			return new String(str);
		} catch (Exception e) {
			return s;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("对字符串123456加密，结果是："+WebTool.getMD5Encoding("123456"));//E10ADC3949BA59ABBE56E057F20F883E
	}
}
