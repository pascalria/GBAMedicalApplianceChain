package com.demo.util;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class BuildKey {
    /**
     * 解码PublicKey
     * @param key
     * @return
     */
    public static PublicKey getPublicKey(String key) {
        try {
            byte[] byteKey = Base64.getMimeDecoder().decode(key);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance("DSA");
            return keyFactory.generatePublic(x509EncodedKeySpec);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 解码PrivateKey
     * @param key
     * @return
     */
    public static PrivateKey  getPrivateKey(String key) {
        try {
//            byte[] byteKey = Base64.getMimeDecoder().decode(key);
//            PKCS8EncodedKeySpec x509EncodedKeySpec = new PKCS8EncodedKeySpec(byteKey);
//            KeyFactory keyFactory = KeyFactory.getInstance("DSA");
//            return keyFactory.generatePrivate(x509EncodedKeySpec);
            KeyFactory keyFactory = KeyFactory.getInstance("DSA");




        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
