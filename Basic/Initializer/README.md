# 概要
Initializerのサンプルです。

Javaには以下の2つのinitializerが存在します。
- (1) static initializer: static初期化ブロック
- (2) instance initializer: 初期化ブロック


(1) static initializer
staticメソッドやstatic変数にアクセスされた場合に、最初の1度だけそのブロックを実行するものです。
```
	static{
		System.out.println("111");;
	}
```

(2) instance initializer
インスタンスが呼ばれるたびに実行されるブロックです。
```
	{
		System.out.println("111");;
	}
```




# 詳細
# mainを実行する
```
$ gradlew Basic:Initializer:run
```

