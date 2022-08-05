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

        Connection conn = null;
        CallableStatement cs = null;

        try {

            // See
            //  cf. https://docs.oracle.com/javase/jp/8/docs/api/java/sql/DriverManager.html
            //  cf. https://docs.oracle.com/javase/jp/8/docs/api/java/sql/Connection.html
            conn = DriverManager.getConnection(URL, USER, PASS);

            // 自動的にcommitされないようにする(今回は更新ないので、意識しなくてokなはず)
            conn.setAutoCommit(false);

            // SQLを指定し準備しておく。ストアドプロシージャの実行の場合にはCallableStatementとする必要があるらしい
            // 今回使っている主な関数は以下のリファレンスを参照のこと
            // cf. https://docs.oracle.com/javase/jp/8/docs/api/java/sql/CallableStatement.html
            cs = conn.prepareCall(SQL);

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

            // コミットする (今回はcommit処理ないのですが、更新時は意識する必要があり)
            conn.commit();

            // クローズ処理
            if (cs != null) cs.close();
            if (conn != null) conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            try {
                conn.rollback();       //ロールバックする
                e.printStackTrace();   //エラー内容をコンソールに出力する
            } catch (Exception ex){    // rollbackのエラーで例外補足が必要となる
                System.out.println("rollbackで不備が発生しました。処理を終了します");
            }
        } finally {
            System.out.println("処理が完了しました");
        }
    }
}
