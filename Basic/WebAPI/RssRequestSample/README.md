# 概要
RSSから取得してその内容を出力するだけの非常に単純なサンプルプログラムです。

# 詳細
# mainを実行する
```
$ gradlew Basic:RssRequestSample:run
```

以下のコメントを外すと大量のログが出力されます。
```
System.setProperty("javax.net.debug", "all");
```

# 参考

以下の
```
System.setProperty("javax.net.debug", "all");
```
