package com.ebupt.ebms.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * @author zhanglei
 * @version 1.0
 * @description 处理类型转化
 */

public class ConvertUtil {

	/**
	 * 针对终端Key的加密密钥，长度需为8的倍数。
	 */
	private static final String key = "ABCDVMFIDKEYXYZW";

	/**
	 * @description 把整形数据转换成定长的字符串
	 * @param value：需要被转换的整形值
	 * @param length：value被转换成定长的字符串的长度
	 * @return 定长的字符串
	 */
	public static String getFixString(int value, int length) {
		StringBuffer fixStr = new StringBuffer();

		for (int i = length; i > 0; i--) {
			if (value < Math.pow(10, (i - 1))) {
				fixStr.append("0");
			}
		}
		fixStr.append(value);
		return fixStr.toString();
	}

	/**
	 * @description 根据指定长度生成密码
	 * @param length
	 *            长度，如果长度大于32，返回32位
	 * @return 指定长度的密码
	 */
	public static String getPassword(int length) {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		if (length > 32)
			length = 32;
		return uuid.substring(32 - length);
	}

	/**
	 * @description 解密
	 * @param VMFKey
	 *            加密后的密文
	 * @return 明文
	 */
	public static String getDecodeKey(String VMFKey) {
//		String algorithm = "DES/ECB/PKCS5Padding";
//		String algorithm = "DES/ECB/NoPadding";
		String algorithm = "DES";
		DESKeySpec dks = null;
		try {
			dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance(algorithm);
			SecretKey deskey = keyFactory.generateSecret(dks);
			//SecretKey deskey =  new SecretKeySpec(key.getBytes(),"DES");
			Cipher c = Cipher.getInstance(algorithm);
			c.init(Cipher.DECRYPT_MODE, deskey);
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] clearByte = c.doFinal(decoder.decodeBuffer(VMFKey));
			return new String(clearByte);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @description 加密
	 * @param VMFKey
	 *            明文
	 * @return 密文
	 */
	public static String getEncodeKey(String VMFKey) {
		//String algorithm = "DES/ECB/PKCS5Padding";
		//String algorithm = "DES/ECB/NoPadding";
		String algorithm = "DES";
		DESKeySpec dks = null;
		try {
			dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance(algorithm);
			SecretKey deskey = keyFactory.generateSecret(dks);
//			SecretKey deskey =  new SecretKeySpec(key.getBytes(),"DES");
			Cipher c = Cipher.getInstance(algorithm);
			c.init(Cipher.ENCRYPT_MODE, deskey);
			byte[] clearByte = c.doFinal(VMFKey.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(clearByte);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] desEncrypt(byte[] plainText) {
		SecureRandom sr = new SecureRandom();
		byte rawKeyData[] = key.getBytes();
		DESKeySpec dks;
		try {
			dks = new DESKeySpec(rawKeyData);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
			byte data[] = plainText;
			byte encryptedData[] = cipher.doFinal(data);
			return encryptedData;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * * DES Decoder *
	 * 
	 * @param encryptText *
	 * @return *
	 * @throws Exception
	 */
	public static byte[] desDecrypt(byte[] encryptText) {
		SecureRandom sr = new SecureRandom();
		byte rawKeyData[] = key.getBytes();
		DESKeySpec dks;
		try {
			dks = new DESKeySpec(rawKeyData);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key, sr);
			byte encryptedData[] = encryptText;
			byte decryptedData[] = cipher.doFinal(encryptedData);
			return decryptedData;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String input) {
		byte[] result = base64Decode(input);
		return new String(desDecrypt(result));
	}

	public static byte[] base64Decode(String s)  {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = null;
		try {
			b = decoder.decodeBuffer(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

	public static String base64Encode(byte[] s) {
		if (s == null)
			return null;
		BASE64Encoder b = new BASE64Encoder();
		return b.encode(s);
	}

	public static String encrypt(String input) {
		return base64Encode(desEncrypt(input.getBytes()));
	}

	public static String addDoubleQuotationMarks(String value) {
		if (value == null)
			return null;
		return "\"" + value + "\"";
	}

	public static String delDoubleQuotationMarks(String value) {
		if (value == null || value.length() == 0)
			return null;
		if ((value.charAt(0) == '"')
				&& ((value.charAt(value.length() - 1)) == '"')) {
			return value.substring(1, value.length() - 1);
		}
		return value;
	}
	
	public static String getFormatByContentPath(String contentPath) {
		if(contentPath == null)
			return null;
		int index = contentPath.lastIndexOf(".");
		if(index < 0) {
			return null;
		}
		String format = contentPath.substring(index + 1);
		return format;
	}
	
	
	/**
	 * Calculate digest of given String using given algorithm. Encode digest in
	 * MIME-like base64.
	 * 
	 * @param pass
	 *            the String to be hashed
	 * @param algorithm
	 *            the algorithm to be used
	 * @return String Base-64 encoding of digest
	 * 
	 * @throws NoSuchAlgorithmException
	 *             if the algorithm passed in cannot be found
	 */
	public static String digestString(String pass, String algorithm)
			throws NoSuchAlgorithmException {

		MessageDigest md;
		ByteArrayOutputStream bos;

		try {
			md = MessageDigest.getInstance(algorithm);
			byte[] digest = md.digest(pass.getBytes("iso-8859-1"));
			bos = new ByteArrayOutputStream();
			OutputStream encodedStream = MimeUtility.encode(bos, "base64");
			encodedStream.write(digest);
			return bos.toString("iso-8859-1");
		} catch (IOException ioe) {
			throw new RuntimeException("Fatal error: " + ioe);
		} catch (MessagingException me) {
			throw new RuntimeException("Fatal error: " + me);
		}
	}
	
	public static String getSqlQuery(String ids) {
		String[] _ids = ids.split(",");
		String query = null;
		for (String id : _ids) {
			if (query == null) {
				query = "'" + id + "'";
			} else {
				query = query + ",'" + id + "'";
			}

		}
		query = "(" + query + ")";
		return query;
	}
	
	public static String getSqlQuery(List<String> ids) {
		String query = null;
		for (String id : ids) {
			if (query == null) {
				query = "'" + id + "'";
			} else {
				query = query + ",'" + id + "'";
			}

		}
		query = "(" + query + ")";
		return query;
	}
	

	/**
	 * 测试使用，发布时需删除
	 * 
	 * @param arg
	 */
	public static void main(String arg[]) {
		//String msisdn = "8613426406327";
		//System.out.println(getmsisdn11(msisdn));
		// String a = "abc";
		// String b = addDoubleQuotationMarks(a);
		// String c = delDoubleQuotationMarks(b);
		// System.out.println(b);
		// System.out.println(c);

		String mykey = "a79352ec7f1946ebaaec2ceb3db64f98";
//		String mykey = "ebabfd439cc34f15b758271e5f868223";
		String dekey = getEncodeKey(mykey);
		System.out.println(dekey);
		String enkey = getDecodeKey(dekey);
		System.out.println(enkey);
//			
//		System.out.println("############################");
//		
//		String mykey2 = "a79352ec7f1946ebaaec2ceb3db64f98";
//		String dekey2 = encrypt(mykey2);
//		//String dekey2 = "Ib2jOZyl38c=";
//		System.out.println(dekey2);
//		String enkey2 = decrypt(dekey2);
//		System.out.println(enkey2);

		// for(int i = 0; i < 34; i++) {
		// System.out.println(getPassword(6));
		// }

		// int value = 8;
		// for(int j = 1; j < 100; ) {
		// value = 8 * j;
		// j *= 10;
		// for(int i = 1; i <= 10; i++) {
		// System.out.println("value: " + value + " length: " + i + " FixStr:" +
		// getFixString(value, i));
		// }
		// }
		
		System.out.println(base64Encode("uid=xcdzh".getBytes()));
	}

}
