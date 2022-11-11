package com.demo.chain.client;

import com.demo.chain.contract.HelloWorld;
import com.demo.chain.contract.StoreHash;
import com.demo.pojo.Case;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.fisco.bcos.web3j.crypto.EncryptType;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Properties;

public class StroeHashClient {
    // 获取配置文件路径
    public final String configFile = StroeHashClient.class.getClassLoader().getResource("applicationContext.toml").getPath();
    private BcosSDK bcosSDK;
    private Client client;
    private CryptoKeyPair cryptoKeyPair;
    private StoreHash storeHash;
    private Properties properties;
    private TransactionCallback transactionCallback;

    public StroeHashClient() {
        bcosSDK = BcosSDK.build(configFile);
        // 为群组1初始化client
        client = bcosSDK.getClient(Integer.valueOf(1));
        cryptoKeyPair = client.getCryptoSuite().createKeyPair();
        transactionCallback = new TransactionCallback() {
            @Override
            public void onResponse(TransactionReceipt transactionReceipt) {
                System.out.println(transactionReceipt.toString());
            }
        };

    }

    public void initialize() throws IOException {
        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("./src/main/resources/contract.properties");
        properties.load(fileInputStream);
        String contractAddress = properties.getProperty("addressStoreHash");
        if (contractAddress == null || contractAddress.trim().equals("")) {
            storeHashDeploy();
        } else {
            storeHashLoad();
        }
    }

    public void storeHashLoad() {
        String contractAddress = properties.getProperty("addressStoreHash");
        storeHash = StoreHash.load(contractAddress, client, cryptoKeyPair);

    }

    public void storeHashDeploy() {
        try {
            storeHash = StoreHash.deploy(client, cryptoKeyPair);
            String address = storeHash.getContractAddress();
            System.out.println("deploy success,contract address is" + address);
            properties.setProperty("addressStoreHash", address);
            FileOutputStream fileOutputStream = new FileOutputStream("./src/main/resources/contract.properties");
            properties.store(fileOutputStream, "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void StoreHashIn(int id, byte[] signedhash) {
        BigInteger caseId = BigInteger.valueOf(id);
        storeHash.hashIn(caseId,signedhash,transactionCallback);


    }


}