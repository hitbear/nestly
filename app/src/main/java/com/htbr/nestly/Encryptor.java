package com.htbr.nestly;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.io.ByteArrayInputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import android.util.Base64;


public class Encryptor {

    private static String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB";

    public static PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = null;
            keySpec = new X509EncodedKeySpec(Base64.decode(base64PublicKey.getBytes(),1));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }



    public static String encrypt(String data) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKeyString));
        return Base64.encodeToString(cipher.doFinal(data.getBytes()),Base64.DEFAULT);
    }

    public static String prepareEncryption(String data)throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        int length = data.length();
        StringBuilder stringBuilder = new StringBuilder();
        if (length > 32){
            int i = 0;
            while ( i < length ){
                if (i > 0) {
                    stringBuilder.append("::");
                }
                stringBuilder.append(encrypt(data.substring(i,Math.min(i+32,length))));

                i = i + 32;
            }
            return stringBuilder.toString();
        }
        else {
            return encrypt(data);
        }
    }




}
