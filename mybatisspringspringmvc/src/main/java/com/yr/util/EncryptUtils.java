package com.yr.util;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtils {
	
	/**
	 * ����MD5����
	 * 
	 * @param info Ҫ���ܵ���Ϣ
	 * @return String ���ܺ���ַ���
	 */
	public static String encryptToMD5(String info) {
		byte[] digesta = null;
		try {
			// �õ�һ��md5����ϢժҪ
			MessageDigest alga = MessageDigest.getInstance("MD5");
			// ���Ҫ���м���ժҪ����Ϣ
			alga.update(info.getBytes());
			// �õ���ժҪ
			digesta = alga.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// ��ժҪתΪ�ַ���
		String rs = byte2hex(digesta);
		return rs;
	}

	/**
	 * ����SHA����
	 * 
	 * @param info Ҫ���ܵ���Ϣ
	 * @return String ���ܺ���ַ���
	 */
	public String encryptToSHA(String info) {
		byte[] digesta = null;
		try {
			// �õ�һ��SHA-1����ϢժҪ
			MessageDigest alga = MessageDigest.getInstance("SHA-1");
			// ���Ҫ���м���ժҪ����Ϣ
			alga.update(info.getBytes());
			// �õ���ժҪ
			digesta = alga.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// ��ժҪתΪ�ַ���
		String rs = byte2hex(digesta);
		return rs;
	}

	/**
	 * ����һ�����㷨�õ���Ӧ��key
	 * 
	 * @param src
	 * @return
	 */
	public String getKey(String algorithm, String src) {
		if (algorithm.equals("AES")) {
			return src.substring(0, 16);
		} else if (algorithm.equals("DES")) {
			return src.substring(0, 8);
		} else {
			return null;
		}
	}

	/**
	 * �õ�AES���ܵ�key
	 * 
	 * @param src
	 * @return
	 */
	public String getAESKey(String src) {
		return this.getKey("AES", src);
	}

	/**
	 * �õ�DES���ܵ�key
	 * 
	 * @param src
	 * @return
	 */
	public String getDESKey(String src) {
		return this.getKey("DES", src);
	}

	/**
	 * �����ܳ�
	 * 
	 * @param algorithm �����㷨,���� AES,DES,DESede,Blowfish
	 * @return SecretKey ���ܣ��Գƣ���Կ
	 */
	public SecretKey createSecretKey(String algorithm) {
		// ����KeyGenerator����
		KeyGenerator keygen;
		// ���� ��Կ����
		SecretKey deskey = null;
		try {
			// ��������ָ���㷨��������Կ�� KeyGenerator ����
			keygen = KeyGenerator.getInstance(algorithm);
			// ����һ����Կ
			deskey = keygen.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// �����ܳ�
		return deskey;
	}

	/**
	 * ����һ��AES����Կ
	 * 
	 * @return
	 */
	public SecretKey createSecretAESKey() {
		return createSecretKey("AES");
	}

	/**
	 * ����һ��DES����Կ
	 * 
	 * @return
	 */
	public SecretKey createSecretDESKey() {
		return createSecretKey("DES");
	}

	/**
	 * ������Ӧ�ļ����㷨����Կ��Դ�ļ����м��ܣ����ؼ��ܺ���ļ�
	 * 
	 * @param Algorithm �����㷨:DES,AES
	 * @param key
	 * @param info
	 * @return
	 */
	public String encrypt(String Algorithm, SecretKey key, String info) {
		// ����Ҫ���ɵ�����
		byte[] cipherByte = null;
		try {
			// �õ�����/������
			Cipher c1 = Cipher.getInstance(Algorithm);
			// ��ָ������Կ��ģʽ��ʼ��Cipher����
			// ����:(ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE,UNWRAP_MODE)
			c1.init(Cipher.ENCRYPT_MODE, key);
			// ��Ҫ���ܵ����ݽ��б��봦��,
			cipherByte = c1.doFinal(info.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �������ĵ�ʮ��������ʽ
		return byte2hex(cipherByte);
	}

	/**
	 * ������Ӧ�Ľ����㷨����Կ����Ҫ���ܵ��ı����н��ܣ����ؽ��ܺ���ı�����
	 * 
	 * @param Algorithm
	 * @param key
	 * @param sInfo
	 * @return
	 */
	public String decrypt(String Algorithm, SecretKey key, String sInfo) {
		byte[] cipherByte = null;
		try {
			// �õ�����/������
			Cipher c1 = Cipher.getInstance(Algorithm);
			// ��ָ������Կ��ģʽ��ʼ��Cipher����
			c1.init(Cipher.DECRYPT_MODE, key);
			// ��Ҫ���ܵ����ݽ��б��봦��
			cipherByte = c1.doFinal(hex2byte(sInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(cipherByte);
	}

	/**
	 * ������Ӧ�Ľ����㷨��ָ������Կ����Ҫ���ܵ��ı����н��ܣ����ؽ��ܺ���ı�����
	 * 
	 * @param Algorithm �����㷨:DES,AES
	 * @param key       ���key�������û��Լ�ָ�� ע��AES�ĳ���Ϊ16λ,DES�ĳ���Ϊ8λ
	 * @param sInfo
	 * @return
	 */
	public static String decrypt(String Algorithm, String sSrc, String sKey) throws Exception {
		try {
			// �ж�Key�Ƿ���ȷ
			if (sKey == null) {
				throw new Exception("KeyΪ��null");
			}
			// �жϲ���AES�ӽ��ܷ�ʽ��Key�Ƿ�Ϊ16λ
			if (Algorithm.equals("AES") && sKey.length() != 16) {
				throw new Exception("Key���Ȳ���16λ");
			}
			// �жϲ���DES�ӽ��ܷ�ʽ��Key�Ƿ�Ϊ8λ
			if (Algorithm.equals("DES") && sKey.length() != 8) {
				throw new Exception("Key���Ȳ���8λ");
			}
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, Algorithm);
			Cipher cipher = Cipher.getInstance(Algorithm);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] encrypted1 = hex2byte(sSrc);
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString;
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * ������Ӧ�ļ����㷨��ָ������Կ��Դ�ļ����м��ܣ����ؼ��ܺ���ļ�
	 * 
	 * @param Algorithm �����㷨:DES,AES
	 * @param key       ���key�������û��Լ�ָ�� ע��AES�ĳ���Ϊ16λ,DES�ĳ���Ϊ8λ
	 * @param info
	 * @return
	 */
	public static String encrypt(String Algorithm, String sSrc, String sKey) throws Exception {
		// �ж�Key�Ƿ���ȷ
		if (sKey == null) {
			throw new Exception("KeyΪ��null");
		}
		// �жϲ���AES�ӽ��ܷ�ʽ��Key�Ƿ�Ϊ16λ
		if (Algorithm.equals("AES") && sKey.length() != 16) {
			throw new Exception("Key���Ȳ���16λ");
		}
		// �жϲ���DES�ӽ��ܷ�ʽ��Key�Ƿ�Ϊ8λ
		if (Algorithm.equals("DES") && sKey.length() != 8) {
			throw new Exception("Key���Ȳ���8λ");
		}
		byte[] raw = sKey.getBytes("ASCII");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, Algorithm);
		Cipher cipher = Cipher.getInstance(Algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes());
		return byte2hex(encrypted);
	}

	/**
	 * ����DES������ɵ���Կ���м���
	 * 
	 * @param key
	 * @param info
	 * @return
	 */
	public String encryptToDES(SecretKey key, String info) {
		return encrypt("DES", key, info);
	}

	/**
	 * ����DESָ����Կ�ķ�ʽ���м���
	 * 
	 * @param key
	 * @param info
	 * @return
	 * @throws Exception
	 */
	public String encryptToDES(String key, String info) throws Exception {
		return encrypt("DES", info, key);
	}

	/**
	 * ����DES���������Կ�ķ�ʽ���н��ܣ���Կ��Ҫ����ܵ����ɵ���Կһ��
	 * 
	 * @param key
	 * @param sInfo
	 * @return
	 */
	public String decryptByDES(SecretKey key, String sInfo) {
		return decrypt("DES", key, sInfo);
	}

	/**
	 * ����DES�û�ָ����Կ�ķ�ʽ���н��ܣ���Կ��Ҫ�����ʱָ������Կһ��
	 * 
	 * @param key
	 * @param sInfo
	 * @return
	 */
	public String decryptByDES(String key, String sInfo) throws Exception {
		return decrypt("DES", sInfo, key);
	}

	/**
	 * ����AES������ɵ���Կ���м���
	 * 
	 * @param key
	 * @param info
	 * @return
	 */
	public String encryptToAES(SecretKey key, String info) {
		return encrypt("AES", key, info);
	}

	/**
	 * ����AESָ����Կ�ķ�ʽ���м���
	 * 
	 * @param key
	 * @param info
	 * @return
	 * @throws Exception
	 */
	public String encryptToAES(String key, String info) throws Exception {
		return encrypt("AES", info, key);
	}

	/**
	 * ����AES���������Կ�ķ�ʽ���н��ܣ���Կ��Ҫ����ܵ����ɵ���Կһ��
	 * 
	 * @param key
	 * @param sInfo
	 * @return
	 */
	public String decryptByAES(SecretKey key, String sInfo) {
		return decrypt("AES", key, sInfo);
	}

	/**
	 * ����AES�û�ָ����Կ�ķ�ʽ���н��ܣ���Կ��Ҫ�����ʱָ������Կһ��
	 * 
	 * @param key
	 * @param sInfo
	 * @return
	 */
	public String decryptByAES(String key, String sInfo) throws Exception {
		return decrypt("AES", sInfo, key);
	}

	/**
	 * ʮ�������ַ���ת��Ϊ2����
	 * 
	 * @param hex
	 * @return
	 */
	public static byte[] hex2byte(String strhex) {
		if (strhex == null) {
			return null;
		}
		int l = strhex.length();
		if (l % 2 == 1) {
			return null;
		}
		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; i++) {
			b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
		}
		return b;
	}

	/**
	 * ��������ת��Ϊ16�����ַ���
	 * 
	 * @param b �������ֽ�����
	 * @return String
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	/**
	 * ����
	 * 
	 * @param publicKey
	 * @param srcBytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] encrypt(RSAPublicKey publicKey, byte[] srcBytes) throws Exception {
		if (publicKey != null) {
			// Cipher������ɼ��ܻ���ܹ���������RSA
			Cipher cipher = Cipher.getInstance("RSA");
			// ���ݹ�Կ����Cipher������г�ʼ��
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] resultBytes = cipher.doFinal(srcBytes);
			return resultBytes;
		}
		return null;
	}

	/**
	 * ����
	 * 
	 * @param privateKey
	 * @param srcBytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] decrypt(RSAPrivateKey privateKey, byte[] srcBytes) throws Exception {
		if (privateKey != null) {
			// Cipher������ɼ��ܻ���ܹ���������RSA
			Cipher cipher = Cipher.getInstance("RSA");
			// ���ݹ�Կ����Cipher������г�ʼ��
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] resultBytes = cipher.doFinal(srcBytes);
			return resultBytes;
		}
		return null;
	}
}