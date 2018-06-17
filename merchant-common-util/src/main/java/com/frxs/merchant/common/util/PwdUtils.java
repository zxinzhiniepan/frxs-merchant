package com.frxs.merchant.common.util;


import java.security.MessageDigest;
import java.util.Objects;
import java.util.UUID;

public class PwdUtils {


	/**
	 * 校验密码是否正确
	 */
	public static boolean verify(String loginPwd,String pwd, String salt) {
		return Objects.equals(md5Hex(loginPwd + salt), pwd);
	}

	/**
	 * 获取十六进制字符串形式的MD5摘要
	 */
	public static String md5Hex(String src) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bs = md5.digest(src.getBytes("UTF-8"));
			return bytesToHexString(bs);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getUUID(){
	    return UUID.randomUUID().toString().replaceAll("-","");
    }

	public static String bytesToHexString(byte[] bArray) {
		StringBuilder sb = new StringBuilder(bArray.length);
		String sTemp;
        for (byte aBArray : bArray) {
            sTemp = Integer.toHexString(0xFF & aBArray);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
		return sb.toString().toLowerCase();
	}

	public static void main(String[] args) {

		//15802525343
		System.out.println(md5Hex("525343"+"lcsPhJZ/U+7U0PmY3TFz7w=="));
	}
}
