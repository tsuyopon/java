/*
 * appプロジェクト用のライブラリです。
 * これはUTF-8で記述されています。
 */
package GraddleApp;

// extendsしないクラスはスーパークラスを持たないのではなく、javaライブラリで提供されている「java.lang.Object」クラスをスーパークラスに持ちます。
// つまり、Objectクラスのメンバ(変数やメソッド)のいくつかが引き継がれていることを頭に入れておいてください。以下の資料を確認しておいてください。
// 公式: https://docs.oracle.com/javase/jp/8/docs/api/java/lang/Object.html
// 参考: https://code4u.jp/archives/604
public class App {
    public String getGreeting() {
        return "こんにちは世界!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
