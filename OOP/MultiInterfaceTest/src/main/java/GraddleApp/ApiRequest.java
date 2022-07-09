package GraddleApp;

// abstractと異なりinterfaceの継承は多重継承が可能となっています。
// 以下の例ではこのApiRequestクラスが外部のAPIにリクエストするために必要な下記情報を定義しなければなりません
//  - リクエストするべきMethodのハンドラ(doGet, doPostなど)
//  - リクエストに必要なHeaderを取得するハンドラ(getAuthorization, getContentType)を定義する必要があります。
public class ApiRequest implements MethodInterface, HeaderInterface {

    public void getGetRequest(){
        // 本来はヘッダを指定したリクエストをdoGetに引き渡して、そのリクエストを書きます。
        this.getAuthorization();
        this.getContentType();
        this.doGet();
    }

    /*
     * Method Interfaceに定義されているAPIの実装
     */
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

    /*
     * Header Interfaceに定義されているAPIの実装
     */
    @Override
    public String getAuthorization() {
        System.out.println("getAuthorization():");
        return "Authorization: Basic XXXXXXXX==";
    }

    @Override
    public String  getContentType() {
        System.out.println("getContentType():");
        return "Content-Type: text/html";
    }

}
