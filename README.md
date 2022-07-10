# 概要
JAVAで自身が試したサンプルコードを配置するためだけのレポジトリです。
以下のGradleAppの雛形をコピーしてそのままcommitしています
- https://github.com/tsuyopon/GradleApp

Spring Boot用のサンプルはここではなくて以下に配置してください。
- https://github.com/tsuyopon/my-springboot-web


# JAVAプロジェクトの追加
別のプロジェクトを追加する場合にはappディレクトリを雛形としてコピーします。
ここではappからapp2でコピーした場合の例を示します。
```
$ cp -r app app2
```

その後、レポジトリ直下のsettings.gradleのincludeにapp2を追加します。
```
$ vim settings.gradle
```

あとはgradlewから以下を実行すれば実行すればサンプルが起動する。
```
$ ./gradlew :app2:run
```

IntelliJからsettings.gradle更新の反映を行た場合には以下を参考にすると良いでしょう
- https://nainaistar.hatenablog.com/entry/2021/03/08/120000

あとはapp2にサンプルコードを追加して、サンプルプログラムとして問題なければ、このレポジトリにcommitすれば良い。

# メモ
- Gradle周りは自身のGradleAppからコピーしてcommitしている。
- oldには昔からあったコードを配置しているだけ。

# 参考資料
- Qiita: Javaを使うなら知っておきたい技術、フレームワーク、ライブラリ、ツールまとめ
  - https://qiita.com/disc99/items/727b51dbe737602a5c91
- awesome-java
  - https://github.com/akullpp/awesome-java
