import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

public class KeyPairTest {
	public static void main(String[] args) throws Exception {
		String ksType = "JKS";
		String keyStoreFile = "./serverkeystore.jks";
		String keyStorePass = "hogestore";
		String alias = "testkeystore";
		String privateKeyPass = "hogekey";

		KeyStore ks = KeyStore.getInstance(ksType);
		ks.load(new FileInputStream(keyStoreFile), keyStorePass.toCharArray());

		PrivateKey privateKey = (PrivateKey) ks.getKey(alias,
				privateKeyPass.toCharArray());

		X509Certificate certificate = (X509Certificate) ks
				.getCertificate(alias);
		PublicKey publicKey = certificate.getPublicKey();

		System.out.println("********** PrivateKey **********");
		System.out.println(privateKey.toString());
		System.out.println();
		System.out.println("********** PublicKey **********");
		System.out.println(publicKey.toString());
	}
}
