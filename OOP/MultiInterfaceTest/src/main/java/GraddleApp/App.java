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
        System.out.println(new App().getGreeting());

        // 複数のinterfaceを継承したApiRequestクラスを呼び出してみます。
        ApiRequest impl = new ApiRequest();
        impl.getGetRequest();

        
        // 複数のinterfaceを継承したApiRequestは、それぞれのInterfaceオブジェクトで取り出します。
        // 取り出したオブジェクトから呼び出す機能はそのInterfaceに定義された関数の実装を呼び出す場合に限られます。
        MethodInterface method = new ApiRequest();
        method.doGet();
//        method.getAuthorization();  // これは実行できません。ApiRequestのインスタンスをMethodInterfaceとして取得しているため

        HeaderInterface header = new ApiRequest();
        header.getAuthorization();
//        header.doGet();  // 実行できません。ApiRequestのインスタンスをHeaderInterfaceとして取得しているため


    }
}
