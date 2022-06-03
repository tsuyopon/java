# 概要
単純にGradleをいじってみるだけのサンプルです。

settings.gradleには複数プロジェクトの指定が記載されています。

# 前提
以下のコマンドにより生成したサンプルプロジェクトです
```
$ gradle init --type java-application
```

初回レポジトリ取得時はGradle Wrapperスクリプトからインストールを行います。
```
$ gradlew -v
```

# 詳細

### ビルド内の全てのプロジェクト一覧を表示する
```
$ ./gradlew -q projects

------------------------------------------------------------
Root project 'GraddleApp'
------------------------------------------------------------

Root project 'GraddleApp'
+--- Project ':app'
+--- Project ':lib'
\--- Project ':services'
     \--- Project ':services:app'

To see a list of the tasks of a project, run gradlew <project-path>:tasks
For example, try running gradlew :app:tasks

```

### テスト実行する
```
$ ./gradlew check

> Configure project :lib
lib設定フェーズ１
lib設定フェーズ２
lib設定フェーズ３
lib設定フェーズ４
lib設定フェーズ５

BUILD SUCCESSFUL in 2s
9 actionable tasks: 6 executed, 3 up-to-date
```

### アプリケーションを実行する
```
$ ./gradlew run

> Configure project :lib
lib設定フェーズ１
lib設定フェーズ２
lib設定フェーズ３
lib設定フェーズ４
lib設定フェーズ５

> Task :app:run
Hello World!

> Task :lib:run
This is library

> Task :services:app:run
This is Service!

BUILD SUCCESSFUL in 774ms
6 actionable tasks: 3 executed, 3 up-to-date
```

控えめな出力にさせることもできます。
```
$ ./gradlew run -q
lib設定フェーズ１
lib設定フェーズ２
lib設定フェーズ３
lib設定フェーズ４
lib設定フェーズ５
Hello World!
This is library
This is Service!
```

### パッケージングを行う
```
$ ./gradlew build

BUILD SUCCESSFUL in 1s
7 actionable tasks: 7 executed
```

実行するとbuildディレクトリが作成され、その中にパッケージングされたapp.tarやapp.zipが生成されます。
```
$ find . -name build
./app/build
./lib/build
./services/app/build
$ ls ./app/build/distributions/
app.tar app.zip
```

ちなみにレポジトリ直下のbuild.gradleに「version = '0.0.1-SNAPSHOT'」を後で付与したら次のように名称が変わりました。
```
$ find . -name "*SNAPSHOT*"
./app/build/libs/app-0.0.1-SNAPSHOT.jar
./app/build/distributions/app-0.0.1-SNAPSHOT.zip
./app/build/distributions/app-0.0.1-SNAPSHOT.tar
./lib/build/libs/lib-0.0.1-SNAPSHOT.jar
./lib/build/distributions/lib-0.0.1-SNAPSHOT.zip
./lib/build/distributions/lib-0.0.1-SNAPSHOT.tar
./services/app/build/libs/app-0.0.1-SNAPSHOT.jar
./services/app/build/distributions/app-0.0.1-SNAPSHOT.zip
./services/app/build/distributions/app-0.0.1-SNAPSHOT.tar
./services/build/libs/services-0.0.1-SNAPSHOT.jar
```


### buildにより生成されたbuildディレクトリを削除する
buildを実行するとそれぞれのプロジェクトでbuildディレクトリが生成されます。
```
$ ./gradlew -q build
$ find . -name build
./app/build
./lib/build
./services/app/build
```

自動で生成されたbuildディレクトリについてはcleanで一括で削除することができます。
```
$ ./gradlew -q clean
$ find . -name build
$ 
```

### 特定のプロジェクトのタスクだけを実行させる。
以下はビルドの例ですが、何も引数を指定しないとapp, lib, services:appの3つのsettings.gradleのincludeで指定したディレクトリをビルドします。
```
$ find . -name build
$ ./gradlew -q build
$ find . -name build
./app/build
./lib/build
./services/app/build
$ rm -rf ./app/build ./lib/build ./services/app/build
```

続いて、以下の例ではlibプロジェクトのみbuildタスクを実行します。:lib:buildと指定します。
タスクは:(プロジェクト名):(タスク名)で実行します。
```
$ ./gradlew -q :lib:build
$ find . -name build
./lib/build
```

### INFO出力を表示する
多くの情報を表示します。以下はrunを指定していますがcheck, buildなどでも変わります。
```
$ ./gradlew run -i
Initialized native services in: /Users/tsuyoshi/.gradle/native
Initialized jansi services in: /Users/tsuyoshi/.gradle/native
The client will now receive all logging from the daemon (pid: 70872). The daemon log file: /Users/tsuyoshi/.gradle/daemon/7.2/daemon-70872.out.log
Starting 29th build in daemon [uptime: 38 mins 35.656 secs, performance: 100%, non-heap usage: 30% of 256 MiB]
Using 8 worker leases.
Now considering [/Users/tsuyoshi/GradleApp, /Users/tsuyoshi/GradleApp/hoge] as hierarchies to watch
Watching the file system is configured to be enabled if available
File system watching is active
Starting Build
Settings evaluated using settings file '/Users/tsuyoshi/GradleApp/settings.gradle'.
Projects loaded. Root project using build file '/Users/tsuyoshi/GradleApp/build.gradle'.
Included projects: [root project 'GraddleApp', project ':app', project ':lib', project ':services', project ':services:app']

> Configure project :
Evaluating root project 'GraddleApp' using build file '/Users/tsuyoshi/GradleApp/build.gradle'.

> Configure project :app
Evaluating project ':app' using build file '/Users/tsuyoshi/GradleApp/app/build.gradle'.

> Configure project :lib
Evaluating project ':lib' using build file '/Users/tsuyoshi/GradleApp/lib/build.gradle'.

> Configure project :services
Evaluating project ':services' using build file '/Users/tsuyoshi/GradleApp/services/build.gradle'.

> Configure project :services:app
Evaluating project ':services:app' using build file '/Users/tsuyoshi/GradleApp/services/app/build.gradle'.
All projects evaluated.
Selected primary task 'run' from project :
Tasks to be executed: [task ':app:compileJava', task ':app:processResources', task ':app:classes', task ':app:run', task ':lib:compileJava', task ':lib:processResources', task ':lib:classes', task ':lib:run', task ':services:app:compileJava', task ':services:app:processResources', task ':services:app:classes', task ':services:app:run']
Tasks that were excluded: []
:app:compileJava (Thread[Execution worker for ':',5,main]) started.

> Task :app:compileJava UP-TO-DATE
Caching disabled for task ':app:compileJava' because:
  Build cache is disabled
Skipping task ':app:compileJava' as it is up-to-date.
:app:compileJava (Thread[Execution worker for ':',5,main]) completed. Took 0.008 secs.
:app:processResources (Thread[Execution worker for ':',5,main]) started.

> Task :app:processResources NO-SOURCE
file or directory '/Users/tsuyoshi/GradleApp/app/src/main/resources', not found
Skipping task ':app:processResources' as it has no source files and no previous output files.
:app:processResources (Thread[Execution worker for ':',5,main]) completed. Took 0.0 secs.
:app:classes (Thread[Execution worker for ':',5,main]) started.

> Task :app:classes UP-TO-DATE
Skipping task ':app:classes' as it has no actions.
:app:classes (Thread[Execution worker for ':',5,main]) completed. Took 0.0 secs.
:app:run (Thread[Execution worker for ':',5,main]) started.

> Task :app:run
Caching disabled for task ':app:run' because:
  Build cache is disabled
Task ':app:run' is not up-to-date because:
  Task has not declared any outputs despite executing actions.
Starting process 'command '/Library/Java/JavaVirtualMachines/jdk-16.0.2.jdk/Contents/Home/bin/java''. Working directory: /Users/tsuyoshi/GradleApp/app Command: /Library/Java/JavaVirtualMachines/jdk-16.0.2.jdk/Contents/Home/bin/java -Dfile.encoding=UTF-8 -Duser.country=JP -Duser.language=ja -Duser.variant -cp /Users/tsuyoshi/GradleApp/app/build/classes/java/main:/Users/tsuyoshi/GradleApp/app/build/resources/main:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.guava/guava/30.1.1-jre/87e0fd1df874ea3cbe577702fe6f17068b790fd8/guava-30.1.1-jre.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.guava/failureaccess/1.0.1/1dcf1de382a0bf95a3d8b0849546c88bac1292c9/failureaccess-1.0.1.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/b421526c5f297295adef1c886e5246c39d4ac629/listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.code.findbugs/jsr305/3.0.2/25ea2e8b0c338a877313bd4672d3fe056ea78f0d/jsr305-3.0.2.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/org.checkerframework/checker-qual/3.8.0/6b83e4a33220272c3a08991498ba9dc09519f190/checker-qual-3.8.0.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.errorprone/error_prone_annotations/2.5.1/562d366678b89ce5d6b6b82c1a073880341e3fba/error_prone_annotations-2.5.1.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.j2objc/j2objc-annotations/1.3/ba035118bc8bac37d7eff77700720999acd9986d/j2objc-annotations-1.3.jar GraddleApp.App
Successfully started process 'command '/Library/Java/JavaVirtualMachines/jdk-16.0.2.jdk/Contents/Home/bin/java''
Hello World!
:app:run (Thread[Execution worker for ':',5,main]) completed. Took 0.051 secs.
:lib:compileJava (Thread[Execution worker for ':',5,main]) started.

> Task :lib:compileJava UP-TO-DATE
Caching disabled for task ':lib:compileJava' because:
  Build cache is disabled
Skipping task ':lib:compileJava' as it is up-to-date.
:lib:compileJava (Thread[Execution worker for ':',5,main]) completed. Took 0.003 secs.
:lib:processResources (Thread[Execution worker for ':',5,main]) started.

> Task :lib:processResources NO-SOURCE
file or directory '/Users/tsuyoshi/GradleApp/lib/src/main/resources', not found
Skipping task ':lib:processResources' as it has no source files and no previous output files.
:lib:processResources (Thread[Execution worker for ':',5,main]) completed. Took 0.0 secs.
:lib:classes (Thread[Execution worker for ':',5,main]) started.

> Task :lib:classes UP-TO-DATE
Skipping task ':lib:classes' as it has no actions.
:lib:classes (Thread[Execution worker for ':',5,main]) completed. Took 0.0 secs.
:lib:run (Thread[Execution worker for ':',5,main]) started.

> Task :lib:run
Caching disabled for task ':lib:run' because:
  Build cache is disabled
Task ':lib:run' is not up-to-date because:
  Task has not declared any outputs despite executing actions.
Starting process 'command '/Library/Java/JavaVirtualMachines/jdk-16.0.2.jdk/Contents/Home/bin/java''. Working directory: /Users/tsuyoshi/GradleApp/lib Command: /Library/Java/JavaVirtualMachines/jdk-16.0.2.jdk/Contents/Home/bin/java -Dfile.encoding=UTF-8 -Duser.country=JP -Duser.language=ja -Duser.variant -cp /Users/tsuyoshi/GradleApp/lib/build/classes/java/main:/Users/tsuyoshi/GradleApp/lib/build/resources/main:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.guava/guava/30.1.1-jre/87e0fd1df874ea3cbe577702fe6f17068b790fd8/guava-30.1.1-jre.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.guava/failureaccess/1.0.1/1dcf1de382a0bf95a3d8b0849546c88bac1292c9/failureaccess-1.0.1.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/b421526c5f297295adef1c886e5246c39d4ac629/listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.code.findbugs/jsr305/3.0.2/25ea2e8b0c338a877313bd4672d3fe056ea78f0d/jsr305-3.0.2.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/org.checkerframework/checker-qual/3.8.0/6b83e4a33220272c3a08991498ba9dc09519f190/checker-qual-3.8.0.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.errorprone/error_prone_annotations/2.5.1/562d366678b89ce5d6b6b82c1a073880341e3fba/error_prone_annotations-2.5.1.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.j2objc/j2objc-annotations/1.3/ba035118bc8bac37d7eff77700720999acd9986d/j2objc-annotations-1.3.jar GraddleLib.App
Successfully started process 'command '/Library/Java/JavaVirtualMachines/jdk-16.0.2.jdk/Contents/Home/bin/java''
This is library
:lib:run (Thread[Execution worker for ':',5,main]) completed. Took 0.054 secs.
:services:app:compileJava (Thread[Execution worker for ':',5,main]) started.

> Task :services:app:compileJava UP-TO-DATE
Caching disabled for task ':services:app:compileJava' because:
  Build cache is disabled
Skipping task ':services:app:compileJava' as it is up-to-date.
:services:app:compileJava (Thread[Execution worker for ':',5,main]) completed. Took 0.003 secs.
:services:app:processResources (Thread[Execution worker for ':',5,main]) started.

> Task :services:app:processResources NO-SOURCE
file or directory '/Users/tsuyoshi/GradleApp/services/app/src/main/resources', not found
Skipping task ':services:app:processResources' as it has no source files and no previous output files.
:services:app:processResources (Thread[Execution worker for ':',5,main]) completed. Took 0.0 secs.
:services:app:classes (Thread[Execution worker for ':',5,main]) started.

> Task :services:app:classes UP-TO-DATE
Skipping task ':services:app:classes' as it has no actions.
:services:app:classes (Thread[Execution worker for ':',5,main]) completed. Took 0.0 secs.
:services:app:run (Thread[Execution worker for ':',5,main]) started.

> Task :services:app:run
Caching disabled for task ':services:app:run' because:
  Build cache is disabled
Task ':services:app:run' is not up-to-date because:
  Task has not declared any outputs despite executing actions.
Starting process 'command '/Library/Java/JavaVirtualMachines/jdk-16.0.2.jdk/Contents/Home/bin/java''. Working directory: /Users/tsuyoshi/GradleApp/services/app Command: /Library/Java/JavaVirtualMachines/jdk-16.0.2.jdk/Contents/Home/bin/java -Dfile.encoding=UTF-8 -Duser.country=JP -Duser.language=ja -Duser.variant -cp /Users/tsuyoshi/GradleApp/services/app/build/classes/java/main:/Users/tsuyoshi/GradleApp/services/app/build/resources/main:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.guava/guava/30.1.1-jre/87e0fd1df874ea3cbe577702fe6f17068b790fd8/guava-30.1.1-jre.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.guava/failureaccess/1.0.1/1dcf1de382a0bf95a3d8b0849546c88bac1292c9/failureaccess-1.0.1.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/b421526c5f297295adef1c886e5246c39d4ac629/listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.code.findbugs/jsr305/3.0.2/25ea2e8b0c338a877313bd4672d3fe056ea78f0d/jsr305-3.0.2.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/org.checkerframework/checker-qual/3.8.0/6b83e4a33220272c3a08991498ba9dc09519f190/checker-qual-3.8.0.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.errorprone/error_prone_annotations/2.5.1/562d366678b89ce5d6b6b82c1a073880341e3fba/error_prone_annotations-2.5.1.jar:/Users/tsuyoshi/.gradle/caches/modules-2/files-2.1/com.google.j2objc/j2objc-annotations/1.3/ba035118bc8bac37d7eff77700720999acd9986d/j2objc-annotations-1.3.jar GraddleService.App
Successfully started process 'command '/Library/Java/JavaVirtualMachines/jdk-16.0.2.jdk/Contents/Home/bin/java''
This is Service!
:services:app:run (Thread[Execution worker for ':',5,main]) completed. Took 0.054 secs.

BUILD SUCCESSFUL in 758ms
6 actionable tasks: 3 executed, 3 up-to-date
Watched directory hierarchies: [/Users/tsuyoshi/GradleApp]
```

### DEBUG出力を表示する
非常に多くのJAVAの出力が行われます。
```
$ ./gradlew run -d

(snip)
2022-04-22T00:05:30.744+0900 [DEBUG] [org.gradle.launcher.daemon.client.DaemonClientConnection] thread 18: dispatching class org.gradle.launcher.daemon.protocol.CloseInput
2022-04-22T00:05:30.745+0900 [DEBUG] [org.gradle.launcher.daemon.client.DaemonClient] Received result Success[value=org.gradle.launcher.exec.BuildActionResult@106cc338] from daemon DaemonInfo{pid=70872, address=[e1239d41-d3e7-4762-8465-7ccd339722c1 port:54398, addresses:[/127.0.0.1]], state=Idle, lastBusy=1650553350190, context=DefaultDaemonContext[uid=b99dad3a-72fb-4e8f-8a6c-b95d59d76259,javaHome=/Library/Java/JavaVirtualMachines/jdk-16.0.2.jdk/Contents/Home,daemonRegistryDir=/Users/tsuyoshi/.gradle/daemon,pid=70872,idleTimeout=10800000,priority=NORMAL,daemonOpts=--add-opens,java.base/java.util=ALL-UNNAMED,--add-opens,java.base/java.lang=ALL-UNNAMED,--add-opens,java.base/java.lang.invoke=ALL-UNNAMED,--add-opens,java.base/java.util=ALL-UNNAMED,--add-opens,java.prefs/java.util.prefs=ALL-UNNAMED,--add-opens,java.prefs/java.util.prefs=ALL-UNNAMED,--add-opens,java.base/java.nio.charset=ALL-UNNAMED,--add-opens,java.base/java.net=ALL-UNNAMED,--add-opens,java.base/java.util.concurrent.atomic=ALL-UNNAMED,-XX:MaxMetaspaceSize=256m,-XX:+HeapDumpOnOutOfMemoryError,-Xms256m,-Xmx512m,-Dfile.encoding=UTF-8,-Duser.country=JP,-Duser.language=ja,-Duser.variant]} (build should be done).
2022-04-22T00:05:30.745+0900 [DEBUG] [org.gradle.launcher.daemon.client.DaemonClientConnection] thread 1: dispatching class org.gradle.launcher.daemon.protocol.Finished
2022-04-22T00:05:30.745+0900 [DEBUG] [org.gradle.launcher.daemon.client.DaemonClientConnection] thread 1: connection stop
2022-04-22T00:05:30.747+0900 [LIFECYCLE] [org.gradle.launcher.cli.DebugLoggerWarningAction] 
(snip)
```


### libプロジェクトのbuild.gradleの設定を見てみる

libプロジェクト中のbuild.gradleは出力やタスクの指定が行われています。
lib/build.gradleの中を確認して、以下のタスクが実行された際にどこが呼び出されているかを確認してみましょう。

```
$ ./gradlew :lib:run

> Configure project :lib
lib設定フェーズ１
lib設定フェーズ２
lib設定フェーズ３
lib設定フェーズ４
lib設定フェーズ５

> Task :lib:run
This is library

BUILD SUCCESSFUL in 702ms
2 actionable tasks: 1 executed, 1 up-to-date
```

libのfooというタスクを呼び出すと「lib実行フェーズ」が呼び出されていることが確認できます。
```
$ ./gradlew :lib:foo

> Configure project :lib
lib設定フェーズ１
lib設定フェーズ２
lib設定フェーズ３
lib設定フェーズ４
lib設定フェーズ５

> Task :lib:foo
lib実行フェーズ１
lib実行フェーズ２

BUILD SUCCESSFUL in 588ms
1 actionable task: 1 executed
```

### dependOnの挙動を確認する

services/app/build.gradleにはdependOnとしてfooが呼ばれたらbarに依存する旨が記載されている。
これによって、fooを実行すると、その前にbarも実行されることが確認できます。
```
$ ./gradlew :services:app:foo

> Configure project :lib
lib設定フェーズ１
lib設定フェーズ２
lib設定フェーズ３
lib設定フェーズ４
lib設定フェーズ５

> Task :services:app:bar
bar from service

> Task :services:app:foo
foo from service

BUILD SUCCESSFUL in 575ms
2 actionable tasks: 2 executed
```

### 設定ファイルgradle.propertiesから環境変数を取得する

appプロジェクトにhelloenvタスクが定義されています。
build.gradleが存在するディレクトリ中にgradle.propertiesファイルがあればそこから環境変数を読み込みます。
```
$ cat app/gradle.properties 
hoge=HOGE
```

helloenvを実行してみると環境変数を取得・表示しています。
```
$ ./gradlew :app:helloenv

> Task :app:helloenv
hoge=HOGE

BUILD SUCCESSFUL in 583ms
1 actionable task: 1 executed
```

### javadocを生成する
javaプラグイン中のjavadocタスクを実行すると build/docs/javadocを生成してくれます。
```
$ ./gradlew javadoc

$ find . -name javadoc
./app/build/docs/javadoc
./app/build/tmp/javadoc
./lib/build/docs/javadoc
./lib/build/tmp/javadoc
./services/app/build/docs/javadoc
./services/app/build/tmp/javadoc
```

### タスクを確認する
```
$ gradle -q tasks --all | grep "services:app:"
services:app:run - Runs this project as a JVM application
services:app:assemble - Assembles the outputs of this project.
services:app:build - Assembles and tests this project.
services:app:buildDependents - Assembles and tests this project and all projects that depend on it.
services:app:buildNeeded - Assembles and tests this project and all projects it depends on.
services:app:classes - Assembles main classes.
services:app:clean - Deletes the build directory.
services:app:jar - Assembles a jar archive containing the main classes.
services:app:testClasses - Assembles test classes.
services:app:assembleDist - Assembles the main distributions
services:app:distTar - Bundles the project as a distribution.
services:app:distZip - Bundles the project as a distribution.
services:app:installDist - Installs the project as a distribution as-is.
services:app:javadoc - Generates Javadoc API documentation for the main source code.
services:app:buildEnvironment - Displays all buildscript dependencies declared in project ':services:app'.
services:app:dependencies - Displays all dependencies declared in project ':services:app'.
services:app:dependencyInsight - Displays the insight into a specific dependency in project ':services:app'.
services:app:help - Displays a help message.
services:app:javaToolchains - Displays the detected java toolchains.
services:app:outgoingVariants - Displays the outgoing variants of project ':services:app'.
services:app:projects - Displays the sub-projects of project ':services:app'.
services:app:properties - Displays the properties of project ':services:app'.
services:app:tasks - Displays the tasks runnable from project ':services:app'.
services:app:projectReport - Generates a report about your project.
services:app:check - Runs all checks.
services:app:test - Runs the unit tests.
services:app:bar
services:app:compileJava - Compiles main Java source.
services:app:compileTestJava - Compiles test Java source.
services:app:components - Displays the components produced by project ':services:app'. [deprecated]
services:app:dependencyReport - Generates a report about your library dependencies.
services:app:dependentComponents - Displays the dependent components of components in project ':services:app'. [deprecated]
services:app:foo
services:app:htmlDependencyReport - Generates an HTML report about your library dependencies.
services:app:model - Displays the configuration model of project ':services:app'. [deprecated]
services:app:processResources - Processes main resources.
services:app:processTestResources - Processes test resources.
services:app:propertyReport - Generates a report about your properties.
services:app:startScripts - Creates OS specific scripts to run the project as a JVM application.
services:app:taskReport - Generates a report about your tasks.
```

独自定義したbarやfooも使えるようになっている
```
$ gradle -q tasks --all | grep "services:app:" | grep -ie bar -ie foo
services:app:bar
services:app:foo
```

適用するプラグインでも独自でタスクが定義されます。
例えば、gradleの基礎として使われているjavaプラグインですが、以下に記載されています。
- http://gradle.monochromeroad.com/docs/userguide/java_plugin.html


app以下に追加したhelloworldも「Other tasks」の下に存在する
```
$ ./gradlew :app:tasks --all

(snip)

Other tasks
-----------
helloWorld - This is a very tiny helloWorld.
(snip)
```

# project-reportプラグイン


### 依存情報を生成する
```
$ ./gradlew dependencyReport

> Configure project :lib
lib設定フェーズ１
lib設定フェーズ２
lib設定フェーズ３
lib設定フェーズ４
lib設定フェーズ５

> Task :dependencyReport
See the report at: file:///Users/tsuyoshi/GradleApp/build/reports/project/dependencies.txt

> Task :app:dependencyReport
See the report at: file:///Users/tsuyoshi/GradleApp/app/build/reports/project/dependencies.txt

> Task :lib:dependencyReport
See the report at: file:///Users/tsuyoshi/GradleApp/lib/build/reports/project/dependencies.txt

> Task :services:dependencyReport
See the report at: file:///Users/tsuyoshi/GradleApp/services/build/reports/project/dependencies.txt

> Task :services:app:dependencyReport
See the report at: file:///Users/tsuyoshi/GradleApp/services/app/build/reports/project/dependencies.txt

BUILD SUCCESSFUL in 646ms
5 actionable tasks: 5 executed
```

### 依存情報を標準出力する
以下の出力例はlibプロジェクトの依存を表示しています。
```
$ ./gradlew :lib:dependencies

> Task :lib:dependencies

------------------------------------------------------------
Project ':lib'
------------------------------------------------------------

annotationProcessor - Annotation processors and their dependencies for source set 'main'.
No dependencies

apiElements - API elements for main. (n)
No dependencies

archives - Configuration for archive artifacts. (n)
No dependencies

compileClasspath - Compile classpath for source set 'main'.
\--- com.google.guava:guava:30.1.1-jre
     +--- com.google.guava:failureaccess:1.0.1
     +--- com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava
     +--- com.google.code.findbugs:jsr305:3.0.2
     +--- org.checkerframework:checker-qual:3.8.0
     +--- com.google.errorprone:error_prone_annotations:2.5.1
     \--- com.google.j2objc:j2objc-annotations:1.3

compileOnly - Compile only dependencies for source set 'main'. (n)
No dependencies

default - Configuration for default artifacts. (n)
No dependencies

implementation - Implementation only dependencies for source set 'main'. (n)
\--- com.google.guava:guava:30.1.1-jre (n)

runtimeClasspath - Runtime classpath of source set 'main'.
\--- com.google.guava:guava:30.1.1-jre
     +--- com.google.guava:failureaccess:1.0.1
     +--- com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava
     +--- com.google.code.findbugs:jsr305:3.0.2
     +--- org.checkerframework:checker-qual:3.8.0
     +--- com.google.errorprone:error_prone_annotations:2.5.1
     \--- com.google.j2objc:j2objc-annotations:1.3

runtimeElements - Elements of runtime for main. (n)
No dependencies

runtimeOnly - Runtime only dependencies for source set 'main'. (n)
No dependencies

testAnnotationProcessor - Annotation processors and their dependencies for source set 'test'.
No dependencies

testCompileClasspath - Compile classpath for source set 'test'.
+--- com.google.guava:guava:30.1.1-jre
|    +--- com.google.guava:failureaccess:1.0.1
|    +--- com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava
|    +--- com.google.code.findbugs:jsr305:3.0.2
|    +--- org.checkerframework:checker-qual:3.8.0
|    +--- com.google.errorprone:error_prone_annotations:2.5.1
|    \--- com.google.j2objc:j2objc-annotations:1.3
\--- org.junit.jupiter:junit-jupiter:5.7.2
     +--- org.junit:junit-bom:5.7.2
     |    +--- org.junit.jupiter:junit-jupiter:5.7.2 (c)
     |    +--- org.junit.jupiter:junit-jupiter-api:5.7.2 (c)
     |    +--- org.junit.jupiter:junit-jupiter-params:5.7.2 (c)
     |    \--- org.junit.platform:junit-platform-commons:1.7.2 (c)
     +--- org.junit.jupiter:junit-jupiter-api:5.7.2
     |    +--- org.junit:junit-bom:5.7.2 (*)
     |    +--- org.apiguardian:apiguardian-api:1.1.0
     |    +--- org.opentest4j:opentest4j:1.2.0
     |    \--- org.junit.platform:junit-platform-commons:1.7.2
     |         +--- org.junit:junit-bom:5.7.2 (*)
     |         \--- org.apiguardian:apiguardian-api:1.1.0
     \--- org.junit.jupiter:junit-jupiter-params:5.7.2
          +--- org.junit:junit-bom:5.7.2 (*)
          +--- org.apiguardian:apiguardian-api:1.1.0
          \--- org.junit.jupiter:junit-jupiter-api:5.7.2 (*)

testCompileOnly - Compile only dependencies for source set 'test'. (n)
No dependencies

testImplementation - Implementation only dependencies for source set 'test'. (n)
\--- org.junit.jupiter:junit-jupiter:5.7.2 (n)

testRuntimeClasspath - Runtime classpath of source set 'test'.
+--- com.google.guava:guava:30.1.1-jre
|    +--- com.google.guava:failureaccess:1.0.1
|    +--- com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava
|    +--- com.google.code.findbugs:jsr305:3.0.2
|    +--- org.checkerframework:checker-qual:3.8.0
|    +--- com.google.errorprone:error_prone_annotations:2.5.1
|    \--- com.google.j2objc:j2objc-annotations:1.3
\--- org.junit.jupiter:junit-jupiter:5.7.2
     +--- org.junit:junit-bom:5.7.2
     |    +--- org.junit.jupiter:junit-jupiter:5.7.2 (c)
     |    +--- org.junit.jupiter:junit-jupiter-api:5.7.2 (c)
     |    +--- org.junit.jupiter:junit-jupiter-engine:5.7.2 (c)
     |    +--- org.junit.jupiter:junit-jupiter-params:5.7.2 (c)
     |    +--- org.junit.platform:junit-platform-commons:1.7.2 (c)
     |    \--- org.junit.platform:junit-platform-engine:1.7.2 (c)
     +--- org.junit.jupiter:junit-jupiter-api:5.7.2
     |    +--- org.junit:junit-bom:5.7.2 (*)
     |    +--- org.apiguardian:apiguardian-api:1.1.0
     |    +--- org.opentest4j:opentest4j:1.2.0
     |    \--- org.junit.platform:junit-platform-commons:1.7.2
     |         +--- org.junit:junit-bom:5.7.2 (*)
     |         \--- org.apiguardian:apiguardian-api:1.1.0
     +--- org.junit.jupiter:junit-jupiter-params:5.7.2
     |    +--- org.junit:junit-bom:5.7.2 (*)
     |    +--- org.apiguardian:apiguardian-api:1.1.0
     |    \--- org.junit.jupiter:junit-jupiter-api:5.7.2 (*)
     \--- org.junit.jupiter:junit-jupiter-engine:5.7.2
          +--- org.junit:junit-bom:5.7.2 (*)
          +--- org.apiguardian:apiguardian-api:1.1.0
          +--- org.junit.platform:junit-platform-engine:1.7.2
          |    +--- org.junit:junit-bom:5.7.2 (*)
          |    +--- org.apiguardian:apiguardian-api:1.1.0
          |    +--- org.opentest4j:opentest4j:1.2.0
          |    \--- org.junit.platform:junit-platform-commons:1.7.2 (*)
          \--- org.junit.jupiter:junit-jupiter-api:5.7.2 (*)

testRuntimeOnly - Runtime only dependencies for source set 'test'. (n)
No dependencies

(c) - dependency constraint
(\*) - dependencies omitted (listed previously)

(n) - Not resolved (configuration is not meant to be resolved)

A web-based, searchable dependency report is available by adding the --scan option.

BUILD SUCCESSFUL in 576ms
1 actionable task: 1 executed
```

# 参考になりそうな資料
- https://qiita.com/opengl-8080/items/4c1aa85b4737bd362d9e
