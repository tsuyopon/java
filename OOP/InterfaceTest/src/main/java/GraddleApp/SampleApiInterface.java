package GraddleApp;

// 以下でInterfaceクラスであることを明示しています
public interface SampleApiInterface {

    // Interfaceに定義した変数は「public static final」が付いているものとみなされる。
    String STR_HOGE = "str_hoge defined by interface";

    // Interfaceクラスには実装を記述することはできない。

    //メソッド定義１
    void doGet();

    //メソッド定義２
    void doPost();

    //メソッド定義3 (doPostでオーバーライドされた関数を用意する)
    void doPost(String str);  //戻り値、メソッド名、引き数のみ定義する

}
