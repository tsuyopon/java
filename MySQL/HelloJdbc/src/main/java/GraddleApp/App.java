/*
 * appプロジェクト用のライブラリです。
 * これはUTF-8で記述されています。
 */
package GraddleApp;

import java.sql.*;

public class App {

    public String getGreeting() {
        return "こんにちは世界!";
    }

    public static void main(String[] args) throws ClassNotFoundException {

        // Class.forName メソッドでクラスをロードすることで、ドライバ登録ロジックが実行される。JVM実行中に1回だけ呼び出せば良い。
        // 以下の行は今回追加せずとも動くようだ。
        //Class.forName("com.mysql.jdbc.Driver");

        System.out.println(new App().getGreeting());

        // /etc/hostsに記載されているローカルテスト用のMySQLサーバであるmytest.co.jp:3306に接続する。接続するデータベースはsakilaを利用する。
        final String URL
                = "jdbc:mysql://mytest.co.jp:3306/sakila";
        final String USER = "test";
        final String PASS = "password";
        final String SQL = "SELECT  * FROM actor WHERE last_name = ? AND actor_id IN (?, ?) ;";

        /*
        テーブルの状態は以下の通り
            mysql> SELECT  * FROM actor WHERE last_name = "TEMPLE";
            +----------+------------+-----------+---------------------+
            | actor_id | first_name | last_name | last_update         |
            +----------+------------+-----------+---------------------+
            |       53 | MENA       | TEMPLE    | 2006-02-15 04:34:33 |
            |      149 | RUSSELL    | TEMPLE    | 2006-02-15 04:34:33 |
            |      193 | BURT       | TEMPLE    | 2006-02-15 04:34:33 |
            |      200 | THORA      | TEMPLE    | 2006-02-15 04:34:33 |
            +----------+------------+-----------+---------------------+
            4 rows in set (0.10 sec)
         */

        // see. https://docs.oracle.com/javase/jp/6/api/java/sql/DriverManager.html
        try(Connection conn =
                    DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(SQL)){

            // 上に記載されたレコードから抽出する。抽出するのはactor_idが53(=MENA)と149(RUSSEL)
            ps.setString(1,"TEMPLE");
            ps.setInt(2,53);
            ps.setInt(3,149);

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    System.out.println(
                            rs.getInt("actor_id") + " " +
                                    rs.getString("first_name") + " " +
                                    rs.getString("last_name"));
                }
            };
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("処理が完了しました");
        }
    }
}
