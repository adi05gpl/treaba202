/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RSA;

import java.io.IOException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Random;
import javax.crypto.Cipher;
/**
 *
 * @author Adrian
 */


public class RSA {
      
    public static KeyPair keyPairRSA(){
        KeyPairGenerator generator = null;
        try{
            generator = KeyPairGenerator.getInstance(RSAConstants.ALGORITHM);
        }catch(Exception e){
            System.out.println("ERROR KEY PAIR GENERATOR IN RSA " + e.toString());
        }
        if(generator != null){
            generator.initialize(RSAConstants.ALGORITHM_BITS);
            KeyPair keyPair = generator.genKeyPair();
            return keyPair;
        }
        return null;
    }
    
    public static byte[] convert(byte[] data, Key key, int mode){
        try{
            Cipher cipher = Cipher.getInstance(RSAConstants.ALGORITHM);
            cipher.init(mode, key);
            byte[] newData = cipher.doFinal(data);
            return newData;
        }catch(Exception e){
            System.out.println("ERROR CONVERT IN RSA " + e.toString());
        }
        return null;
    }
    
    public static byte[] encrypt(String original, Key privateKey){
        if(original != null && privateKey != null){
            byte[] bs = original.getBytes();
            byte[] encData = convert(bs, privateKey, Cipher.ENCRYPT_MODE);
            return encData;
        }
        return null;
    }
    
    public static byte[] decrypt(byte[] encrypted, Key publicKey){
        if(encrypted != null && publicKey != null){
            byte[] decData = convert(encrypted, publicKey, Cipher.DECRYPT_MODE);
            return decData;
        }
        return null;
    }
}
