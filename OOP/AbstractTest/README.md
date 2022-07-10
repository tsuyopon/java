# 概要
abstractを利用するサンプルプログラムです。

- InterfaceとAbstractは非常に似ていて混乱しがちなので、ちゃんと違いを理解しましょう
  - Interfaceはクラスではないが、Abstractはクラス
  - Interfaceは変数を宣言しても定数扱いだが、Abstractは通常のクラスと同様の扱い
  - Interfaceは多重継承可能だが、Abstractは違う

abstractとinterface使い分けとしては、このようなイメージだと思います。
- Interface: 仕様のI/Fとロジックの分離。クラスへの仕様の強制適用
- Abstract: ロジックの重複定義を避けるために利用されるもの

# 実装イメージ

一般ユーザ用と管理者用のF/Wの実装を強制します。
今回の以下のクラスを用意しています。これらは、リストを表示するような画面での一般ユーザと管理者のクラスを表しています。

- Base.java        (基底クラス)
- GeneralUser.java (基底クラスであるBaseを継承)
- AdminUser.java   (GeneralUserを継承)

基本的には多くのビジネスロジック処理は管理者も一般ユーザと一致しているので、AdminUserはGeneralUserを継承しています。同一のロジックはsuper.xxx()で呼び出しています。
管理者側に必要な追加処理があればその処理は別途呼び出しています。

App.javaの中からはGeneralUserかAdminUserかを判定して、画面閲覧許可を指示した上で、メイン処理を実行しています。

# mainを実行する
```
$ gradlew OOP:AbstractTest run
```

