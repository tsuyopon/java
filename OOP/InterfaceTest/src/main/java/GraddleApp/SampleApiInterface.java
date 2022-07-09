package GraddleApp;

// 以下でInterfaceクラスであることを明示しています
public interface SampleApiInterface {

    // Interfaceに定義した変数は「public static final」が付いているものとみなされる。実質、定数扱いとなる。
    String STR_HOGE = "str_hoge defined by interface";

    // Interfaceクラスには実装を記述することはできない。
    // ただし、defaultとstaticだけは実装定義可能です。

    //メソッド定義１
    void doGet();

    //メソッド定義２
    void doPost();

    //メソッド定義3 (doPostでオーバーライドされた関数を用意する)
    void doPost(String str);  //戻り値、メソッド名、引き数のみ定義する

    //メソッド定義4 (default指定)
    default void doHead() {
        System.out.println("default void doHead()");
    }

    //メソッド定義5 (static指定なので、インスタンス化しなくても利用可能)
    static String getExplain(){
        return "このクラスはWebAPIのサンプルInterfaceクラスです。";
    }
}
