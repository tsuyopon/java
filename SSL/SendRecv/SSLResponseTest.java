import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
public class SSLResponseTest {

	public static String KeyPath	= "./response.keystore";
	public static String KeyPass	= "response.keypass";
	public static String TrustPath	= "./response.truststore";
	public static String TrustPass	= "response.trustpass";

    public static void main(String[] args) {

    	SSLServerSocketFactory	SSL_SOCKET_FACTORY	= null;
    	SSLContext				sContext			= null;
    	KeyStore				trustStore			= null;
    	TrustManagerFactory		tmf					= null;
    	KeyStore				keyStore			= null;
    	KeyManagerFactory		kmf					= null;

        SSLServerSocket serverSocket = null; // サーバ用のソケット
        Socket socket = null; // ソケットをやり取りする為に使用する
        BufferedReader in = null;
        BufferedWriter out = null;

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

        	SSL_SOCKET_FACTORY = sContext.getServerSocketFactory();

            while(true){
                try {
                    serverSocket=(SSLServerSocket)SSL_SOCKET_FACTORY.createServerSocket(8080); // 受信側はポート側のみ定義し、8080ポートを開放する。
                    // クライアント認証をするかどうか
                    serverSocket.setNeedClientAuth(false);

                    socket = serverSocket.accept(); // コネクションの要求が来るまで待機する。

                    out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String str; str = in.readLine(); // クライアント側からの送信を１行読み込む
                    System.out.println("受信：" + str);
                    String responce = "うけとったよー";
                    out.write(responce);
                    out.newLine();
                    out.flush();
                    System.out.println("返信：" + responce);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    try { // 終わった後の後始末。必ずクローズしましょう。
                        if (serverSocket != null) {
                            serverSocket.close();
                        }
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
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}
