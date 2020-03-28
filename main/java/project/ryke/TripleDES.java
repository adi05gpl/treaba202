/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package project.ryke;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;


/**
 *
 * @author Adrian
 
public class TripleDES {
    String key;
    
    public TripleDES(String myEncryptionKey){
        key = myEncryptionKey;
    }
    
    public String encrypt(String unencryptedString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] digestOfPassword = md.digest(key.getBytes("utf-8"));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        
        for (int j = 0, k = 16; j <8;){
            keyBytes[k++] = keyBytes[j++];
        }
        
        SecretKey secretKey = new SecretKeySpec(keyBytes, "DESede");
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        
        byte[] plainTextBytes = unencryptedString.getBytes("utf-8");
        byte[] bbuf = cipher.doFinal(plainTextBytes);
        byte[] base64Bytes = Base64.encodeBase64(bbuf);
        String base64EncryptedString = new String(base64Bytes);
        
        return base64EncryptedString;
    }
    
    public String decrypt(String encryptedString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        if(encryptedString == null){
            System.out.println("ERROR AT TRIPLE DES DECRYPTION");
        }
        byte[] message = Base64.decodeBase64(encryptedString.getBytes("utf-8"));
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digestOfPassword = md.digest(key.getBytes("utf-8"));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        
        for (int j = 0, k = 16; j <8;){
            keyBytes[k++] = keyBytes[j++];
        }
        
        SecretKey secretKey = new SecretKeySpec(keyBytes, "DESede");
        Cipher decipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        decipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainText = decipher.doFinal(message);
        
        return new String(plainText, "UTF-8");
    }
}
*/