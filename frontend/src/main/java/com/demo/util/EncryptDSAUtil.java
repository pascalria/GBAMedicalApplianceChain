package com.demo.util;

import com.sun.security.auth.module.KeyStoreLoginModule;
import org.apache.commons.io.FileUtils;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.util.encoders.Base64;

import java.io.*;
import java.nio.charset.Charset;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;

public class EncryptDSAUtil {

//    public static void main(String[] args) throws Exception {
//// 创建秘钥生成器
//
//        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
//
//        kpg.initialize(512);
//
//        KeyPair keypair = kpg.generateKeyPair();// 生成秘钥对
//
//        DSAPublicKey publickey = (DSAPublicKey) keypair.getPublic();
//
//        DSAPrivateKey privatekey = (DSAPrivateKey) keypair.getPrivate();
//        String data = "pangugle";}


// 签名和验证

    // 签名
    public static byte[] DSASign(PrivateKey privateKey, String data) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {

        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initSign(privateKey);// 初始化私钥，签名只能是私钥

        sign.update(data.getBytes());// 更新签名数据

        byte[] b = sign.sign();// 签名，返回签名后的字节数组

        return b;
    }
// 验证

    public static boolean DSAVerify(PublicKey publickey, byte[] b, String data) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initVerify(publickey);// 初始化公钥，验证只能是公钥

        sign.update(data.getBytes());// 更新验证的数据

        boolean result = sign.verify(b);// 签名和验证一致返回true 不一致返回false

        System.out.println(result);
        return result;
    }

    public static void CreateKey() throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(512);
        KeyPair keypair = kpg.generateKeyPair();// 生成秘钥对

        DSAPublicKey publicKey = (DSAPublicKey) keypair.getPublic();
        DSAPrivateKey privateKey = (DSAPrivateKey) keypair.getPrivate();

        FileOutputStream fs1 = new FileOutputStream("./account/data/public.dat");
        fs1.write(privateKey.getEncoded());
        fs1.close();

        FileOutputStream fs2 = new FileOutputStream("./account/data/private.dat");
        fs2.write(privateKey.getEncoded());
        fs2.close();

    }

    public static void loadKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        FileInputStream fsPublicKey = new FileInputStream("./account/data/public.dat");
        BufferedInputStream bfsPublicKey = new BufferedInputStream(fsPublicKey);
        byte[] bytePublicKey = new byte[bfsPublicKey.available()];
        bfsPublicKey.read(bytePublicKey);
        bfsPublicKey.close();
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(
                bytePublicKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

    }

}



