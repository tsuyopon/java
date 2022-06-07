# 概要
Servletを試すだけの非常に簡単なサンプルです。

gradleのプラグインにはgrettyを利用しています。このプラグインによってjettyとtomcatを切り替えることができます。


# 実行方法
以下で起動することによって 
```
$ gradlew Servlet:HelloServlet:appRun
```

下記でWebサーバが起動することになる。
http://localhost:8080/


# 確認方法

JSPの確認
```
$ curl http://localhost:8080/myjsp.jsp
<html><body>
ジェイエスピー: Hello JSP World!<br>
Apache Tomcat/10.0.21<br>
java.vm.name: Java HotSpot(TM) 64-Bit Server VM<br>
java.vm.vendor: Oracle Corporation<br>
java.vm.version: 16.0.2+7-67<br>
</body></html>
```

サーブレットの確認
```
$ curl http://localhost:8080/servlet
Hello unauthenticated user
```

サーブレット提供外だとエラーになることを確認
```
$ curl http://localhost:8080/hoge
<!doctype html><html lang="en"><head><title>HTTP Status 404 – Not Found</title><style type="text/css">body {font-family:Tahoma,Arial,sans-serif;} h1, h2, h3, b {color:white;background-color:#525D76;} h1 {font-size:22px;} h2 {font-size:16px;} h3 {font-size:14px;} p {font-size:12px;} a {color:black;} .line {height:1px;background-color:#525D76;border:none;}</style></head><body><h1>HTTP Status 404 – Not Found</h1><hr class="line" /><p><b>Type</b> Status Report</p><p><b>Message</b> The requested resource [&#47;hoge] is not available</p><p><b>Description</b> The origin server did not find a current representation for the target resource or is not willing to disclose that one exists.</p><hr class="line" /><h3>Apache Tomcat/10.0.21</h3></body></html
```
