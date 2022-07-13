
package GraddleApp;

public class App {
    public String getGreeting() {
        return "こんにちは世界!";
    }

    public static void main(String[] args) {

        System.out.println(new App().getGreeting());

        // method1とmethod2はinstanceメソッドなので、インスタンスしてかアクセスする
        Sample sample = new Sample();
        sample.method1();
        sample.method2();

        // method4とmethod5はstaticメソッドなので「クラス.メソッド」でアクセス可能
        Sample.method4();
        Sample.method5();

    }
}
