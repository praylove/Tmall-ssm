package com.sherl.tmall.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncoderUtil {

	public static String md5Encode(String s) throws NoSuchAlgorithmException {
		MessageDigest md5Encoder = MessageDigest.getInstance("MD5");
		md5Encoder.update(s.getBytes());
		String encoder = new BigInteger(1, md5Encoder.digest()).toString(16);
		if (encoder.length() < 32) {
			encoder = 0 + encoder;
		}
		return encoder;
	}

}
