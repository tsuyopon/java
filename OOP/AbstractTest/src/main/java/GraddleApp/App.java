/*
 * appプロジェクト用のライブラリです。
 * これはUTF-8で記述されています。
 */
package GraddleApp;

public class App {
    public String getGreeting() {
        return "mainを開始します。";
    }

    public static void main(String[] args) {

        // 一般ユーザには独自のプロジェクトが管理するページだけ見せる。
        // 管理者ユーザーには他のプロジェクトが管理するページも含めて全て見える様にしたい。
        // となる場合、同じURLへのリクエスト想定ですが、一般ユーザと管理者ユーザで権限制御が異なることが想定されます。


        // この場合には以下の制御が必要となります (同一クラス上で実施することも可能ですが、この例では分岐するようにしています)

        // 一般ユーザーがアクセスした場合
        GeneralUser generalUser = new GeneralUser();
        generalUser.isAllowed(false);           // ここは本来正当なプロジェクト管轄のユーザーならば「true」にする必要がある。今回はテストなので「false」にする。
        generalUser.mainProcess();

        // 管理者ユーザーがアクセスした場合
        AdminUser adminUser = new AdminUser();
        adminUser.isAllowed(true);
        adminUser.mainProcess();

    }

}
