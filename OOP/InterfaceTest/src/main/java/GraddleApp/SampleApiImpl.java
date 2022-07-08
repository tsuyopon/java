package GraddleApp;

// Javaでは慣例的に「implements」するものは「xxxImpl.java」というファイル名称にすることが多い様です。


// 「implements」キーワードによりSampleApiInterfaceを実装することを宣言しています。
// Interfaceクラスには複数指定することができますが、ここでは簡単のために1つにしておきます。
public class SampleApiImpl implements SampleApiInterface {

    // SampleApiInterfaceで定義されたI/Fは全て@Overrideで実装しないとエラーになります。

    @Override
    public void doGet() {
        System.out.println("doGet():" + STR_HOGE);
    }

    @Override
    public void doPost() {
        System.out.println("doPost():" + STR_HOGE);
    }

    @Override
    public void doPost(String str) {
        System.out.println("doPost(String str):" + str);
    }

}
