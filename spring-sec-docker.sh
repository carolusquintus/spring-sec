#!/bin/sh

docker run -p 3316:3306 --name spring-sec \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=eazybank \
-e MYSQL_USER=admin \
-e MYSQL_PASSWORD=admin \
-d mysql/mysql-server:8.0.22
