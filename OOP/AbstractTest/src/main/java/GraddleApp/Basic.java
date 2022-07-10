package GraddleApp;

abstract public class Basic {

    // 画面の閲覧が許可されているかどうかを表すフラグです。
    boolean allowedEnabled;

    // 画面を見せても良いかどうかを表す
    public void isAllowed(boolean enabled){
        this.allowedEnabled = enabled;
    }

    // メイン処理
    public void mainProcess(){
        if( !this.allowedEnabled ){
            System.out.println("許可されてなければ、ここで本来は終了する(サンプルなので今回は終了しない)");
        } else {
            System.out.println("許可されています。");
        }
        this.init();
        this.execute();
        this.render();
    }

    // 継承する派生クラス側で実装すべき処理
    abstract public void init();
    abstract public void execute();
    abstract public void render();

}
