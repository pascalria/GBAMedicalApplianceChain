import com.demo.chain.client.HelloWorldClient;
import com.demo.pojo.Case;
import com.demo.util.BuildKey;
import com.demo.util.EncryptDSAUtil;
import com.demo.util.EncryptSha256Util;
import org.fisco.bcos.channel.client.P12Manager;
import org.fisco.bcos.channel.client.PEMManager;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class test {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException, KeyStoreException, InvalidKeySpecException, NoSuchProviderException {
//        String pubKey = "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAE7aSAtzKLmDk0Ud2wQbfh9kKbF99B0HdB" +
//                "JbAsL0nUloAuwJQf2q/4OgrT8ES6IHc2rwvjz62UbwAupHosd7kdWQ==";
//        String priKey = "MIGNAgEAMBAGByqGSM49AgEGBSuBBAAKBHYwdAIBAQQg4VeeOjnuwo7eL3xEU0E3" +
//                "2C9b1dmtxU3YZCI20n2pQw6gBwYFK4EEAAqhRANCAATtpIC3MouYOTRR3bBBt+H2" +
//                "QpsX30HQd0ElsCwvSdSWgC7AlB/ar/g6CtPwRLogdzavC+PPrZRvAC6keix3uR1Z";
        Case c = new Case(6,"12;23","2022-11-11",4);
        String data = c.toString();


        PEMManager pemManager = new PEMManager();
        FileInputStream fileInputStream = new FileInputStream("account/ecdsa/0x18597af9581460496d7792c4e1e0e58b7be400ac.pem");
        pemManager.load(fileInputStream);
        PrivateKey privateKey =  pemManager.getPrivateKey();
        PublicKey publicKey = pemManager.getPublicKey();
        System.out.println(publicKey);
        //将string转为PrivateKey
//        PrivateKey privateKey = BuildKey.getPrivateKey(priKey);
        //将需要加密的数据进行sha256
        String enData = EncryptSha256Util.getSha256Str(data);
        //DSA加密
        byte[] signDSA = EncryptDSAUtil.DSASign(privateKey,enData);
        System.out.println("result:"+signDSA);


        //将string转为PublicKey
//        PublicKey publicKey = BuildKey.getPublicKey(pubKey);
        //待验证数据
        byte[] verify = signDSA;
        //DSA验证 verify是存在链上的，data是患者提交的
        Boolean ifVerify = EncryptDSAUtil.DSAVerify(publicKey,verify,data);

        System.out.println("verifyResult:"+ifVerify);
    }
}
