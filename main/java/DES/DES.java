/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DES;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
/**
 *
 * @author Adrian
 */
public class DES {
    
    private static Cipher ecipher;
    private static Cipher dcipher;
    
    private static SecretKey key;
    
    public static void DES() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException{
        
        key = KeyGenerator.getInstance("DES").generateKey();
        
        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
    
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        
        dcipher.init(Cipher.DECRYPT_MODE, key);

    }
    
    public static String encrypt(String str){
     try{
         byte[] utf8 = str.getBytes("UTF8");
         byte[] enc = ecipher.doFinal(utf8);
         
         String encString = new String(Base64.getEncoder().encodeToString(enc));
         return new String(encString);
     }
     catch(Exception e){
         System.out.println("Error while encrypting with DES " + e.toString());
     }
     return null;
    }
    
    public static String decrypt(String str){
        try{
            byte[] dec = Base64.getDecoder().decode(str.getBytes());
            byte[] utf8 = dcipher.doFinal(dec);
            return new String(utf8, "UTF8");
        }
        catch(Exception e){
         System.out.println("Error while decrypting with DES " + e.toString());
        }
        return null;
    }
}
