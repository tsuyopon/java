package GraddleApp;
// APIにリクエストする際に必ず付与するべきリクエストヘッダ

// 以下でInterfaceクラスであることを明示しています
public interface HeaderInterface {

    // Interfaceクラスには実装を記述することはできない(defaultやstaticが付与された場合の例外を除く)

    // Authorizationヘッダ
    String getAuthorization();

    // Content-Typeヘッダ
    String getContentType();
}
