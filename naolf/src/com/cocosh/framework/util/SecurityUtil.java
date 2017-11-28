package com.cocosh.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密工具类
 * 
 * @author jerry
 */
public class SecurityUtil {
	private static MessageDigest md = null;
	
	private final static String DES = "DES";
	
	private final static String g_key  = "nlf!@#$%";

	/**
	 * md5加密字符串
	 * 
	 * @param input
	 * @return
	 */
	public static String md5(String input) {
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(input.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return new BigInteger(1, md.digest()).toString(16);
	}
	
	/**
	 * 不可逆加密
	 * @param str
	 * @return
	 */
	public static String getMD5Str(String str) {  
        MessageDigest messageDigest = null;  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");  
            messageDigest.reset();  
            messageDigest.update(str.getBytes("UTF-8"));  
        } catch (NoSuchAlgorithmException e) {  
            System.out.println("NoSuchAlgorithmException caught!");  
            System.exit(-1);  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        byte[] byteArray = messageDigest.digest();  
        StringBuffer md5StrBuff = new StringBuffer();  
        for (int i = 0; i < byteArray.length; i++) {  
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
        return md5StrBuff.toString();  
    }
	
	/**
     * Description 根据键值进行加密
     * @param data 
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encrypt(String data) throws Exception {
        byte[] bt = encrypt(data.getBytes());
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }
 
    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data) throws IOException,
            Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf);
        return new String(bt);
    }
 
    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data) throws Exception {
    	 byte[] key = g_key.getBytes();
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
 
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
 
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }
     
     
    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data ) throws Exception {
    	byte[] key = g_key.getBytes();
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
 
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
 
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }  

	/**
	 * md5加密文件
	 * 
	 * @param file
	 * @return
	 */
	public static String md5(File file) {
		FileInputStream fis = null;
		try {
			md = MessageDigest.getInstance("MD5");
			fis = new FileInputStream(file);
			byte[] buffer = new byte[2048];
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				md.update(buffer, 0, length);
			}
			return new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * md5加密文件流
	 * 
	 * @param iStream
	 * @return
	 */
	public static String md5(InputStream iStream) {
		//File文件 可以通过new FileInputStream(file)获取
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[2048];
			int length = -1;
			while ((length = iStream.read(buffer)) != -1) {
				md.update(buffer, 0, length);
			}
			return new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				iStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args) throws IOException, Exception {
		String s=encrypt("13666871895");
		System.out.println(s);
		String d=decrypt("RWN1LtUInTqHw+CjZfiFZQ==");
		System.out.println(d);
//		System.out.println(md5("NrHME9397OXas58Y60mn3GSYYP~534030~8cddbcb281234a45a4d25d1e9fc0881a~3.06~USD"));
//		System.out.println(md5(new File("D:\\test.txt")));
//		try {
//			System.out.println(md5(new FileInputStream(new File("D:\\test.txt"))));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		System.out.println(encodeFile(new File("D:\\test.txt")));
	}

//	/**
//	 * MD5加密文件
//	 */
//	static char hexdigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
//			'9', 'a', 'b', 'c', 'd', 'e', 'f' };
//
//	public static String encodeFile(File file) {
//		FileInputStream fis = null;
//		try {
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			fis = new FileInputStream(file);
//			byte[] buffer = new byte[2048];
//			int length = -1;
//			while ((length = fis.read(buffer)) != -1) {
//				md.update(buffer, 0, length);
//			}
//			byte[] b = md.digest();
//			return byteToHexString(b);
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			try {
//				fis.close();
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * 把byte[]数组转换成十六进制字符串表示形式
//	 * 
//	 * @param tmp
//	 *            要转换的byte[]
//	 * 
//	 * @return 十六进制字符串表示形式
//	 */
//
//	private static String byteToHexString(byte[] tmp) {
//		String s;
//		// 用字节表示就是 16 个字节
//		char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
//		// 所以表示成 16 进制需要 32 个字符
//		int k = 0; // 表示转换结果中对应的字符位置
//		for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
//			// 转换成 16 进制字符的转换
//			byte byte0 = tmp[i]; // 取第 i 个字节
//			str[k++] = hexdigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
//			// >>> 为逻辑右移，将符号位一起右移
//			str[k++] = hexdigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
//		}
//		s = new String(str); // 换后的结果转换为字符串
//		return s;
//	}
}
