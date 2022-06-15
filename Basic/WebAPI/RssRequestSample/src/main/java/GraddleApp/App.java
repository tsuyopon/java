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
            // リーダを閉じます。
            br.close();
        }
    }

    // RSS情報
    public static void main(String[] args) {

        String buffered_resp = "";

        // 指定されたRSSから取得する
        try {
            URL url = new URL(RSS_URL);
            URLConnection conn;
            conn = url.openConnection();
            InputStream in = conn.getInputStream();

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
