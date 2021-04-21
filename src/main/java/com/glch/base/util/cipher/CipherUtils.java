package com.glch.base.util.cipher;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CipherUtils {

	public static byte[] encryptMD5(String source) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md5 = MessageDigest.getInstance("md5");
		md5.update(source.getBytes("utf-8"));
		return md5.digest();
	}
	
	public static byte[] encryptSHA(String source) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		MessageDigest sha = MessageDigest.getInstance("sha");
		sha.update(source.getBytes("utf-8"));
		return sha.digest();
	}
	
    public static byte[] decryptBASE64(byte[] source) {  
        return Base64.decodeBase64(source);
    }  
  
    public static byte[] encryptBASE64(byte[] source) {  
    	 return Base64.encodeBase64(source);
    }  
    
    public static String decryptBASE64(String source) throws UnsupportedEncodingException{  
        return new String(decryptBASE64(source.getBytes("utf-8")),"utf-8");
    }  
  
    public static String encryptBASE64(String source) throws UnsupportedEncodingException{  
    	 return new String(encryptBASE64(source.getBytes("utf-8")),"utf-8");
    }
    
    public static String encrypt(String source,String al) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    	if("md5".equals(al)){
    		byte[] b = encryptMD5(source);
    		int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
 
            return buf.toString();
    	}else if("sha".equals(al)){
    		return new String(encryptBASE64(encryptSHA(source)),"utf-8");
    	}else{
    		return source;
    	}
    }
    
    public static String encrypt(String source){
    	try {
			return encrypt(source, "sha");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return source;
    }
    
    public static String encryptAES128(String content, String password){
    	try{
    		KeyGenerator keygen = KeyGenerator.getInstance("AES");
    		keygen.init(128, new SecureRandom(password.getBytes("utf-8")));
    		SecretKeySpec key = new SecretKeySpec(keygen.generateKey().getEncoded(),"AES");
    		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    		cipher.init(Cipher.ENCRYPT_MODE, key);
    		byte[] byteContent = content.getBytes("utf-8");
    		byte[] cryptograph = cipher.doFinal(byteContent);
    		return new String(Base64.encodeBase64(cryptograph));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static String decryptAES128(String content, String password){
    	try{
    		KeyGenerator keygen = KeyGenerator.getInstance("AES");
    		keygen.init(128, new SecureRandom(password.getBytes("utf-8")));
    		SecretKeySpec key = new SecretKeySpec(keygen.generateKey().getEncoded(),"AES");
    		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    		cipher.init(Cipher.DECRYPT_MODE, key);
    		byte[] cryptograph = cipher.doFinal(Base64.decodeBase64(content));
    		return new String(cryptograph);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static void main(String[] args){
    	/*try {
			System.out.println(encrypt("123", "md5"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	/*String content = "桂林长海发展有限责任公司";
    	String password = "GLCHDJZ,.!";
    	System.out.println("明文："+content);
    	System.out.println("密码文："+password);
    	
    	String encryptContent = encryptAES128(content,password);
    	System.out.println("密文："+encryptContent);
    	System.out.println("密文："+decryptAES128(encryptContent,password));*/

		String token =  "20200709192450808d478f19a42754ca8a877ce33c694cb1d";
//        String pwd = "fEqNCco3Yq9h5ZUglD3CZJT4lBs";
		String pwd = "123";
		String enCodePwd = CipherUtils.encrypt(pwd);
//        String enCodePwd = "WUpBQb0gwR382iW4l0LXu0xKJRW=";
		System.out.println(CipherUtils.encrypt(enCodePwd+token));
    }
}
