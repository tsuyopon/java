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

        // ここはInterfaceでdefault宣言して実装が呼ばれる。doHeadはSampleApiImplで実装されていないことに注意する。
        impl.doHead();

        // 以下は「SampleApiImpl.getExplain()」だとシンボルが存在しないエラーとなり呼び出せない。
        // これはgetExpalinがSampleApiInterface中でstaticで呼ばれていて、コンパイル時に決定してしまうことに起因する。
        SampleApiInterface.getExplain();
    }
}
