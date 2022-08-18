# MySQl

- [Installation](https://dev.mysql.com/downloads/mysql/)

**_Use Default User-root_**

```
$ sudo mysql -u root -p

```

**_Use New User_**

```
$ sudo mysql -u root -p

mysql> CREATE USER 'Shiru99'@'localhost' IDENTIFIED BY '1234';

```

**_Create New Database_**

```
mysql> CREATE DATABASE appointmentsystem;  (dbname - all letters small)

mysql> use appointmentsystem

mysql> grant all on appointmentsystem.* to 'Shiru99'@'localhost';

$ mysql -u Shiru99 -h localhost -p appointmentsystem
```

---

**_Check Status_**

```
mysql> status
```

**_Create New Table_**

```
mysql> CREATE TABLE `Users` (
         `mobile_num` varchar(255) PRIMARY KEY NOT NULL,
         `password` varchar(255) NOT NULL
         );

mysql> show tables;
```

**_Insert New Table-entries_**

```
mysql> INSERT INTO `Users` (`mobile_num`, `password`)
         VALUES
            ( '1234567890', '1234'),
            ( '1234567891', '2345')
         ;

mysql> select * from Users;

```
