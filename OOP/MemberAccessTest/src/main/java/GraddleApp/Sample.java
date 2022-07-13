package GraddleApp;

/*
 * メンバー変数はstatic変数とinstance変数の2種類がありますが、これらのメンバー間アクセスには以下のルールがあります。
 *   (1). クラス内で定義したインスタンスメンバ同志、staticメンバ同志は直接アクセス可能
 *   (2). クラス内で定義したインスタンスメンバはクラス内で定義したstaticメンバに直接アクセス可能
 *   (3). クラス内で定義したstaticメンバは、クラス内で定義したインスタンスメンバに直接アクセスできない。アクセスする場合には、インスタンスしてからであればアクセス可能
 */
public class Sample {
    int instanceValue = 100;        // instance変数
    static int staticValue = 200;   // static変数

    int method1() { return instanceValue; }   // (1)
    int method2() { return staticValue; }     // (2)
    //static int method3() { return instanceValue; }  // (3)によりコンパイルエラー。staticメソッド中でインスタンス変数をインスタンス化せずに呼び出せない
    static int method4() { return staticValue; }      // (1)
    static int method5() {                            // (3)によりインスタンス化しているのでアクセス可能
        Sample obj = new Sample();
        return obj.instanceValue;
    }

}
