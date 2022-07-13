/*
 * appプロジェクト用のライブラリです。
 * これはUTF-8で記述されています。
 */
package GraddleApp;

/*
 * 参考: https://itsakura.com/java_block
 */
class Sample{
    // static初期化ブロック (static initializer)
    // staticメソッドやstatic変数が呼ばれる最初の1度だけ以下のブロックが実行されます。
    static{
        System.out.println("static");;
    }
    // 初期化ブロック (instance initializer)
    // インスタンスが呼び出されるたびに呼ばれる
    {
        System.out.println("instance");;
    }
    // コンストラクタ
    Sample() {
        System.out.println("constructer");
    }
}

public class App {
    public String getStartMsg() {
        return "実行開始";
    }

    public static void main(String[] args) {
        System.out.println(new App().getStartMsg());
        new Sample(); // static instance constructer

        System.out.println(new App().getStartMsg());
        new Sample(); // instance constructer
    }
}
