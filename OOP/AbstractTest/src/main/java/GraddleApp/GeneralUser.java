package GraddleApp;

// 基底クラスを継承した一般ユーザ用クラス(GeneralUser)を用意します。
public class GeneralUser extends Basic {

    // @Override記述は不要

    public void init(){
        System.out.println("GeneralUser: init()");
    }

    public void execute(){
        System.out.println("GeneralUser: execute()");
    }

    public void render(){
        System.out.println("GeneralUser: render()");
    }
}
