package GraddleApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SakilaDao {

    public List<SakilaActorDto> getActorItems() {
        // /etc/hostsに記載されているローカルテスト用のMySQLサーバであるmytest.co.jp:3306に接続する。接続するデータベースはsakilaを利用する。
        final String URL
                = "jdbc:mysql://mytest.co.jp:3306/sakila";
        final String USER = "test";
        final String PASS = "password";
        final String SQL = "SELECT * FROM actor LIMIT 10;";

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

        List<SakilaActorDto> list = new ArrayList<>();

        // see. https://docs.oracle.com/javase/jp/6/api/java/sql/DriverManager.html
        try(Connection conn =
                    DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(SQL)){

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {

                    SakilaActorDto dto = new SakilaActorDto();
                    dto.setActorId(rs.getInt("actor_id"));
                    dto.setFirstName(rs.getString("first_name"));
                    dto.setLastName(rs.getString("last_name"));
                    list.add(dto);


//                    System.out.println(
//                            rs.getInt("actor_id") + " " +
//                                    rs.getString("first_name") + " " +
//                                    rs.getString("last_name"));
                }
            };
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("MySQL接続処理が完了しました。");
        }

        return list;
    }
}
