package GraddleApp;

public class SakilaActorDto {

    // sakilaデータベース中のactorテーブルについて定義しています。

    private int actor_id;
    private String first_name;
    private String last_name;

    public int getActorId() {
        return actor_id;
    }

    public void setActorId(int actor_id) {
        this.actor_id = actor_id;
    }

    public String getFirstName() {
        return first_name;
    }
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
    public String getLastName() {
        return last_name;
    }
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

}