package GraddleApp;

// 基底クラスを継承した一般ユーザ用クラス(GeneralUser)を継承した管理者用クラス(AdminUser)を用意します。
public class AdminUser extends GeneralUser {

    public void init(){
        System.out.println("管理者用画面にログインしました。");
        System.out.println("AdminUser: init()");
        super.execute();
    }

    public void execute(){
        System.out.println("AdminUser: execute()");
        super.execute();
    }

    public void render(){
        System.out.println("AdminUser: render()");
        this.renderForAdmin(); // 通常のGeneralUser.render()に加えて管理者クラス特有の処理などあれば実施する。
        super.render();
    }

    public void renderForAdmin(){
        System.out.println("管理者専用のタブ画面を表示させます。");
    }


}
