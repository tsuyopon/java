/*
 * appプロジェクト用のライブラリです。
 * これはUTF-8で記述されています。
 */
package GraddleApp;

public class App {
    public String getGreeting() {
        return "こんにちは世界!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
