import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class SSLSocketTest {

	public static String KeyPath	= "request.keystore";
	public static String KeyPass	= "request.keypass";
	public static String TrustPath	= "request.truststore";
	public static String TrustPass	= "request.trustpass";

    public static void main(String[] args) {
    	SSLSocketFactory	SSL_SOCKET_FACTORY	= null;
    	SSLSocket 			sslSock				= null;
    	SSLContext			sContext			= null;
    	KeyStore			trustStore			= null;
    	TrustManagerFactory	tmf					= null;
    	KeyStore			keyStore			= null;
    	KeyManagerFactory	kmf					= null;
    	Socket				socket				= null;
        BufferedReader		in					= null;
        BufferedWriter		out					= null;

        try {
        	// KeyStoreの読み込み
        	char[] keyPassChar = null;
        	keyStore = KeyStore.getInstance("JKS");
        	keyPassChar = KeyPass.toCharArray();
        	keyStore.load(new FileInputStream(KeyPath), keyPassChar);
        	kmf = KeyManagerFactory.getInstance("SunX509");
        	kmf.init(keyStore, keyPassChar);

        	// TrustStoreの読み込み
        	char[] trustPassChar = null;
        	trustStore = KeyStore.getInstance("JKS");
        	trustPassChar = TrustPass.toCharArray();
        	trustStore.load(new FileInputStream(TrustPath), trustPassChar);
        	tmf = TrustManagerFactory.getInstance("SunX509");
        	tmf.init(trustStore);

        	KeyManager[] key_managers = null;
        	key_managers = kmf.getKeyManagers();

        	TrustManager[] trust_manager = null;
        	trust_manager = tmf.getTrustManagers();

        	sContext = SSLContext.getInstance("TLS");
        	sContext.init(key_managers, trust_manager, null);

        	SSL_SOCKET_FACTORY = sContext.getSocketFactory();

        	// コネクション確立
        	sslSock=(SSLSocket)SSL_SOCKET_FACTORY.createSocket("localhost", 8080);
        	// 証明書の確認
        	sslSock.startHandshake();
        	System.out.println("認証成功");

        	// 接続に成功したら後はただのSocketと同じ事をすればOK！
        	socket = sslSock;

            out = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            String request = "test";
            out.write(request);
            out.newLine();
            out.flush();
            System.out.println("送信：" + request);

            String response = in.readLine();
            System.out.println("受信：" + response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
