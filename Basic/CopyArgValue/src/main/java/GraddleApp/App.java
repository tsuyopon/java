/*
 * appプロジェクト用のライブラリです。
 * これはUTF-8で記述されています。
 */
package GraddleApp;

// 基本データ型と参照型の違いを理解しておくこと

class Sample {

    // intの場合には引数として基本データ型を受け取ります。
    void method1(int num) {
        num += 10;
        System.out.println("method1: "+num);  // 20
    }

    // int[]の場合には引数として参照を受け取ることになります。
    void method2(int[] array) {
        array[0] += 10;
        System.out.println("method2: "+array[0]);  // 20
    }


}
public class App {
    public String getGreeting() {
        return "こんにちは世界!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        int num = 10;
        int[] array = {10};

        Sample obj = new Sample();
        obj.method1(num);
        System.out.println("main: int: " + num);         // 10  (値は変わっていない)
        obj.method2(array);
        System.out.println("main: int[]: " + array[0]);  // 20  (値は変わっている)
    }
}
