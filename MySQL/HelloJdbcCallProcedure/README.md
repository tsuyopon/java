# 概要

sakilaデータベースに存在する下記のfilm_in_stockというプロシージャを利用している
- https://dev.mysql.com/doc/sakila/en/sakila-structure-procedures-film_in_stock.html
```
mysql> use sakila
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql> CALL film_in_stock(1,1,@count);
+--------------+
| inventory_id |
+--------------+
|            1 |
|            2 |
|            3 |
|            4 |
+--------------+
4 rows in set (0.19 sec)

Query OK, 1 row affected (0.19 sec)

mysql> SELECT @count;
+--------+
| @count |
+--------+
|      4 |
+--------+
1 row in set (0.00 sec)
```

# 詳細
# mainを実行する
```
$ gradlew MySQL:HelloJdbcCallProcedure:run
```

