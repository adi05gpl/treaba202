/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ryke;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;

/**
 *
 * @author Adrian
 */
public class TripleDES {
    String key;
    
    public TripleDES(String myEncryptionKey){
        key = myEncryptionKey;
    }
    
    public String encrypt(String unencryptedString){
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
        byte[] base64Bytes = Base64.encodeBase64(buf);
        String base64EncryptedString = new String(base64Bytes);
        
        return base64EncryptedString;
    }
    
    public String decrypt(String encryptedString){
        if(encryptedString == null){
            System.out.println("ERROR AT TRIPLE DES DECRYPTION" + e.toString());
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
        
        return new String(plaintext, "UTF-8");
    }
}
