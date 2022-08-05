/*
 * MySQLのプロシージャを実行する非常に単純なサンプルプログラムです。
 * これはUTF-8で記述されています。
 */
package GraddleApp;

import java.sql.*;

public class App {

    public String getGreeting() {
        return "プロシージャ実行処理を開始します";
    }

    public static void main(String[] args) throws ClassNotFoundException {

        // Class.forName メソッドでクラスをロードすることで、ドライバ登録ロジックが実行される。JVM実行中に1回だけ呼び出せば良い。
        // 以下の行は今回追加せずとも動くようだ。
        //Class.forName("com.mysql.jdbc.Driver");

        System.out.println(new App().getGreeting());

        // /etc/hostsに記載されているローカルテスト用のMySQLサーバであるmytest.co.jp:3306に接続する。接続するデータベースはsakilaを利用する。
        final String URL = "jdbc:mysql://mytest.co.jp:3306/sakila";
        final String USER = "test";
        final String PASS = "password";

        // film_in_stock は 第１、第２引数はINPUT、第３引数はOUTPUTです。
        // cf. https://dev.mysql.com/doc/sakila/en/sakila-structure-procedures-film_in_stock.html
        final String SQL = "CALL film_in_stock(?, ?, ?);";

        try(Connection conn =
                    DriverManager.getConnection(URL, USER, PASS) ){

            // 自動的にcommitされないようにする(今回は不要かも!?)
            conn.setAutoCommit(false);

            // SQLを指定し準備しておく
            CallableStatement cs = conn.prepareCall(SQL);

            // 3番目の引数は OUTパラメータでINTEGERが帰ってくる
            cs.registerOutParameter(3, Types.INTEGER);

            // INPUTで指定する値は型によってsetするハンドラを分ける (setInt, setString, etc...)
            cs.setInt(1, 1);
            cs.setInt(2, 1);

            // プロシージャを実行する
            cs.executeUpdate();

            // 実行結果取得 (getInt, getString, getArrayなど型によって使い分ける)
            int ret = cs.getInt(3);

            // 結果を出力する
            System.out.println("プロシージャ実行により取得したレコード数は"+ret+"件です。");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("処理が完了しました");
        }
    }
}
