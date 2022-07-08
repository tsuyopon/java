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
        SampleApiImpl impl = new SampleApiImpl();
        impl.doGet();
        impl.doPost();
        impl.doPost("TestString");

    }
}
