/*
 * RSSを取得してprintlnで出力するだけのサンプルです。
 * RSSの取得にはURLConnectionクラスを用いていて、InputStreamReaderからレスポンスを取得して文字列用バッファに格納して出力しています。
 *
 * ヤフーニュースで配信しているRSS一覧から取得する
 *    https://news.yahoo.co.jp/rss
 *
 * Yahoo!ニュース・トピックス - 主要から取得する
 *    https://news.yahoo.co.jp/rss/topics/top-picks.xml
 */
package GraddleApp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class App {

    private static final String RSS_URL = "https://news.yahoo.co.jp/rss/topics/top-picks.xml";

    // InputStreamとして取得したレスポンス情報を文字列用バッファに格納します。
    public static String responseToString(InputStream in_response) throws IOException {
        BufferedReader br = null;
        try {
            // ファイルを読み込むバッファドリーダを作成します。
            br = new BufferedReader(new InputStreamReader(in_response));
            // 読み込んだ文字列を保持するストリングバッファを用意します。
            StringBuffer sb = new StringBuffer();
            // ファイルから読み込んだ一文字を保存する変数です。
            int c;
            // ファイルから１文字ずつ読み込み、バッファへ追加します。
            while ((c = br.read()) != -1) {
                sb.append((char) c);
            }
            // バッファの内容を文字列化して返します。
            return sb.toString();
        } finally {
            // リーダを閉じます。例外が発生したかどうかに関わらず実行したい処理がfinally中に記載されます。
            br.close();
        }
    }

    // RSS情報
    public static void main(String[] args) {

        // 下記コメントを外すと非常に大量のログが出力されます
        // 「all」以外も指定できます。詳細は以下を参照のこと
        //  cf. https://docs.oracle.com/en/java/javase/11/security/java-secure-socket-extension-jsse-reference-guide.html#GUID-31B7E142-B874-46E9-8DD0-4E18EC0EB2CF
        //System.setProperty("javax.net.debug", "all");

        String buffered_resp = "";

        // 指定されたRSSから取得する
        try {
            HttpURLConnection urlCon = null;
            URL url = new URL(RSS_URL);
            URLConnection conn;

            // リクエスト
            urlCon = (HttpURLConnection) url.openConnection();
            System.out.println("レスポンスコード:" + urlCon.getResponseCode());
            InputStream in = urlCon.getInputStream();

            // InputStreamを取得してレスポンス内容を変数に格納する
            buffered_resp = responseToString(in);
            System.out.println(buffered_resp);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
