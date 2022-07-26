/*
 * appプロジェクト用のライブラリです。
 * これはUTF-8で記述されています。
 */
package GraddleApp;

import java.util.List;

public class App {

    public String getGreeting() {
        return "こんにちは世界!";
    }

    public static void main(String[] args) throws ClassNotFoundException {

        // DAOのオブジェクトから、DTOのリストを取得します。
        SakilaDao sakilaDao = new SakilaDao();
        List<SakilaActorDto> list = sakilaDao.getActorItems();

        // 出力します。
        for(SakilaActorDto obj: list){
            System.out.println(obj.getActorId());
            System.out.println(obj.getFirstName());
            System.out.println(obj.getLastName());
        }

    }
}
