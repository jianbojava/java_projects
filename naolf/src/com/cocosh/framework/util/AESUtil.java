package com.cocosh.framework.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSONObject;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/*******************************************************************************
 * AES加解密算法
 * 2016.07.22
 * @author  王学明 
 * aes 128位 cbc 算法
 * HTML的&lt; &gt;&amp;&quot;&copy;&nbsp;分别是<，>，&，"，©;空格的转义字符
 */

public class AESUtil {

    // 加密
    public static String Encrypt(String sSrc, String sKey){
        return Encrypt(sSrc,sKey,CommonUtil.IvParameterSpec);
    }
    
    // 加密
    public static String Encrypt(String sSrc, String sKey,String ivStr){
        try {
			if (sKey == null) {
			    System.out.print("Key为空null");
			    return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
			    System.out.print("Key长度不是16位");
			    return null;
			}
			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
			IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度1234567890123456 
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			// sSrc= escapeChar(sSrc);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes());
			
			String str=new BASE64Encoder().encode(encrypted);
			str=str.replaceAll("\r\n", "");
			

			return str;//new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) {
    	return Decrypt(sSrc,sKey,CommonUtil.IvParameterSpec);
    }
    
    // 解密
    public static String Decrypt(String sSrc, String sKey,String ivStr){
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes("utf-8"));
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
               // originalString= unEscapeChar(originalString);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    
  
    /*
     * escapeChar 字符转换 
     * 加密前分别把<，>，&，"，© 的转义字符 转换成 &lt; &gt;&amp;&quot;&copy;
     * 
     
    private static String  escapeChar(String beforeEncryptString ){
    	String escapeStr=beforeEncryptString;
    	escapeStr=escapeStr.replaceAll("<", "&lt;");
    	escapeStr=escapeStr.replaceAll(">", "&gt;");
    	escapeStr=escapeStr.replaceAll("&", "&amp;");
    	escapeStr=escapeStr.replaceAll("\"", "&quot;");
    	escapeStr=escapeStr.replaceAll("©", "&copy;");
    	escapeStr=escapeStr.replaceAll(" ", "&nbsp;");
    	
		return escapeStr;
    	
    }
    * */
    /*
     * unEscapeChar 反向字符转换 
     * 解密后分别把&lt; &gt;&amp;&quot;&copy; 的转义字符 转换成  <，>，&，"，©
     *  
    
    private static String  unEscapeChar(String beforeDecryptString ){
    	String unEscapeStr=beforeDecryptString;
    	unEscapeStr=unEscapeStr.replaceAll( "&lt;","<");
    	unEscapeStr=unEscapeStr.replaceAll( "&gt;",">");
    	unEscapeStr=unEscapeStr.replaceAll( "&amp;","&");
    	unEscapeStr=unEscapeStr.replaceAll( "&quot;","\"");
    	unEscapeStr=unEscapeStr.replaceAll( "&copy;","©");
    	unEscapeStr=unEscapeStr.replaceAll( "&nbsp;"," ");
    	
		return unEscapeStr;
    	
    }
 * */
    public static void main(String[] args) throws Exception {
        /*
         * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符，虽然不会错，至于怎么裁决，个人看情况而定
         * 此处使用AES-128-CBC加密模式，key需要为16位。
         */
        String cKey = "1234567890abcdef";
        // 需要加密的字串
        JSONObject o=new JSONObject();
		o.put("OperatorID","MA1K370P7");
		o.put("OperatorSecret","1234567890abcdef");
        String cSrc = o.toString();
//        cSrc="{\"province\": \"山东\",\"city\": \"青岛\",\"region\": \"\",\"type\": \"\", \"opState\": \"\",\"pageNo\": \"1\",\"pageSize\": \"2\"}";
       // cSrc="示例：{\"total\":1,\"stationStatusInfo\":{\"operationID\":\"123456789\",\"stationID\":\"111111111111111\",\"connectorStatusInfos\":{\"connectorID\":1,\"equipmentID\":\"10000000000000000000001\",\"status\":4,\"currentA\":0,\"currentB\":0,\"currentC\":0,\"voltageA\":0,\"voltageB\":0,\"voltageC\":0,\"soc\":10,}";
        System.out.println(cSrc);
        // 加密
        long lStart = System.currentTimeMillis();
        String enString = AESUtil.Encrypt(cSrc, cKey);
        System.out.println("加密后的字串是：" + enString);

        long lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("加密耗时：" + lUseTime + "毫秒");
        // 解密
        lStart = System.currentTimeMillis();
        //AESUtil.Decrypt("DHVWF+8xRIfU7nUCNQdLaGF15VaMZWtNcwaqeumUPe/ok9zgSkR0pbOJUmYYQs7ZFMN7GhLB1ywEN3kb1gH4z+Mc2Z4rQe8Xa42LrmkDRvwwosmVMuR+mbLFCG+Xf5unkRO6JJx1PiTAxAB6oyWqUmbOKskK81LqpWBU5fKnBZwXo3jv2hnKItwCODYw+B+Pg+0IzZ5ye5cKcwz99NO5//H2gU0scZhn+rl8Jcktbm42TVklnxdzG/aw200H2z9ugpB1q2X0sGAi55SQH3DbLpWb5oQE5vy0As7lje4e+4dE8vbLIR0dMw8/lA9cBPYRO2WOkH6SFwFUyi+IishP8j+mzEcfoyAOIUSh5G/5VYqlYu1zlVUsYCHWu7MTE1Gr55SicHZQxl5KHgmgFBw8OQl8DerA++D8vswR8eiRNcXR2MQmNXYarCmZ7Kuc6mRSbrSk2cImOZAywVIo6MpBSu/u0BINysS3S7Ni1LB6zqAu3Ly0yNbbxzz+ZpHjmAM+MMsx4/K6LtG1rhiW8iE3bbGOLJqu9njLFVLQtKXrVsUnF4b1FWuIADG3FBCXqcdyTTTj5vNwI2xxFm/zq5lvJUKUlcFPvYSwBQFsjKHZnl8=", cKey);
        String DeString = AESUtil.Decrypt("ZK0kANdZ1x7drYYp9+v3RWxxVpfLWo5CCbploaG3ccB/F6G2Nf++E6Dh0jbNDmfGZDYIhRWmECjU2TPSvhmeqKd/QmICpaopBB0gGsJZwtGhMVcXYA0hjqWVfnazE3RwLAVCopxxQAQcUMrgclK6CIw1t0ffDjiLBNZ1PBbt51F10UAacMPo5YiUhVFzVm92Kw04R0zlFvzhbjUhtO1nSA==", cKey);
        System.out.println("解密后的字串是：" + DeString);
        lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("解密耗时：" + lUseTime + "毫秒");
    }
}