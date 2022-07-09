package GraddleApp;
// APIにリクエストする際に必ず実装するべきMethod一覧を定義している。

// 以下でInterfaceクラスであることを明示しています
public interface MethodInterface {

    // Interfaceに定義した変数は「public static final」が付与されたものとみなす。つまり、実質定数扱いとなる。
    String STR_HOGE = "str_hoge defined by interface";

    // Interfaceクラスには実装を記述することはできない(defaultやstaticが付与された場合の例外を除く)

    //メソッド定義１
    void doGet();

    //メソッド定義２
    void doPost();

    //メソッド定義3 (doPostでオーバーライドされた関数を用意する)
    void doPost(String str);  //戻り値、メソッド名、引き数のみ定義する

}
