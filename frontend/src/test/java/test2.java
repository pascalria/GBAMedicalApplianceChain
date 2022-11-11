import com.demo.util.EncryptDSAUtil;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

public class test2 {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, InvalidKeySpecException {
//        EncryptDSAUtil.CreateKey();
        EncryptDSAUtil.loadKey();
    }
}
