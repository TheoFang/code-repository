1、初识MySQL

JavaEE：企业级Java开发-Web

- 前端（页面：展示，数据！）

- 后台（连接点：连接数据库JDBC，连接前端：控制视图跳转和给前端传递数据(servlet,springMVC)）

- 数据库：存数据

> 只会写代码，学好数据库，基本混饭吃
>
> 学习操作系统，数据结构与算法！当一个不错的程序员！
>
> 离散数学，数字电路，体系结构，编译原理，加上实战经验，高级程序员！

### 1.1、为什么学习数据库

1. 岗位需求
2. 现在的大数据时代，数据是可以变现的，得数据者得天下
3. 被迫需求：存数据
4. **数据库是所有软件体系中最核心的存在**  DBA

### 1.2、什么是数据库

数据库（DB，Database）

概念：数据仓库，安装在操作系统之上的**软件**。可以存储大量数据（500万）

作用：存储数据，管理数据

### 1.3 数据库分类

关系型数据库：行、列

- MySQL，Oracle，SQL Server，DB2，SQLlite
- 通过表和表之间，行和列之间的关系进行数据的存储

非关系型数据库：NoSQL（Not Only SQL)

- Redis，MongoDB
- 对象存储，通过对象自身的属性来绝对。

**DBMS（数据库管理系统）**

- 数据库的管理软件，科学有效地管理，维护和获取数据。
- MySQL，数据库管理系统

### 1.4、MySQL简介

MySQL是一个关系型数据库管理系统

前世：瑞典MySQL AB公司

今生：属于Oracle旗下产品

MySQL是最好的RDBMS应用软件之一。

开源的数据库软件。

体积小、速度快、总体拥有成本低

适用于中小型或者大型网站，集群！

官网：https://www.mysql.com

5.7版本稳定，最新8.0版本



安装建议：

1. 尽量不要使用exe安装
2. 尽可能使用压缩包安装

### 1.5、安装MySQL

> sc delete mysql	清空服务

### 1.6、安装SQLyog

### 1.7、连接数据库

命令行连接

```sql
--连接数据库
mysql -u root -p
mysql -uroot -p
mysql -uroot -p123456
mysql -u root -p123456
--修改用户密码
update mysql.user set authentication_string=password('123456') where user='root' and Host='localhost';
--刷新权限
flush privileges

***********************************************

--mysql所有语句都是用分号(;)结尾
--查看所有的数据库
show databases;
--切换数据库：use 数据库名
use school;
--查看数据库中所有的表
show tables;
--显示数据库中所有表的信息
describe student;

--创建一个数据库
create database westos;

--退出连接
exit;
--单行注释
--  (sql本身的注释)
--多行注释
/*
	hello
	hi
*/
```

**数据库 xxx 语言**   CRUD增删改查	CV程序猿	API程序猿	CRUD程序猿（业务）

DDL：定义

DML：操作管理

DQL：查询

DCL：控制

## 2、操作数据库

操作数据库 > 操作数据库中的表 > 操作数据库中表的数据

**mysql关键字不区分大小写**

### 2.1、操作数据库（了解）

1. 创建数据库

   ```
   CREATE DATABASE [IF NOT EXISTS] westos
   ```

2. 删除数据库

   ```
   DROP DATABASE [IF EXISTS] westos
   ```

3. 使用数据库

   ```sql
   如果表明或者字段名是一个特殊字符，就需要带倒引号
   USE school
   SELECT `user` FROM stuent
   ```

4. 查看数据库

   ```sql
   SHOW DATABASES	--查看所有的数据库
   ```

对比：SQLyog的可视化操作

学习思路：

- 对照SQLyog可视化历史记录查看sql
- 固定的语法或关键字必须记住

### 2.2、数据库的数据（列）类型

> 数值

- tinyint			   十分小的数据                1个字节
- smallint             较小的数据                    2个字节
- mediumint       中等大小的数据             3个字节
- **int                      标准的整数                     4个字节（常用）**
- bigint                 较大的数据                    8个字节
- float                   浮点数                            4个字节
- double               浮点数                            8个字节
- decimal             字符串形式的浮点数 ，金融计算的时候，一般使用decimal     

> 字符串

- char                  固定大小的字符串          0~255
- **varchar           可变字符串                      0~65535**
- tinytext            微型文本                          2^8 - 1
- **text                  文本串                             2^16 - 1** 

> 时间日期

- date                  YYYY-MM-DD      日期格式
- time                  HH:mm:ss           时间格式
- **datetime          YYYY-MM-DD HH:mm:ss**
- **timestamp       时间戳 ， 1970.1.1到现在的毫秒数**
- year                  年份表示

> null

- 没有值，未知
- **注意，不要使用NULL进行运算，结果为NULL**

### 2.3、数据库的字段属性（重点）

unsigned：

- 无符号整数
- 不能声明为负数

zerofill：

- 0填充的
- 不足的位数使用0来填充， int(3),  5->005

自增：

- 通常理解为自增，自动在上一条记录的基础上加1（默认）
- 通常用来设计唯一的主键，必须是整数类型
- 可以自定义设置主键自增的起始值和步长

非空  not null

- 假设设置为not null ，如果不给它赋值，就会报错
- 如果不填写值，默认就是null 

默认：

- 设置默认值！
- sex，默认值为男，如果不指定该列的值，则会有默认值。

**拓展**：每一个表，都必须存在以下五个字段：（未来做项目用，表示一个记录存在的意义）

```
id				主键
`version`		乐观锁
is_delete		伪删除
gmt_create		创建时间
gmt_update		修改时间
```

### 2.4、创建数据库表

```sql
-- 注意：使用英文括号，表的名称和字段尽量使用倒引号括起来
-- AUTO_INCREMENT 自增
-- 字符串使用单引号括起来
-- 所有语句后加逗号，最后一个字段不用加
-- PRIMARY KEY 主键，一般一个表只有一个唯一的主键
CREATE TABLE IF NOT EXISTS `student`(
	`id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
	`name` VARBINARY(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
	`pwd` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
	`sex` VARCHAR(2) NOT NULL DEFAULT '男' COMMENT '性别',
	`birthday` DATETIME DEFAULT NULL COMMENT '出生日期',
	`address` VARCHAR(100) DEFAULT NULL COMMENT '家庭住址',
	`email` VARCHAR(20) DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8
```

格式

```sql
CREATE TABLE [IF EXIST] `表明` (
	`字段名` 列类型 [属性][索引][注释],
	`字段名` 列类型 [属性][索引][注释],
	`字段名` 列类型 [属性][索引][注释],
	...
	`字段名` 列类型 [属性][索引][注释]
)[表类型][表字符集设置][注释]
```

**常用命令**

```sql
SHOW CREATE DATABASE school -- 查看创建数据库的语句
-- CREATE DATABASE `school` /*!40100 DEFAULT CHARACTER SET utf8 */
SHOW CREATE TABLE student -- 查看创建表的语句
-- CREATE DATABASE `school` /*!40100 DEFAULT CHARACTER SET utf8 */
DESC student  -- 显示表的结构
```

### 2.5、数据库表的类型

```sql
-- 关于数据库引擎
 /*
 INNODB  默认使用
 MYISAM  早些年使用的
*/
```

|              | MYISAM | INNODB                 |
| ------------ | ------ | ---------------------- |
| 事务支持     | 不支持 | 支持                   |
| 数据行锁定   | 不支持 | 支持                   |
| 外键约束     | 不支持 | 支持                   |
| 全文索引     | 支持   | 不支持                 |
| 表空间的大小 | 较小   | 较大（约为MYISAM两倍） |

常规使用的操作：

- MYISAM：节约空间，速度较快
- INNODB：安全性高，支持事务的处理，多表多用户操作

> 在物理空间存在的位置

所有的数据库文件都存在data目录下，一个文件夹就对应一个数据库。

本质还是文件的存储

MySQL数据库引擎在屋里文件上区别

- INNODB在数据库表中只有一个 *.frm文件，以及上级目录下的ibadata1文件
- MYISAM对应文件
  - *.frm   表结构的定义文件
  - *.MYD 数据文件（data)
  - *.MYI   索
  - 引文件（index)

> 设置数据库表的字符集编码

```sql
CHARSET=utf8
```

不设置的话，会是MySQL默认的字符集编码（不支持中文）

MySQL的默认编码是Latin1，不支持中文

在my.ini中配置默认的编码

```sql
character-set-server=utf8
```

### 2.6、修改删除表

> 修改

```sql
-- 修改表名：	ALTER TABLE 旧表名 RENAME AS 新表名
ALTER TABLE teacher RENAME AS teacher1
-- 增加表的字段 	ALTER TABLE 表名 ADD 字段名 列属性
ALTER TABLE teacher1 ADD age INT(11)
-- 修改表的字段（重命名，修改约束）
-- ALTER TABLE 表名 MODIFY 字段名 列属性[]
ALTER TABLE teacher1 MODIFY age VARCHAR(11)  -- 修改约束
-- ALTER TABLE 表名 CHANGE 旧名字 新名字 列属性[]
ALTER TABLE teacher1 CHANGE age age1 INT(3)  -- 字段重命名（可以同时修改约束）
-- 删除表的字段
ALTER TABLE teacher1 DROP age1
```

> 删除

```sql
-- 删除表
DROP TABLE IF EXISTS teacher1
```

注：所有的创建和删除操作尽量加上判断，以免报错

注意点：

- 所有字段名使用倒引号包裹
- 注释 -- /**/
- sql关键字大小写不敏感，建议小写
- 所有的符号都用英文

## 3、MySQL数据管理

### 3.1、外键（了解）

> 方式一、在创建表的时候，增加约束（麻烦，比较复杂）

```sql
CREATE TABLE `grade` (
  `gradeid` INT(10) NOT NULL AUTO_INCREMENT COMMENT '年级id',
  `gradename` VARCHAR(50) NOT NULL COMMENT '年级名称',
  PRIMARY KEY (`gradeid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8


-- 学生表的 gradeid字段要引用年级表的gradeid
-- 定义外键key
-- 给这个外键添加约束（执行引用）
CREATE TABLE IF NOT EXISTS `student` (
	`id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
	`name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
	`pwd` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
	`sex` VARCHAR(2) NOT NULL DEFAULT '女' COMMENT '性别',
	`birthday` DATETIME DEFAULT NULL COMMENT '出生日期',
	`address` VARCHAR(100) DEFAULT NULL COMMENT '家庭住址',
	`email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
	`gradeid` INT(10) NOT NULL COMMENT '学生年级',
	PRIMARY KEY (`id`),
	KEY `FK_gradeid` (`gradeid`),
	CONSTRAINT `FK_gradeid` FOREIGN KEY (`gradeid`) REFERENCES `grade`(`gradeid`)
)ENGINE=INNODB DEFAULT CHARSET=utf8
```

删除有外键关系的表的时候，必须要先删除引用别人的表（从表），再删除被引用的表（主表）

> 方式二：创建表成功后，添加外键约束

```sql 

SHOW CREATE TABLE `grade`
CREATE TABLE `grade` (
  `gradeid` INT(10) NOT NULL AUTO_INCREMENT COMMENT '年级id',
  `gradename` VARCHAR(50) NOT NULL COMMENT '年级名称',
  PRIMARY KEY (`gradeid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

CREATE TABLE IF NOT EXISTS `student` (
	`id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
	`name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
	`pwd` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
	`sex` VARCHAR(2) NOT NULL DEFAULT '女' COMMENT '性别',
	`birthday` DATETIME DEFAULT NULL COMMENT '出生日期',
	`address` VARCHAR(100) DEFAULT NULL COMMENT '家庭住址',
	`email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
	`gradeid` INT(10) NOT NULL COMMENT '学生年级',
	PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8
 -- 创建表的时候没有外键关系
 
 ALTER TABLE `student` 
 ADD CONSTRAINT `FK_gradeid` FOREIGN KEY(`gradeid`) REFERENCES `grade`(`gradeid`);
 -- ALTER TABLE 表 ADD CONSTRAINT 约束名 FOREIGN KEY（作为外键的列） REFERENCES 哪个表（哪个字段）
```

以上操作都是物理外键，数据库级别的外键，不建议使用。（避免数据库过多造成困扰，了解即可）

**最佳实践**

- 数据库就是单纯的表，只用来存数据，只有行（数据）和列（字段）
- 想使用多张表的数据，想使用外键的话（使用程序实现）

### 3.2、DML语言（背下来）

**数据库意义**：数据存储，数据管理

DML语言：数据操作语言

- insert
- update
- delete

### 3.3、添加

```sql
-- 插入语句（添加）
-- insert into 表名 ([字段名1],[字段2],[字段3])values('值1'),('值2',('值3')
INSERT INTO `grade` (`gradename`) VALUES ('大四')

-- 由于主键自增，我们可以省略（如果不写表的字段，他就会一一匹配）
-- 一般写插入语句，我们一定要数据和字段一一对应！
INSERT INTO `student`
VALUES ('6','李四','aaaaaa','男','1999-01-01','xian','theofang')

INSERT INTO `grade` (`gradename`) VALUES ('大二'),('大三')

INSERT INTO `student`(`name`) VALUES ('张三')

INSERT INTO `student`(`name`,`pwd`,`sex`) VALUES ('张三','aaaaaa','男')

INSERT INTO `student`(`name`,`pwd`,`sex`) 
VALUES ('李四','aaaaaa','男'),('王五','bbbbbb','男')
```

语法：`insert into 表名 ([字段名1],[字段2],[字段3])values('值1'),('值2',('值3')`

注意事项：

1. 字段和字段之间用英文逗号隔开
2. 字段是可以省略的，但是后面的值必须要一一对应
3. 可以同时插入多条数据，VALUES后面的值，需要使用逗号隔开`VALUES (),(),...`

### 3.4、修改

> update	修改谁（条件） set 原来的值 = 新值

```sql
-- 修改学员名字
-- 带条件
UPDATE `student` SET `name` = 'thefan' WHERE `id` = '2';
UPDATE `student` SET `name` = 'thefan' WHERE id = 2;
 
-- 不指定条件的情况下，会改动所有表！
UPDATE `student` SET `name`='theofang';
-- 修改多个属性，逗号隔开
UPDATE `student` SET `name`='theo',`email`='theo@qq.com' WHERE id=1;
```

语法：`update 表名 set colnum_name = value,[colnum_name = value,...]	where	条件`

条件：where 子句 运算符 id等于某个值，大于某个值，在某个区间内修改...

操作符会返回布尔值，为真时执行操作

| 操作符              | 含义                     | 范围        | 结果  |
| ------------------- | ------------------------ | ----------- | ----- |
| =                   | 等于                     | 5=6         | flase |
| <>或!=              | 不等于                   | 5<>6        | true  |
| >                   |                          |             |       |
| <                   |                          |             |       |
| <=                  |                          |             |       |
| >=                  |                          |             |       |
| BETWEEN ... and ... | 闭合区间[]，在某个范围内 | [2,5]       |       |
| AND                 | 我和你 &&                | 5>1 and 1>2 | false |
| OR                  | 我或你 \|\|              | 5>1 or 1>2  | true  |

```sql
-- 通过多个条件定位数据
UPDATE `student` SET `name`='fang' WHERE `name`='theofang' AND `sex`='女'
```

注意：

- colnum_name是数据库的列，尽量带上倒引号
- 条件，筛选的条件，如果没有指定，则会修改所有的列
- value是一个具体的值，也可以是一个变量
- 多个设置的属性之间，使用英文逗号隔开

```sql
UPDATE `student` SET `birthday` = CURRENT_TIME WHERE `name`='theofang' AND sex='女'
```

### 3.5、删除

> delete

语法：`delete from 表名 [where 条件]

```sql 
-- 删除数据（避免这样写，会全部删除）
DELETE FROM `student`
-- 删除指定数据
DELETE FROM `student` WHERE id=1;
```

> TRUNCATE 

作用：完全清空一个数据库表，表的结果和索引约束不会变！

```sql
-- 清空student表
TRUNCATE `student`
```

> delete 和 TRUNCATE 区别

- 相同点：都能删除数据，都不会删除表结构
- 不同点：
  - TRUNCATE重新设置自增列，计数器会归零
  - TRUNCATE不会影响事务

测试：

```sql
-- 测试delete和TRUNCATE
CREATE TABLE `test`(
	`id` INT(4) NOT NULL AUTO_INCREMENT,
	`coll` VARCHAR(20) NOT NULL,
	PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO `test`(`coll`) VALUES('1'),('2'),('3')

DELETE FROM `test` -- 不会影响自增

TRUNCATE `test` -- 自增会归零
```

了解：`delete`删除的问题，重启数据库，现象：

- INNODB 自增列会从1开始（存在内存中，断电即失）
- MYISAM继续从上一个自增量开始（存在文档中，不会丢失）

## 4、DQL查询数据（最重点）

### 4.1、DQL

（Data Query Language：数据查询语言）

- 所有的查询操作都用它
- 简单的查询和复杂的查询都能做
- **数据库中最核心的语言，最重要的语句**
- 使用频率最高的语句

### 4.2、指定查询字段

```sql
-- 查询全部的学生
SELECT * FROM `student`

SELECT * FROM `result`

-- 查询指定字段
SELECT `studentno`,`studentname` FROM `student`

-- 别名，给结果起一个名字 AS	
-- 可以给字段起别名，也可以给表起别名
SELECT `studentno` AS 学号,`studentname` AS 学生姓名 FROM `student` AS s

-- 函数 concat (a,b)
SELECT CONCAT('姓名:',`studentname`) AS 新名字 FROM `student`
```

语法：`SELECT  字段，...  FROM  表`

> 有的时候，列名字不是那么的见名知意，我们起别名    AS    字段名    AS    别名    AS    别名

> 去重  distinct

作用：去除SELECT查询出来的结果中重复的数据，重复的数据只显示一条

```sql
-- 查询一下哪些同学参加了考试（有成绩）
SELECT * FROM result  -- 查询全部的考试成绩
SELECT `studentno` FROM result   -- 查询有哪些同学参加了考试
SELECT DISTINCT `studentno` FROM result   -- 发现重复数据，去重
```

> 数据库的列

```sql
-- 查询系统版本（函数）
SELECT VERSION()

SELECT 100-3 AS 计算结果 -- 用来计算 （表达式）
SELECT @@auto_increment_increment  -- 查询自增的步长（变量）

-- 学员考试成绩+1分查看
SELECT `studentno`,`studentresult`+1 AS 提分后 FROM result
```

**数据库中的表达式：文本值，列，NULL，函数，计算表达式，系统变量**

select`表达式` from 表

Select完整的语法：

```sql
SELECT [ALL | DISTINCT]
{* | table.* | [table.field1[as alias1][,table.field2[as alias2]][,...]]} 
FROM table_name [as table_alias]
	[left | right | inner join table_name2]		-- 联合查询
	[WHERE ...]								-- 指定结果需满足的条件
	[GROUP BY ...]							-- 指定结果按照哪几个字段来分组
	[HAVING]								-- 过滤分组的记录必须满足的次要条件
	[ORDER BY ...]							-- 指定查询记录按一个或多个条件排序
	[LIMIT {[offset,]row_count | row_countOFFSET offset}]; -- 指定查询的记录从哪条到哪条
```

**注意：[]括号代表可选的，{}括号代表必选的**

### 4.3、where条件子句

作用：检索数据中符合条件的值

> 逻辑运算符

搜索条件由一个或多个表达式组成，结果为布尔值

| 运算符     | 语法               | 描述                           |
| :--------- | ------------------ | ------------------------------ |
| and    &&  | a and b    a&&b    | 逻辑与，两个都为真，结果为真   |
| or    \|\| | a or b    a \|\| b | 逻辑或，其中一个为真，结果为真 |
| Not    !   | not a    ! a       | 逻辑非，真亦假，假亦真         |

**尽量使用英文字母**

```sql
-- =======================  where  ============================
 SELECT `studentno`,`studentresult` FROM result;

-- 查询考试成绩在95~100分之间的

 SELECT `studentno`,`studentresult` FROM result
 WHERE `studentresult`>=95 AND `studentresult`<=100;

--	and	&&
 SELECT `studentno`,`studentresult` FROM result
 WHERE `studentresult`>=95 && `studentresult`<=100;

-- 模糊查询（区间）
 SELECT `studentno`,`studentresult` FROM result
 WHERE `studentresult` BETWEEN 95 AND 100;

-- 除了1000号学生以外的同学的成绩

SELECT `studentno`,`studentresult` FROM result
 WHERE `studentno` != 1000;

--	!=	not
SELECT `studentno`,`studentresult` FROM result
 WHERE NOT `studentno` = 1000;
```

> 模糊查询：比较运算符

| 运算符      | 语法               | 描述                                     |
| ----------- | ------------------ | ---------------------------------------- |
| IS NULL     | a IS NULL          | 如果操作符为NULL，则结果为真             |
| IS NOT NULL | a IS NOT NULL      | 如果操作符为NULL，则结果为假             |
| BETWEEN     | a BETWEEN b AND c  | 若a在b和c之间，则结果为真                |
| **Like**    | a Like b           | SQL匹配，若a能匹配到b，结果为真          |
| **In**      | a In (a1,a2,a3...) | a在a1或a2或...其中的某一个值中，结果为真 |

```sql
-- ****************模糊查询******************
-- 查询姓刘的同学
-- Like结合  %(代表0到任意个字符）   _（代表一个字符）
SELECT `studentno`,`studentname` FROM `student`
WHERE `studentname` LIKE '张%'

-- 查询姓刘的同学，名字后面只有一个字的
SELECT `studentno`,`studentname` FROM `student`
WHERE `studentname` LIKE '张_'

-- 查询姓刘的同学，名字后面只有两个字的
SELECT `studentno`,`studentname` FROM `student`
WHERE `studentname` LIKE '张__'

-- 查询名字中有嘉字的同学   %嘉%
SELECT `studentno`,`studentname` FROM `student`
WHERE `studentname` LIKE '%嘉%'


-- ******************* in（具体的一个或者多个值）*****************
-- 查询 1001,1002,1003号学员
SELECT `studentno`,`studentname` FROM `student`
WHERE `studentno` IN (1001,1002,1003);

SELECT `studentno`,`studentname` FROM `student`
WHERE `studentno` BETWEEN 1001 AND 1003;

-- 查询在 Earth 的学生
SELECT `studentno`,`studentname` FROM `student`
WHERE `address` IN ('Earth');

-- ******************null  not null ******************
-- 查询地址为空的学生（null    ' ')
SELECT `studentno`,`studentname` FROM `student`
WHERE `address`='' OR `address` IS NULL


-- 查询有出生日期的同学
SELECT `studentno`,`studentname` FROM `student`
WHERE `borndate` IS NOT NULL

-- 查询没有出生日期的同学
SELECT `studentno`,`studentname` FROM `student`
WHERE `borndate` IS NULL
```

### 4.4、联表查询

> JOIN对比

- Join（连接的表） on（判断的条件） 连接查询
- where  等值查询

```sql
-- *******************联表查询******************
-- 查询参加了考试的同学（学号，姓名，科目编号，分数）
SELECT * FROM `student`
SELECT * FROM `result`

/*思路
1、分析需求，分析查询的字段来自哪些表
2、确定使用哪种连接查询（7种），确定交叉点（两个表中哪个数据是相同的）
判断的条件：学生表中的 `studentno` = 成绩表中的`studentno`

*/
SELECT s.`studentno`,`studentname`,`subjectno`,`studentresult`
FROM `student` AS s
INNER JOIN `result` AS r
WHERE s.`studentno`=r.`studentno`

-- Right Join
SELECT s.`studentno`,`studentname`,`subjectno`,`studentresult`
FROM `student`  s -- AS 可省略
RIGHT JOIN `result`  r
ON s.`studentno`=r.`studentno`

-- Left Join
SELECT s.`studentno`,`studentname`,`subjectno`,`studentresult`
FROM `student`  s -- AS 可省略
LEFT JOIN `result`  r
ON s.`studentno`=r.`studentno`
```

| 操作       | 描述                                       |
| ---------- | ------------------------------------------ |
| Inner Join | 如果表中至少有一个匹配，就返回匹配的值     |
| Left Join  | 会从左表中返回所有的值，即使右表中没有匹配 |
| Right Join | 会从右表中返回所有的值，即使左表中没有匹配 |

```sql
-- *******************联表查询******************

-- 查询缺考的同学
SELECT s.`studentno`,`studentname`,`subjectno`,`studentresult`
FROM `student`  s -- AS 可省略
LEFT JOIN `result`  r
ON s.`studentno`=r.`studentno`
WHERE `studentresult` IS NULL

-- 思考：查询参加了考试的同学信息：学号，学生姓名，科目名称，分数

SELECT s.`studentno`,`studentname`,`subjectname`,`studentresult`
FROM `student` s
RIGHT JOIN `result` r
ON r.`studentno`=s.`studentno`
INNER JOIN `subject` sub
ON r.`subjectno`=sub.`subjectno`

-- 查询哪些数据 select ...
-- 从哪些表查  from  表  xxx join  连接的表 on 交叉条件
-- 假设存在多张表查询，先查询两张表再慢慢增加
```

> 自连接（了解）

自己的表和自己的表连接，**核心：一张表拆为两张一样的表**

父类

| categoryid | categoryname |
| ---------- | ------------ |
| 2          | 信息技术     |
| 3          | 软件开发     |
| 5          | 美术设计     |
|            |              |

子类

| pid  | categoryid | categoryname |
| ---- | ---------- | ------------ |
| 3    | 4          | 数据库       |
| 2    | 8          | 办公信息     |
| 3    | 6          | web开发      |
| 5    | 7          | ps技术       |

操作：查询父类对应的子类关系

| 父类     | 子类     |
| -------- | -------- |
| 信息技术 | 办公信息 |
| 软件开发 | 数据库   |
| 软件开发 | web开发  |
| 美术设计 | ps技术   |

```sql
-- 创建category表
CREATE TABLE `school`.`category`( 
`categoryid` INT(3) NOT NULL COMMENT 'id', 
`pid` INT(3) NOT NULL COMMENT '父id 没有父则为1', 
`categoryname` VARCHAR(10) NOT NULL COMMENT '种类名字', 
PRIMARY KEY (`categoryid`) 

) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;
-- 插入数据
INSERT INTO `school`.`category` (`categoryid`, `pid`, `categoryname`) VALUES ('2', '1', '信息技术');
INSERT INTO `school`.`CATEGOrY` (`categoryid`, `pid`, `categoryname`) VALUES ('3', '1', '软件开发');
INSERT INTO `school`.`category` (`categoryid`, `PId`, `categoryname`) VALUES ('5', '1', '美术设计');
INSERT INTO `School`.`category` (`categoryid`, `pid`, `categorynamE`) VALUES ('4', '3', '数据库');
INSERT INTO `school`.`category` (`CATEgoryid`, `pid`, `categoryname`) VALUES ('8', '2', '办公信息');
INSERT INTO `school`.`category` (`categoryid`, `pid`, `CAtegoryname`) VALUES ('6', '3', 'web开发');
INSERT INTO `SCHool`.`category` (`categoryid`, `pid`, `categoryname`) VALUES ('7', '5', 'ps技术');
```

```sql
-- 查询父子信息：把一张表看为两个一模一样的表
SELECT a.`categoryname` AS '父栏目',b.`categoryname` AS '子栏目'
FROM `category` AS a,`category` AS b
WHERE a.`categoryid`=b.`pid`

-- 查询学员所属的年级（学号，学生的姓名，年级名称）
SELECT `studentno`,`studentname`,`gradename`
FROM `student` s
INNER JOIN `grade` g
ON s.`gradeid`=g.`gradeid`

-- 查询科目所属的年级（科目名称，年级名称）
SELECT `subjectname`,`gradename`
FROM `subject` sub
INNER JOIN `grade` g
ON sub.`gradeid`=g.`gradeid`

-- 查询参加了 高等数学-1 考试的同学信息：学号，学生姓名，科目名称，分数
SELECT s.`studentno`,`studentname`,`subjectname`,`studentresult`
FROM `student` s
INNER JOIN `result`r
ON s.`studentno`=r.`studentno`
INNER JOIN `subject` sub
ON r.`subjectno`=sub.`subjectno`
WHERE `subjectname`='高等数学-1'
```

### 4.5、分页和排序

> 排序

```sql
-- 排序：升序  ASC，降序  DESC
-- 语法：ORDER BY 通过哪个字段排序，怎么排
-- 查询的结果根据 成绩降序 排序
SELECT s.`studentno`,`studentname`,`subjectname`,`studentresult`
FROM `student` s
INNER JOIN `result`r
ON s.`studentno`=r.`studentno`
INNER JOIN `subject` sub
ON r.`subjectno`=sub.`subjectno`
WHERE `subjectname`='高等数学-1'
ORDER BY `studentresult` ASC
```

> 分页

```sql
-- 100万条数据
-- 为什么分页
--   缓解数据库压力，给人体验更好
-- 分页，每页只显示2条数据
-- 语法：limit 起始值，页面的大小
SELECT s.`studentno`,`studentname`,`subjectname`,`studentresult`
FROM `student` s
INNER JOIN `result`r
ON s.`studentno`=r.`studentno`
INNER JOIN `subject` sub
ON r.`subjectno`=sub.`subjectno`
WHERE `subjectname`='高等数学-1'
ORDER BY `studentresult` ASC
LIMIT 4,2
-- 第一页 limit 0,2
-- 第二页 limit 2,2
-- 第三页 limit 4.2
-- 第n页  limit (n-1)*pageSize,pageSize
-- [ pageSize：页面大小 ]
-- [ (n-1)*pageSize：起始值 ]
-- [ n：当前页 ]
-- [ 数据总数/页面大小 = 总页数 ]
```

语法：`limit  起始下标，pageSize`





## 10、JDBC（重点）

### 10.1数据库驱动



### 10.2、JDBC



### 10.3、第一个JDBC程序

创建测试表

```sql
CREATE DATABASE jdbcStudy CHARACTER SET utf8 COLLATE utf8_general_ci;

USE jdbcStudy;

CREATE TABLE `users`(
id INT PRIMARY KEY,
NAME VARCHAR(40),
PASSWORD VARCHAR(40),
email VARCHAR(60),
birthday DATE
);

INSERT INTO `users`(id,NAME,PASSWORD,email,birthday)
VALUES(1,'zhansan','123456','zs@sina.com','1980-12-04'),
(2,'lisi','123456','lisi@sina.com','1981-12-04'),
(3,'wangwu','123456','wangwu@sina.com','1979-12-04')
```

1. 创建一个普通项目
2. 导入数据库驱动
3. 编写测试代码

```java
package com.theo.lesson01;

import java.sql.*;

//我的第一个JDBC程序
public class JdbcFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");//固定写法，加载驱动
        //2.连接信息（用户信息和URL）
        //userUnicode=true&characterEncoding=utf8&useSSL=true
        // 支持中文编码  设定中文字符集为utf8   使用安全连接
        String url = "jdbc:mysql://localhost:3306/jdbcStudy?userUnicode=true&characterEncoding=utf8&useSSL=false";
        String username = "root";
        String password = "theofang";
        //3.连接成功，返回数据库对象
        Connection connection = DriverManager.getConnection(url, username, password);
        //4.执行SQL的对象   Statement
        Statement statement = connection.createStatement();
        //5.用执行SQL的对象 执行 SQL，可能存在结果，查看返回结果
        String sql = "SELECT * FROM `users`";

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            System.out.println("id=" + resultSet.getObject("id"));
            System.out.println("name=" + resultSet.getObject("NAME"));
            System.out.println("pwd=" + resultSet.getObject("PASSWORD"));
            System.out.println("email" + resultSet.getObject("email"));
            System.out.println("birth" + resultSet.getObject("birthday"));
        }
        //6.释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
```

步骤总结：

1. 加载驱动
2. 连接数据库 DriverManager
3. 获取执行sql的对象 Statement
4. 编写SQL（根据业务，不同的SQL）
5. 执行SQL
6. 获得返回的结果集
7. 释放连接

> DriverManager

```java
//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
Class.forName("com.mysql.jdbc.Driver");//固定写法，加载驱动
Connection connection = DriverManager.getConnection(url, username, password);

// connection 代表数据库
//设置数据库自动提交
//事务提交
//事务回滚
connection.rollback();
connection.commit();
connection.setAutoCommit();
```



> URL

```java
String url = "jdbc:mysql://localhost:3306/jdbcStudy?userUnicode=true&characterEncoding=utf8&useSSL=false";

//mysql默认端口号3306
//协议://主机地址:端口号/数据库名?参数1&参数2&参数3

//oralce默认端口号1521
//jdbc:oracle:thin:@localhost:1521:sid
```



> Statement 执行SQL的对

```java
String sql = "SELECT * FROM `users`"; //编写SQL语句

statement.executeQuery();//查询操作返回ResultSet
statement.execute();    //可以执行任何SQL
statement.executeUpdate();//更新、插入、删除都用这个，返回一个受影响的行数
```



> ResultSet 查询的结果集：封装了所有的查询结果

获得指定的数据类型

```java
 resultSet.getObject(); //在不知道列类型的情况下使用
//如果知道列的类型就使用指定的类型
resultSet.getString();
resultSet.getInt();
resultSet.getFloat();
resultSet.getDate();
resultSet.getDouble();
...
```

遍历：指针

```java
resultSet.beforeFirst();//移动到最前面
resultSet.afterLast();//移动到最后面
resultSet.next();   //移动到下一个数据
resultSet.previous();//移动到前一行
resultSet.absolute(row);//移动到指定行
```

> 释放资源

```java
//6.释放连接（耗资源，用完关掉）
resultSet.close();
statement.close();
connection.close();
```

### 10.4、Satement对象

jdbc中的Satement对象用于向数据库发送SQL语句，想完成对数据库的增删改查，只需要通过这个对象向数据库发送增删改查语句即可。

Statement对象的executeUpdate方法，用于向数据库发送增、删、改的SQL语句，executeUpdate执行完成后，将会**返回一个整数**（即增删改语句导致了数据库几行数据发生了变化）。

Statement.executeQuery方法用于向数据库发送查询语句，executeQuery方法返回代表查询结果的ResultSet对象。

> CRUD操作-create

使用executeUpdate(String sql)方法完成数据添加操作，示例操作：

```java
Statement st = conn.createStatement();
String sql = "insert into user(...) values(...)";
int num = st.executeUpdate(sql);
if(num > 0){
    System.out.println("插入成功！！");
}
```

> CRUD操作-delete

使用executeUpdate(String sql)方法完成数据删除操作，示例操作：

```java
Statement st = conn.createStatement();
String sql = "delete from user where id = 1";
int num = st.executeUpdate(sql);
if(num > 0){
    System.out.println("删除成功！！");
}
```

> CRUD操作-update

使用executeUpdate(String sql)方法完成数据修改操作，示例操作：

```java
Statement st = conn.createStatement();
String sql = "update user set name='' where name=''";
int num = st.executeUpdate(sql);
if(num > 0){
    System.out.println("修改成功！！");
}
```

> CRUD操作-read

使用executeQuery(String sql)方法完成数据查询操作，示例操作：

```java
Statement st = conn.createStatement();
String sql = "select * from user where id=1";
ResultSet rs = st.executeQuery(sql);
while(rs.next()){
    //根据获取列的数据类型，分别调用rs的相应方法映射到java对象中
}
```



> 代码实现

1. 提取工具类

```java
package com.theo.lesson02.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static{
        try {
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(in);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            //1.驱动只用加载一次
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取连接
    public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(url, username, password);
    }

    //释放连接资源
    public static void release(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

```

2. 编写增删改的方法，`executeUpdate()`

```java
package com.theo.lesson02;

import com.theo.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsert {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {

            conn = JdbcUtils.getConnection();//获取数据库连接
            st = conn.createStatement();//获得SQL的执行对象
            String sql = "insert into `users`(`id`,`NAME`,`PASSWORD`,`email`,`birthday`) values(4,'theofang','123456','theofang@qq.com','2020-01-01')";

            int i = st.executeUpdate(sql);
            if (i > 0) {
                System.out.println("插入成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```

```java
package com.theo.lesson02;

import com.theo.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDelete {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {

            conn = JdbcUtils.getConnection();//获取数据库连接
            st = conn.createStatement();//获得SQL的执行对象
            String sql = "DELETE FROM `users` WHERE id=4";

            int i = st.executeUpdate(sql);
            if (i > 0) {
                System.out.println("删除成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```

```java
package com.theo.lesson02;

import com.theo.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUpdate {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {

            conn = JdbcUtils.getConnection();//获取数据库连接
            st = conn.createStatement();//获得SQL的执行对象
            String sql = " UPDATE `users` SET `NAME`='fang',`email`='123232@qq.com' WHERE id=1";

            int i = st.executeUpdate(sql);
            if (i > 0) {
                System.out.println("更新成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```

3. 查询 `executeQuery()`

```java
package com.theo.lesson02;

import com.theo.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestQuery {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            //Sql
            String sql = "select * from `users` where id = 1";
            rs = st.executeQuery(sql); //查询完毕会返回一个结果

            while (rs.next()) {
                System.out.println(rs.getString("NAME"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```

> SQL注入

SQL存在漏洞，会被攻击导致数据泄露（SQL会被拼接 or）

```java
package com.theo.lesson02;

import com.theo.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL注入 {
    public static void main(String[] args) {
        //正常登录
        //login("theo","123456");
        //SQL注入
        login("'or' 1=1","'or' 1=1");
    }

    //登录业务
    public static void login(String username, String password) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            //Sql
            //SELECT * FROM `users` WHERE `NAME` = 'theo' AND `PASSWORD` = '123456'
            //SELECT * FROM `users` WHERE `NAME` = ''or' 1=1' AND `PASSWORD` = ''or' 1=1'
            String sql = "select * from `users` where `NAME` = '" + username + "' AND `PASSWORD` = '"+ password +"'";
            rs = st.executeQuery(sql); //查询完毕会返回一个结果

            while (rs.next()) {
                System.out.println(rs.getString("NAME"));
                System.out.println(rs.getString("PASSWORD"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```

### 10.5、PreparedStatement对象

PreparedStatement可以防止SQL注入，并且效率更高

1、新增

```java
package com.theo.lesson03;

import com.theo.lesson02.utils.JdbcUtils;
import java.util.Date;
import java.sql.*;

public class TestInsert {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = JdbcUtils.getConnection();

            //区别
            //使用 ？占位符代替参数
            String sql = "insert into `users`(`id`,`NAME`,`PASSWORD`,`email`,`birthday`) values(?,?,?,?,?)";

            pst = conn.prepareStatement(sql);//预编译SQL，先写SQL，然后不执行

            //手动给参数赋值
            pst.setInt(1,4);
            pst.setString(2,"theofang");
            pst.setString(3,"123456");
            pst.setString(4, "theofang@qq.com");
            //注意：sql.Date       数据库使用
            //    util.Date       Java     new Date().getTime() 获得时间戳
            pst.setDate(5, new java.sql.Date(new Date().getTime()));

            //执行
            int i = pst.executeUpdate();
            if (i > 0) {
                System.out.println("插入成功");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.release(conn,pst,null);
        }
    }
}
```

2、删除

```java
package com.theo.lesson03;

import com.theo.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDelete {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "DELETE FROM `users` WHERE id=?";
            pst = conn.prepareStatement(sql);//预编译

            //手动给参数赋值
            pst.setInt(1,4);

            //执行
            int i = pst.executeUpdate();
            if (i > 0) {
                System.out.println("删除成功");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.release(conn, pst, null);
        }
    }
}
```

3、更新

```java
package com.theo.lesson03;

import com.theo.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestUpdate {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = " UPDATE `users` SET `NAME`=?,`email`=? WHERE id=?";
            pst = conn.prepareStatement(sql);

            pst.setString(1, "theofang");
            pst.setString(2, "theofang@qq.com");
            pst.setInt(3, 1);
            int i = pst.executeUpdate();
            if (i > 0) {
                System.out.println("更新成功");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
```

4、查询

```java
package com.theo.lesson03;

import com.theo.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestSelect {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from `users` where id=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, 1);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("NAME"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.release(conn, pst, rs);
        }
    }
}
```

5、防止SQL注入

```java
package com.theo.lesson03;

import com.theo.lesson02.utils.JdbcUtils;

import java.sql.*;

public class SQL注入 {
    public static void main(String[] args) {
        //正常登录
        login("theofang","123456");
        //login("'or' 1=1","'or' 1=1");
    }

    //登录业务
    public static void login(String username, String password) {
        Connection conn = null;
        //PreparedStatement 防止SQL注入的本质是：把传递进来的参数当做字符
        //假设其中存在转义字符，就直接忽略，比如说 ' 会被直接转义
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();

            String sql = "select * from `users` where `NAME` =? and `PASSWORD` =?";

            pst = conn.prepareStatement(sql);

            pst.setString(1,username);
            pst.setString(2,password);
            rs = pst.executeQuery(); //查询完毕会返回一个结果

            while (rs.next()) {
                System.out.println(rs.getString("NAME"));
                System.out.println(rs.getString("PASSWORD"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.release(conn, pst, rs);
        }
    }
}
```

### 10.6、使用IDEA连接数据库

### 10.7、事务

要么都成功，要么都失败

> ACID原则

原子性：要么全部完成，要么全不完成

一致性：总数不变

**隔离性：多个进程互不干扰**

持久性：一单提交不可逆，持久化到数据库了



隔离性的问题：

- 脏读：一个事务读取了另一个没有提交的事务
- 不可重复读：在同一个事务内，重复读取表中的数据，表数据发生了改变
- 虚读（幻读）：在一个事务内，读取到了别人插入的数据，导致前后读出来的结果不一致

> 代码实现

1、开启事务 `conn.setAutoCommit(false);`

2、一组业务提交完毕，提交事务

3、可以在catch语句中显式地定义回滚语句，但默认失败就会回滚。

```java
package com.theo.lesson04;

import com.theo.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction2 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();

            //关闭数据库的自动提交功能，会自动开启事务
            conn.setAutoCommit(false);

            String sql1 = "update account set money = money-100 where name = 'A'";
            pst = conn.prepareStatement(sql1);
            pst.executeUpdate();

            /*int x = 1/0;//会报错*/

            String sql2 = "update account set money = money+100 where name = 'B'";
            pst = conn.prepareStatement(sql2);
            pst.executeUpdate();

            //业务完毕，提交事务
            conn.commit();
            System.out.println("成功");
        } catch (SQLException throwables) {
            //如果失败，则默认回滚
            /*try {
                conn.rollback();//如果失败则回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
            throwables.printStackTrace();
        }finally {
            JdbcUtils.release(conn, pst, rs);
        }
    }
}
```

### 10.8、数据库连接池

数据库连接--执行完毕--释放     

连接--释放 十分浪费系统资源

**池化技术：准备一些预先的资源，过来就连接预先准备好的**

银行：------开门--业务员：等待 ---服务---

常用连接数：10个

**最小连接数：10个**

**最大连接数：15（业务最高承载上限）**

**等待超时：100ms**

编写连接池，实现一个借口：DataSource

> 开源数据源实现

DBCP

C3P0

Druid：阿里巴巴

使用这些数据库连接池之后，我们在项目开放中就不需要编写连接数据库的代码了。

> DBCP

需要用到的jar包

commons-dbcp-1.4、commons-pool-1.6

- 使用dbcp2版本会报错，需要找到dbcp2的配置方法

> C3P0

需要导入的jar包













































