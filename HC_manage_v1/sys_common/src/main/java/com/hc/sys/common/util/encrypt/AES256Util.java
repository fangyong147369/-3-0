package com.hc.sys.common.util.encrypt;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * AES256加密解密
 * 注: 一般服务器返回的加密后数据都要Base64编码(否则容易丢失数据，抛出异常：javax.crypto.IllegalBlockSizeException: last block incomplete in decryption).
 * 	  所以AES256加密后再用Base64编码。
 * @author fangyong
 * @version 1.0.0.0
 */
@Component
public class AES256Util {
	
	private static final String KEY_ALGORITHM = "AES";

	private static final String CIPHER_ALGORITHM = "AES";

	private static final String CHARSET = "UTF8";
	
//	@Value("${security.AES256.key}")
	private String KEY;

	
	private byte[] decrypt(byte[] src, byte[] key) throws Exception {
		Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(src);
	}

	/**
	 * 解密
	 * @param data	待解密的字符串
	 * @return 解密后的字符串
	 */
	public String decrypt(String data) {
		try {
			byte[] key = KEY.getBytes(CHARSET);
			byte[] src = new BASE64Decoder().decodeBuffer(data);
			return new String(decrypt(src, key), CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Key toKey(byte[] key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}

	private byte[] encrypt(byte[] data, byte[] key) throws Exception {
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}

	/**
	 * 加密
	 * @param data 待加密的字符串
	 * @return 加密后的字符串
	 */
	public String encrypt(String data) {
		return addSecureToStr(data);
	}

	private String addSecureToStr(String str) {
		byte[] data = null;
		byte[] key = new byte[0];
		try {
			key = KEY.getBytes(CHARSET);
			data = encrypt(str.getBytes(CHARSET), key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BASE64Encoder().encode(data);
	}
	
}