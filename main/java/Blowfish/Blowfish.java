/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blowfish;

import javax.swing.*;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random;
/**
 *
 * @author Adrian
 */
public final class Blowfish {
    byte[] secret = new byte[1000];
    static byte[] key;
    String inputMessage, encryptedData, decryptedData, secretString;
    
       public Blowfish() {
           
           try{
            generateSymmetricKey();
            
        //    inputMessage = jTextField1.getText();   program set the field to private, can't use it here...
        
            inputMessage = "abcdefg";
            byte[] ibyte = inputMessage.getBytes();
            byte[] ebyte = encrypt(key, ibyte);
            String encryptedData = new String(ebyte);
            
            byte[] dbyte = decrypt(key, ebyte);
            String decryptedData = new String(dbyte);
            
            
           }catch(Exception e){
               System.out.println("Blowfish Error Constructor" + e.toString());
           }          
       }
       
       void generateSymmetricKey(){
           try{
               Random r = new Random();
               int num = r.nextInt(10000);
               String knum = String.valueOf(num);
               byte[] knumb = knum.getBytes();
               secret = getRawKey(knumb);
               secretString = new String(secret);
           }catch(Exception e){
               System.out.println("Blowfish Error generateSymmetricKey in Blowfish" + e.toString());
           }
       }
       
       private static byte[] getRawKey(byte[] seed) throws Exception {
           KeyGenerator kgen = KeyGenerator.getInstance("Blowfish");
           SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
           sr.setSeed(seed);
           kgen.init(128, sr);
           SecretKey secret = kgen.generateKey();
           key = secret.getEncoded();
           return key;
       }
       
       private static byte[] encrypt(byte[] key, byte[] clear) throws Exception {
           SecretKeySpec secretSpec = new SecretKeySpec(key, "Blowfish");
           Cipher cipher = Cipher.getInstance("Blowfish");
           cipher.init(Cipher.ENCRYPT_MODE, secretSpec);
           byte[] encrypted = cipher.doFinal(clear);
           return encrypted;
       }
       
       private static byte[] decrypt(byte[] key, byte[] encrypted) throws Exception {
           SecretKeySpec secretSpec = new SecretKeySpec(key, "Blowfish");
           Cipher cipher = Cipher.getInstance("Blowfish");
           cipher.init(Cipher.DECRYPT_MODE, secretSpec);
           byte[] decrypted = cipher.doFinal(encrypted);
           return decrypted;
       }
}

