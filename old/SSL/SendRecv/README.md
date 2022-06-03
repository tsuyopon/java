# 概要
JavaのSSLサーバ間通信のサンプルプログラム  
truststoreとkeystoreが正しく作成できているのかを確認するためのサンプルプログラムです。

# 詳細
受信用と送信用プログラムが存在します。

- SSLResponseTest.java
  - 受信用プログラム(whileループしてサーバとして起動)
- SSLSocketTest.java
  - 送信用プログラム(クライアント的な役割)

# 参考
- http://blog.maplesystems.co.jp/programming/7056.html

# TODO
- キーの作成方法もしくはスクリプトをここに配置する
