package com.demo.chain.client;

import com.demo.chain.contract.HelloWorld;
import com.demo.util.Proper;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.client.protocol.response.BlockNumber;
import org.fisco.bcos.sdk.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;

import java.io.*;
import java.util.Properties;

public class HelloWorldClient
{
    // 获取配置文件路径
    public final String configFile = HelloWorldClient.class.getClassLoader().getResource("applicationContext.toml").getPath();
    private BcosSDK bcosSDK;
    private Client client;
    private CryptoKeyPair cryptoKeyPair;
    private HelloWorld helloWorld;
    private Properties properties;
   public HelloWorldClient(){
       bcosSDK =  BcosSDK.build(configFile);
       // 为群组1初始化client
       client = bcosSDK.getClient(Integer.valueOf(1));
       cryptoKeyPair = client.getCryptoSuite().createKeyPair();


   }

   public void initialize() throws IOException {
       properties = new Properties();
       FileInputStream fileInputStream = new FileInputStream("./src/main/resources/contract.properties");
       properties.load(fileInputStream);
       String contractAddress = properties.getProperty("address");
       if (contractAddress == null || contractAddress.trim().equals("")){
           helloDeploy();
       }else {
           helloLoad();
       }
   }

    public void helloLoad()  {
       String contractAddress = properties.getProperty("address");
       helloWorld = HelloWorld.load(contractAddress,client,cryptoKeyPair);

    }

    public void helloDeploy(){
        try {
            helloWorld = HelloWorld.deploy(client, cryptoKeyPair);
            String address = helloWorld.getContractAddress();
            System.out.println("deploy success,contract address is"+address);
            properties.setProperty("addressHello", address);
            FileOutputStream fileOutputStream = new FileOutputStream("./src/main/resources/contract.properties");
            properties.store(fileOutputStream,"");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void registerP(){
        EncryptType.encryptType = 0;
//        Credentials credentials = GenCredential.create();
//        String address = credentials.getEcKeyPair().getPrivateKey().toString(16);
//        String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
//        String publicKey = credentials.getEcKeyPair().getPublicKey().toString(16);
        CryptoSuite cryptoSuite = new CryptoSuite(CryptoType.ECDSA_TYPE);
        CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair();
        String accountAddress = cryptoKeyPair.getAddress();
//        String pemFilePath = "./account/"+accountAddress+".pem";
//        File file = new File(pemFilePath);
//        file.mkdirs();
//        cryptoKeyPair.storeKeyPairWithPem()
        cryptoKeyPair.storeKeyPairWithPemFormat();

    }
    public void helloSet(String content){
       TransactionReceipt receipt = helloWorld.set(content);
    }
    public void helloGet() throws ContractException {
        String getValue = helloWorld.get();
        System.out.println("getValue:"+getValue);

    }

}