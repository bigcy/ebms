package com.ebupt.ebms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class MD5Util {
	
	public static char[] hexChar = {'0', '1', '2', '3',   
        							'4', '5', '6', '7',   
							        '8', '9', 'a', 'b',   
							        'c', 'd', 'e', 'f'};   

	public static String randomUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replace("-", "");
	}

	public static String MD5(String data) {
		if(data == null)
			return null;
		byte[] defaultBytes = null;
		try {
			defaultBytes = data.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();

			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1)
					hexString.append('0');

				hexString.append(hex);
			}
			return hexString.toString();

//            BigInteger number = new BigInteger(1, messageDigest);
//            return number.toString(16);
            
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String FileMD5(String filePath) {
		if(filePath == null)
			return null;
		File file = new File(filePath);
		if(!file.exists()) {
			return null;
		}
		byte[] buffer = new byte[1024];   
		InputStream fis = null;
		try {
			fis = new FileInputStream(file);
			MessageDigest md5 = MessageDigest.getInstance("MD5");   
			int numRead = 0;   
			while ((numRead = fis.read(buffer)) > 0) {   
				md5.update(buffer, 0, numRead);   
			}   
			fis.close();   
			return toHexString(md5.digest()); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		return null;
	}
	
	public static String toHexString(byte[] b) {   
        StringBuilder sb = new StringBuilder(b.length * 2);   
        for (int i = 0; i < b.length; i++) {   
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);   
            sb.append(hexChar[b[i] & 0x0f]);   
        }   
        return sb.toString();   
    }   
	
	public static void main(String arg[]) {
		String filePath = "D:\\temp\\t.xml";
//		String filePath = "D:\\movie\\Crazy.Stone.rmvb";
		System.out.println(FileMD5(filePath));
	}

}
