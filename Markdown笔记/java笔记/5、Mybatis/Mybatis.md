## Mybatis

环境：

- JDK1.8
- Mysql 5.7
- Maven 3.6.1
- IDEA

回顾：

- JDBC
- MySQL
- Java基础（封装，继承思想）
- Maven
- Junit

SSM框架：配置文件的，最好的学习方式：看官网文档；

## 1、简介



### 1.1、What

![image-20200901144615073](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200901144615073.png)

- MyBatis 是一款优秀的**持久层框架**。
- 它支持自定义 SQL、存储过程以及高级映射。
- MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。
- MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。
- MyBatis 本是[apache](https://baike.baidu.com/item/apache/6265)的一个开源项目[iBatis](https://baike.baidu.com/item/iBatis), 2010年这个项目由apache software foundation 迁移到了google code，并且改名为MyBatis 。
- 2013年11月迁移到Github。

如何获得Mybatis

- Maven仓库

  ```xml
  <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
  <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.5</version>
  </dependency>
  ```

- Github（https://github.com/mybatis/mybatis-3/releases）
- 中文文档（https://mybatis.org/mybatis-3/zh/index.html）

### 1.2、持久化

数据持久化

- 持久化是将程序的数据在持久状态和瞬时状态转化的过程
- 内存：**断电即失**
- 数据库（jdbc），io文件持久化
- 生活：冷藏，罐头。

为什么要持久化？

- 有一些对象不能让它丢掉。
- 内存太贵了

### 1.3、持久层

Dao层，Service层，Controller层。。。

- 完成持久化工作的代码块
- 层界限十分明显

### 1.4、为什么需要Mybatis?

- 帮助程序猿将数据存入到数据库中
- 方便
- 传统的JDBC代码太复杂，为了简化，使用框架、自动化。
- 不用Mybatis也可以，更容易上手。**技术没有高低之分。**
- 优点
  - 简单易学：本身就很小且简单。没有任何第三方依赖，最简单安装只要两个jar文件+配置几个sql映射文件易于学习，易于使用，通过文档和源代码，可以比较完全的掌握它的设计思路和实现。
  - 灵活：mybatis不会对应用程序或者数据库的现有设计强加任何影响。 sql写在xml里，便于统一管理和优化。通过sql语句可以满足操作数据库的所有需求。
  - 解除sql与程序代码的耦合：通过提供DAO层，将业务逻辑和数据访问逻辑分离，使系统的设计更清晰，更易维护，更易单元测试。sql和代码的分离，提高了可维护性。
  - 提供映射标签，支持对象与数据库的orm字段关系映射
  - 提供对象关系映射标签，支持对象关系组建维护
  - 提供xml标签，支持编写动态sql。
  - **使用的人多**

框架：Spring SpringMVC SpringBoot

## 2、Mybatis程序

思路：搭建环境-->导入Mybatis-->编写代码-->测试

### 2.1、搭建环境

搭建数据库

```sql
CREATE DATABASE `mybatis`;

CREATE TABLE `user`(
	`id` INT(20) NOT NULL PRIMARY KEY,
	`name` VARCHAR(30) DEFAULT NULL,
	`pwd` VARCHAR(30) DEFAULT NULL

)ENGINE=INNODB DEFAULT CHARSET=utf8;

USE `mybatis`;

INSERT INTO `user`(`id`,`name`,`pwd`) VALUES
(1,'theo','1234'),
(2,'fang','1234'),
(3,'lis','1234');
```

新建项目

1. 新建一个普通的Maven项目

2. 删除src目录

3. 导入依赖

   ```xml
   <!--导入依赖-->
   <dependencies>
       <!--mysql驱动-->
       <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>5.1.47</version>
       </dependency>
       <!--mybatis-->
       <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis</artifactId>
           <version>3.5.2</version>
       </dependency>
       <!--junit-->
       <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>4.12</version>
           <scope>test</scope>
       </dependency>
   </dependencies>
   ```

### 2.2、创建一个模块（不用频繁导包，直接用父项目的）

- 编写mybatis的核心配置文件

  ```xml
  <?xml version="1.0" encoding="UTF8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <!--configuration核心配置文件-->
  <configuration>
      <environments default="development">
          <environment id="development">
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.jdbc.Driver"/>
                  <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=false&amp;useUnicode=true&amp;characterEncoding=utf-8"/>
                  <property name="username" value="root"/>
                  <property name="password" value="theofang"/>
              </dataSource>
          </environment>
      </environments>
  </configuration>
  ```

- 编写mybatis工具类

  ```java
  //sqlSessionFactory  sqlSession
  public class MybatisUtils {
      private static SqlSessionFactory sqlSessionFactory;
      //使用Mybatis第一步：获取sqlSessionFactory对象
      static {
          try {
              String resource = "mybatis-config.xml";
              InputStream inputStream = Resources.getResourceAsStream(resource);
              sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  
      public static SqlSession getSqlSession() {
          /*SqlSession sqlSession = sqlSessionFactory.openSession();
          return sqlSession;*/
          return sqlSessionFactory.openSession();
      }
  }
  ```

### 2.3、编写代码

- 实体类

  ```java
  public class User {
      private Integer id;
      private String name;
      private String pwd;
  
      public User() {
      }
  
      public User(Integer id, String name, String pwd) {
          this.id = id;
          this.name = name;
          this.pwd = pwd;
      }
  
      public Integer getId() {
          return id;
      }
  
      public void setId(Integer id) {
          this.id = id;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public String getPwd() {
          return pwd;
      }
  
      public void setPwd(String pwd) {
          this.pwd = pwd;
      }
  
      @Override
      public String toString() {
          return "User{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  ", pwd='" + pwd + '\'' +
                  '}';
      }
  }
  ```

- Dao接口

  ```java
  public interface UserDao {
      List<User> getUserList();
  }
  ```

- 接口实现类（由原来的UserDaoImpl转变为一个Mapper配置文件）

  ```xml
  <?xml version="1.0" encoding="UTF8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <!--namespace绑定一个对应的Dao/Mapper接口--><!--类比之前的UserDaoImpl实现接口-->
  <mapper namespace="com.theo.dao.UserDao">
      <!--select查询语句-->
      <select id="getUserList" resultType="com.theo.pojo.User">
          select * from mybatis.user
      </select>
  </mapper>
  ```

### 2.4、测试

- junit测试

  ```java
  public class UserDaoTest {
      @Test
      public void test() {
          //第一步：获取SqlSession对象
          SqlSession sqlSession = MybatisUtils.getSqlSession();
          //执行SQL
          //方式一：getMapper
          UserDao userDao = sqlSession.getMapper(UserDao.class);
          List<User> userList = userDao.getUserList();
  
          for (User user : userList) {
              System.out.println(user);
          }
          //关闭SqlSession
          sqlSession.close();
  
      }
  }
  ```

- 出错问题

  ```xml
  <?xml version="1.0" encoding="UTF8" ?>
  ```

  ```xml
  <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=false&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
  ```

  1. 配置没有注册
  2. 绑定接口错误
  3. 方法名不对
  4. 返回类型不对
  5. Maven导出资源问题

- 官方建议

  ```java
  public class UserDaoTest {
      @Test
      public void test() {
          //第一步：获取SqlSession对象
          SqlSession sqlSession = MybatisUtils.getSqlSession();
          //执行SQL
          try {
              //方式一(新）：getMapper
              UserDao userDao = sqlSession.getMapper(UserDao.class);
              List<User> userList = userDao.getUserList();
  
              for (User user : userList) {
                  System.out.println(user);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }finally {
              //关闭SqlSession
              sqlSession.close();
          }
  
  
          //方式二（旧）：
         /* List<User> userList1 = sqlSession.selectList("com.theo.dao.UserDao.getUserList");
          for (User user : userList1) {
              System.out.println(user);
          }*/
      }
  }
  ```

## 3、CRUD

### 1、namespace

namespace中的包名要和Dao/Mapper接口的包名一致

### 2、select

选择，查询语句

- id：就是对应的namespace中的方法名
- parameterType：参数类型
- resultType：SQL语句执行的返回值！

1. 编写接口

   ```java
   //根据ID查询用户
   User getUserById(int id);
   ```

2. 编写对应的Mapper中的SQL语句

   ```xml
   <select id="getUserById" parameterType="int" resultType="com.theo.pojo.User">
       select * from mybatis.user where id=#{id}
   </select>
   ```

3. 测试

   ```java
   @Test
   public void getUserByIdTest() {
       SqlSession sqlSession = MybatisUtils.getSqlSession();
       UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
       User user = mapper.getUserById(1);
       System.out.println(user);
       sqlSession.close();
   }
   ```

### 3、Insert

- ```xml
  <insert id="addUser" parameterType="com.theo.pojo.User" >
      insert into mybatis.user (id,name,pwd) values (#{id},#{name},#{pwd})
  </insert>
  ```

### 4、update

- ```xml
  <update id="updateUser" parameterType="com.theo.pojo.User">
      update mybatis.user set name = #{name},pwd=#{pwd} where id=#{id};
  </update>
  ```

### 5、delete

- ```xml
  <delete id="deleteUser" parameterType="int">
      delete from mybatis.user where id=#{id}
  </delete>
  ```

**注意点：**

- 增删改需要事务提交

### 6、错误分析

- 标签不要匹配错误
- resource绑定mapper，需要使用路径（/）
- 程序配置文件要符合规范
- NullPointerException，没有注册到资源
- 输出的xml文件中存在中文乱码问题！
- Maven资源没有导出问题

### 7、万能的Map

假设实体类或者数据库中的表、字段或者参数过多，我们应当考虑使用Map！

- ```java
  //万能的Map
  int addUser2(Map<String,Object> map);
  ```

- ```xml
  <!--传递map的key-->
  <insert id="addUser2" parameterType="map">
              insert into mybatis.user (id,name,pwd) values (#{userid},#{userName},#{password})
  </insert>
  ```

- ```java
  @Test
  public void addUser2() {
      SqlSession sqlSession = MybatisUtils.getSqlSession();
      UserMapper mapper = sqlSession.getMapper(UserMapper.class);
  
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("userid", 5);
      map.put("userName", "hello");
      map.put("password", "jdfdsk");
  
      mapper.addUser2(map);
      sqlSession.commit();
      sqlSession.close();
  }
  ```

Map传递参数，直接在SQL中取出key即可！【parameterType="map"】

对象传递参数，直接在SQL中取对象的属性即可！【parameterType="com.theo.pojo.User"】

只有一个基本类型参数的情况下，可以直接在SQL中取到！

多个参数用Map，或者注解！

### 8、思考

模糊查询

1. Java代码执行的时候，传递通配符（%%）

   ```java
   List<User> userList = mapper.getUserLike("%li%");
   ```

2. 在sql拼接中使用通配符

   ```xml
   <select id="getUserLike" resultType="com.theo.pojo.User">
       select * from mybatis.user where name like "%"#{value}"%"
   </select>
   ```

   ```xml
   <select id="getUserLike" resultType="com.theo.pojo.User">
       select * from mybatis.user where name like '%${value}%'
   </select>
   ```

## 4、配置解析

### 1、核心配置文件

- mybatis-config.xml
- - ![image-20200902145501808](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200902145501808.png)

### 2、环境配置（environments）

- MyBatis 可以配置成适应多种环境
- **尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境。**
  - **每个数据库对应一个 SqlSessionFactory 实例**

- Mybatis默认的事务管理器：JDBC；连接池：POOLED
- 学会使用配置多套运行环境

### 3、属性（properties）

通过properties属性实现引用配置文件

编写一个配置文件

- db.properties

  - ```properties
    driver=com.mysql.jdbc.Driver
    url=jdbc:mysql://localhost:3306/mybatis?useSSL=false&amp;useUnicode=true&amp;characterEncoding=UTF-8
    username=root
    password=theofang
    ```

- 在核心配置文件中引入

  - ```xml
    <properties resource="db.properties"/>
    ```

- 可以直接引入外部文件

- 可以在其中增加一些属性配置

- 如果一个属性在不只一个地方进行了配置，那么，MyBatis 将按照下面的顺序来加载：

  - 首先读取在 properties 元素体内指定的属性。
  - 然后根据 properties 元素中的 resource 属性读取类路径下属性文件，或根据 url 属性指定的路径读取属性文件，并覆盖之前读取过的同名属性。
  - 最后读取作为方法参数传递的属性，并覆盖之前读取过的同名属性。

- 因此，通过方法参数传递的属性具有最高优先级，resource/url 属性中指定的配置文件次之，最低优先级的则是 properties 元素中指定的属性。

### 4、类型别名（typeAliases）

- 类型别名可为 Java 类型设置一个缩写名字。 

- 它仅用于 XML 配置，意在降低冗余的全限定类名书写。

  ```xml
  <!--可以给实体类起别名-->
  <typeAliases>
      <typeAlias type="com.theo.pojo.User" alias="User"/>
  </typeAliases>
  ```

  ```xml
  <select id="getUserList" resultType="User">
      select * from mybatis.user
  </select>
  ```

- 也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean

  扫描实体类的包，它的默认别名就位这个类的类名，首字母小写

  ```xml
  <typeAliases>
      <package name="com.theo.pojo"/>
  </typeAliases>
  ```

  ```xml
  <select id="getUserList" resultType="user">
      select * from mybatis.user
  </select>
  ```

- 在实体类比较少的时候，使用第一种方式

- 如果实体类十分多，建议使用第二种

- 第一种可以自定义别名，第二种则不行（可以使用注解解决）

  ```java
  @Alias("user")
  public class User {}
  ```


### 5、设置

这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为。

![image-20200902192221806](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200902192221806.png)

![image-20200902192235958](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200902192235958.png)

### 6、其他配置

- 类型处理器（typeHandlers）
- objectFactory（对象工厂）
- plugins（插件）
  - mybatis-generator-core
  - mybatis-plus
  - 通用mapper

### 7、映射器（mappers）

MapperRegistry：注册绑定配置文件

![image-20200902193301528](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200902193301528.png)

方式一：【推荐使用】

```xml
<!--每一个Mapper.xml都需要在Mybatis核心配置文件中注册-->
<mappers>
    <mapper resource="com/theo/dao/UserMapper.xml"/>
</mappers>
```

方式二：使用class方式绑定注册

```xml
<mappers>
        <mapper class="com.theo.dao.UserMapper"/>
    </mappers>
```

注意：

- 接口和对应的Mapper配置文件必须同名
- 接口和对应的Mapper配置文件必须在同一个包下

方式三：使用扫描包进行注册绑定

```xml
<mappers>
<!--        <mapper resource="com/theo/dao/UserMapper.xml"/>-->
<!--        <mapper class="com.theo.dao.UserMapper"/>-->
        <package name="com.theo.dao"/>
    </mappers>
```

### 8、生命周期和作用域（Scope）

生命周期和作用域是至关重要的，错误的使用会导致非常严重的**并发性问题**

![image-20200902195814987](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200902195814987.png)

SqlSessionFactoryBuilder：

- 一旦创建了SqlSessionFactory，就不再需要它了
- 局部变量

SqlSessionFactory：

- 可以理解为：数据库连接池
- SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。
- 因此 SqlSessionFactory 的最佳作用域是**应用作用域**。
- 最简单的就是**单例模式**或者静态单例模式。

SqlSession

- 连接到连接池的一个请求！
- SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。
- 用完之后需要赶紧关闭，否则资源被占用！

![image-20200902201048574](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200902201048574.png)

这里的每一个Mapper都代表一个具体的业务

## 5、解决属性名和字段名不一致的问题

### 1、问题

数据库中的字段

![image-20200902205859209](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200902205859209.png)



```java
public class User {
    private Integer id;
    private String name;
    private String password;
```

![image-20200902212620645](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200902212620645.png)

```sql
//select * from mybatis.user where id=#{id}
//类型处理器
//select id,name,pwd from mybatis.user where id =#{id}
```

解决方法：

- 起别名

  ```xml
  <select id="getUserById" parameterType="int" resultType="com.theo.pojo.User">
      select id,name,pwd as password from mybatis.user where id=#{id}
  </select>
  ```

- ResultMap

### 2、resultMap

结果集映射

```
id	name	pwd
id	name	password
```

```xml
<mapper namespace="com.theo.dao.UserMapper">
    <!--结果集映射-->
    <resultMap id="UserMap" type="User">
        <!--column数据库中的字段，property实体类中的属性-->
        <result column="id" property="id"/>
        <result column="name" property="name"/>
        <result column="pwd" property="password"/>
    </resultMap>
    <select id="getUserById" parameterType="int" resultMap="UserMap">
        select * from mybatis.user where id=#{id}
    </select>
</mapper>
```

- `resultMap` 元素是 MyBatis 中最重要最强大的元素。
- ResultMap 的设计思想是，对简单的语句做到零配置，对于复杂一点的语句，只需要描述语句之间的关系就行了。

## 6、日志

### 6.1、日志工厂

如果一个数据库操作出现了异常，我们需要排错，日志就是最好的助手！

曾经：sout、debug

现在：日志工厂！

![image-20200903075852441](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200903075852441.png)

- SLF4J 
-  LOG4J 【掌握】
-  LOG4J2 
- JDK_LOGGING 
- COMMONS_LOGGING  
- STDOUT_LOGGING 【掌握】
- NO_LOGGING

在Mybatis中具体使用哪一个日志实现，在设置中设定。

**STDOUT_LOGGING标准日志输出**

在mybatis核心配置文件中，配置日志

```xml
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
```

![image-20200903081330958](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200903081330958.png)

### 6.2、LOG4J

- Log4j是[Apache](https://baike.baidu.com/item/Apache/8512995)的一个开源项目，通过使用Log4j，我们可以控制日志信息输送的目的地是[控制台](https://baike.baidu.com/item/控制台/2438626)、文件、[GUI](https://baike.baidu.com/item/GUI)组件，甚至是套接口服务器、[NT](https://baike.baidu.com/item/NT/3443842)的事件记录器、[UNIX](https://baike.baidu.com/item/UNIX) [Syslog](https://baike.baidu.com/item/Syslog)[守护进程](https://baike.baidu.com/item/守护进程/966835)等；
- 我们也可以控制每一条日志的输出格式；
- 通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程。
- 这些可以通过一个[配置文件](https://baike.baidu.com/item/配置文件/286550)来灵活地进行配置，而不需要修改应用的代码。

1. 导入log4j的包

   ```xml
   <!-- https://mvnrepository.com/artifact/log4j/log4j -->
   <dependency>
       <groupId>log4j</groupId>
       <artifactId>log4j</artifactId>
       <version>1.2.17</version>
   </dependency>
   ```

2. log4j.properties

   ```properties
   #将等级为DEBUG的日志信息输出到console和file这两个目的地，console和file的定义在下面的代码
   log4j.rootLogger=DEBUG,console,file
   
   #控制台输出的相关设置
   log4j.appender.console = org.apache.log4j.ConsoleAppender
   log4j.appender.console.Target = System.out
   log4j.appender.console.Threshold=DEBUG
   log4j.appender.console.layout = org.apache.log4j.PatternLayout
   log4j.appender.console.layout.ConversionPattern=[%c]-%m%n
   
   #文件输出的相关设置
   log4j.appender.file = org.apache.log4j.RollingFileAppender
   log4j.appender.file.File=./log/theo.log
   log4j.appender.file.MaxFileSize=10mb
   log4j.appender.file.Threshold=DEBUG
   log4j.appender.file.layout=org.apache.log4j.PatternLayout
   log4j.appender.file.layout.ConversionPattern=[%p][%d{yy-MM-dd}][%c]%m%n
   
   #日志输出级别
   log4j.logger.org.mybatis=DEBUG
   log4j.logger.java.sql=DEBUG
   log4j.logger.java.sql.Statement=DEBUG
   log4j.logger.java.sql.ResultSet=DEBUG
   log4j.logger.java.sql.PreparedStatement=DEBUG
   ```

3. 配置log4j为日志的实现

   ```xml
   <settings>
           <setting name="logImpl" value="LOG4J"/>
       </settings>
   ```

4. log4j的使用，测试

简单使用

1. 在要使用Log4j的类中导入包 

   ```java
   import org.apache.log4j.Logger;
   ```

2. 生成日志对象，参数为当前类的class

   ```java
   static Logger logger = Logger.getLogger(UserMapperTest.class);
   ```

3. 日志级别

   ```java
   logger.info("info:进入了testLog4j");
   logger.debug("debug:进入了testLog4j");
   logger.error("error:进入了testLog4j");
   ```

## 7、分页

思考：**为什么要分页**

- 减少数据的处理量，增加效率

### 7.1、使用Limit分页

```sql
语法：select * from user limit startIndex,pageSize;
select * from user limit 3; 	#[0,n]
```

使用Mybatis实现分页，核心是SQL

1. 接口

   ```java
   //分页
   List<User> getUserLimit(Map<String,Integer> map);
   ```

2. Mapper.xml

   ```xml
   <select id="getUserLimit" parameterType="map" resultMap="UserMap">
       select * from mybatis.user limit #{startIndex},#{pageSize}
   </select>
   ```

3. 测试

   ```java
   @Test
   public void getUserByLimit() {
       SqlSession sqlSession = MybatisUtils.getSqlSession();
       UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
       Map<String,Integer> map=new HashMap<String,Integer>();
       map.put("startIndex", 0);
       map.put("pageSize", 2);
       List<User> userList = mapper.getUserLimit(map);
       for (User user : userList) {
           System.out.println(user);
       }
       sqlSession.close();
   ```

### 7.2、RowBounds分页

不再使用SQL实现分页

1. 接口

   ```java
   //分页2
   List<User> getUserByRowBounds(Map<String,Integer> map);
   ```

2. Mapper.xml

   ```xml
   <select id="getUserByRowBounds" resultMap="UserMap">
       select * from mybatis.user
   </select>
   ```

3. 测试

   ```java
   @Test
   public void getUserByRowBounds() {
       SqlSession sqlSession = MybatisUtils.getSqlSession();
   
       //RowBounds实现
       RowBounds rowBounds = new RowBounds(1, 2);
   
       //通过Java代码层面实现分页
       List<User> userList = sqlSession.selectList("com.theo.dao.UserMapper.getUserByRowBounds",null,rowBounds);
   
       for (User user : userList) {
           System.out.println(user);
       }
   }
   ```

### 7.3、分页插件

![image-20200903100921605](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200903100921605.png)

## 8、使用注解开发

### 8.1、面向接口编程

![image-20200903101532853](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200903101532853.png)



### 8.2、使用注解开发

1. 注解在接口上实现

   ```java
   @Select("select * from user")
   List<User> getUsers();
   ```

2. 需要在核心配置文件中绑定接口

   ```xml
   <!--    绑定接口-->
       <mappers>
           <mapper class="com.theo.dao.UserMapper"/>
       </mappers>
   ```

3. 测试



本质：反射机制实现

底层：动态代理！

![image-20200903112235155](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200903112235155.png)

**Mybatis详细执行流程**

  ![未命名文件](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5C%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6.png)

### 8.3、CRUD

可以在工具类创建的时候实现自动提交事务

```java
public static SqlSession getSqlSession() {
    return sqlSessionFactory.openSession(true);
}
```

编写接口，增加注解

```java
public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();

    //方法存在多个简单类型的参数，所有参数之前必须加上@Param("value")注解
    @Select("select * from user where id=#{id} and name=#{name}")
    User getUserById(@Param("id") int id,@Param("name") String name);

    @Insert("insert into user(id,name,pwd) values(#{id},#{name},#{password})")
    int addUser(User user);

    @Update("update user set name=#{name},pwd=#{password} where id=#{id}")
    int updateUser(User user);

    @Delete("delete from user where id=#{uid}")
    int deleteUser(@Param("uid") int id);
}
```

测试

【注意】：必须要将接口注册绑定到核心配置文件中

```xml
<!--    绑定接口-->
    <mappers>
        <mapper class="com.theo.dao.UserMapper"/>
    </mappers>
```

**关于@Param()注解**

- 基本类型的参数或者String类型需要加上
- 引用类型不需要加
- 如果只有一个基本类型可以忽略，但是建议加上
- 在SQL中引用的就是这里的@Param("value")中设定的属性名！

#{}  VS  ${}

## 9、Lombok

步骤：

1. IDEA中安装Lombok

2. 在项目中导入Lombok包

   ```xml
   <dependency>
       <groupId>org.projectlombok</groupId>
       <artifactId>lombok</artifactId>
       <version>1.18.10</version>
   </dependency>
   ```

3. 在实体类上加注解即可

```java
@Getter and @Setter
@FieldNameConstants
@ToString
@EqualsAndHashCode
@AllArgsConstructor, @RequiredArgsConstructor and @NoArgsConstructor
@Log, @Log4j, @Log4j2, @Slf4j, @XSlf4j, @CommonsLog, @JBossLog, @Flogger, @CustomLog
@Data
@Builder
@SuperBuilder
@Singular
@Delegate
@Value
@Accessors
@Wither
@With
@SneakyThrows
@val
@var
@UtilityClass
```

```java
@Data：无参构造，get、set、toString、HashCode、equals
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
```

## 10、多对一处理

多对一：

- 多个学生，对应一个老师
- 对于学生，（**关联**）
  - 多个学生关联一个老师【多对一】
- 对于老师，（**集合**）
  - 一个老师有很多学生【一对多】

![image-20200903162621202](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200903162621202.png)

**测试环境搭建**

1. 导入lombok

2. 新建实体类

3. 建立Mapper接口

4. 建立Mapper.xml

5. 在核心配置文件中绑定注册Mapper接口或者文件

   ```xml
   <mapper resource="com/theo/dao/TeacherMapper.xml"/>
   <!--        <mapper class="com.theo.dao.TeacherMapper"/>-->
   ```

6. 测试查询是否能够成功

**按照查询嵌套处理**

```xml
<?xml version="1.0" encoding="UTF8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--configuration核心配置文件-->
<mapper namespace="com.theo.dao.StudentMapper">
    <!--思路
        1.查询所有的学生信息
        2.根据查询出的学生的tid，寻找对应的老师
    -->
    <select id="getStudent" resultMap="StudentTeacher">
        select * from student
    </select>
    <resultMap id="StudentTeacher" type="Student">
        <result property="id" column="id"/>
        <result property="name" column="name"/>
        <!--复杂的属性需要单独处理
        对象就用association
        集合就用collection
        -->
        <association property="teacher" column="tid" javaType="Teacher" select="getTeacher"/>
    </resultMap>
    <select id="getTeacher" resultType="Teacher">
        select * from teacher where id = #{id}
    </select>
</mapper>
```

**按照结果嵌套处理**

```xml
<!--按照结果嵌套处理-->
<select id="getStudent2" resultMap="StudentTeacher2">
    SELECT s.`id` sid,s.`name` sname,t.id tid,t.`name` tname
    FROM student AS s,teacher AS t
    WHERE s.`tid`=t.`id`;
</select>
<resultMap id="StudentTeacher2" type="Student">
    <result property="id" column="sid"/>
    <result property="name" column="sname"/>
    <association property="teacher" javaType="Teacher">
        <result property="name" column="tname"/>
        <result property="id" column="tid"/>
    </association>
</resultMap>
```

回顾Mysql多对一查询方式：

- 子查询
- 联表查询

## 11、一对多处理

比如：一个老师拥有多个学生

对于老师就是一对多的关系！

1. 环境搭建（与刚才相同）

   **实体类**

   ```java
   @Data
   public class Student {
       private int id;
       private String name;
       private int tid;
   }
   ```

   ```java
   @Data
   public class Teacher {
       private int id;
       private String name;
       //一个老师拥有多个学生
       private List<Student> students;
   }
   ```

**按照结果嵌套处理**

```xml
<!--按结果嵌套查询-->
<select id="getTeacher" resultMap="TeacherStudent">
    select s.id sid,s.name sname,t.name tname,t.id tid
    from student s,teacher t
    where s.`tid`=t.`id` and t.id=#{tid};
</select>
<resultMap id="TeacherStudent" type="Teacher">
    <result property="id" column="tid"/>
    <result property="name" column="tname"/>
    <!--javaType=""指定属性的类型！
        集合中的泛型信息，我们使用ofType获取
    -->
    <collection property="students" ofType="Student">
        <result property="id" column="sid"/>
        <result property="name" column="sname"/>
        <result property="tid" column="tid"/>
    </collection>
</resultMap>
```

**按照查询嵌套处理**

```xml
<select id="getTeacher2" resultMap="TeacherStudent2">
    select * from teacher where id=#{tid};
</select>
<resultMap id="TeacherStudent2" type="Teacher">
    <collection property="students" javaType="ArrayList" ofType="Student" select="getStudentByTeacherId" column="id"/>
</resultMap>
<select id="getStudentByTeacherId" resultType="Student">
    select * from student where tid=#{tid};
</select>
```

小结

1. 关联（association）【多对一】
2. 集合（collection）【一对多】
3. javaType  &   ofType
   - javaType用来指定实体类中书写的类型
   - ofType用来指定映射到List或者集合中的pojo类型，泛型中的约束类型！

注意：

- 保证SQL的可读性，尽量保证通俗易懂
- 注意一对多和多对一中，属性名和字段的问题！
- 如果问题不好排查错误，可以使用日志，建议使用log4j

面试高频

- Mysql引擎
- InnoDB底层原理
- 索引
- 索引优化

## 12、动态SQL

是什么：根据不同的条件生成不同的SQL语句

```
如果你之前用过 JSTL 或任何基于类 XML 语言的文本处理器，你对动态 SQL 元素可能会感觉似曾相识。在 MyBatis 之前的版本中，需要花时间了解大量的元素。借助功能强大的基于 OGNL 的表达式，MyBatis 3 替换了之前的大部分元素，大大精简了元素种类，现在要学习的元素种类比原来的一半还要少。

if
choose (when, otherwise)
trim (where, set)
foreach
```

**搭建环境**

```sql
CREATE TABLE `blog`(
`id` VARCHAR(50) NOT NULL COMMENT '博客id',
`title` VARCHAR(100) NOT NULL COMMENT '博客标题',
`author` VARCHAR(30) NOT NULL COMMENT '博客作者',
`create_time` DATETIME NOT NULL COMMENT '创建时间',
`views` INT(30) NOT NULL COMMENT '浏览量'
)ENGINE=INNODB DEFAULT CHARSET=utf8
```

创建一个基础工程

1. 导包

2. 编写配置文件

3. 编写实体类

   ```java
   @Data
   public class Blog {
       private int id;
       private String title;
       private String author;
       private Date createTime;
       private int views;
   }
   ```

4. 编写实体类对应Mapper接口Mapper.xml文件

### IF

1. 接口

   ```java
   //查询博客
   List<Blog> queryBlogIF(Map map);
   ```

2. 配置文件

   ```xml
   <select id="queryBlogIF" parameterType="map" resultType="blog">
       select * from blog where 1=1
       <if test="title != null">
           and title=#{title}
       </if>
       <if test="author != null">
           and author=#{author}
       </if>
   </select>
   ```

3. 测试

   ```java
   @Test
       public void queryBlogIF() {
           SqlSession sqlSession = MybatisUtils.getSqlSession();
           BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
           HashMap map = new HashMap();
   //        map.put("title", "Spring");
           map.put("author", "含章");
           List<Blog> blogs = mapper.queryBlogIF(map);
           for (Blog blog : blogs) {
               System.out.println(blog);
           }
           sqlSession.close();
       }
   ```

### choose、when、otherwise

- ```xml
  <update id="updateBlogSet" parameterType="map">
      update blog
      <set>
          <if test="title != null">
              title=#{title},
          </if>
          <if test="author != null">
              author=#{author}
          </if>
          where id=#{id}
      </set>
  </update>
  ```



### trim（where、set）

- ```xml
  <select id="queryBlogIF" parameterType="map" resultType="Blog">
      select * from blog
      <where>
          <if test="title != null">
              and title=#{title}
          </if>
          <if test="author != null">
              and author=#{author}
          </if>
      </where>
  
  </select>
  ```

- ```xml
  <update id="updateBlogSet" parameterType="map">
      update blog
      <set>
          <if test="title != null">
              title=#{title},
          </if>
          <if test="author != null">
              author=#{author}
          </if>
          where id=#{id}
      </set>
  </update>
  ```

所谓的动态SQL本质还是SQL语句，只是可以在SQL层面去执行一个逻辑代码。

- if
- where
- set
- choose
- when

### SQL片段

有时将一些公共部分抽取出来，方便复用

1. 使用SQL标签抽取公共部分

2. 在需要使用的地方使用include标签引用即可

   ```xml
   <sql id="if-title-author">
       <if test="title != null">
           and title=#{title}
       </if>
       <if test="author != null">
           and author=#{author}
       </if>
   </sql>
   <select id="queryBlogIF" parameterType="map" resultType="Blog">
       select * from blog
       <where>
           <include refid="if-title-author"></include>
       </where>
   
   </select>
   ```

### Foreach

- ```sql
  select * from user where 1=1 and
  
   <foreach item="id"collection="ids"
        open="(" separator="or" close=")">
          #{id}
    </foreach>
  
  (id=1 or id=2 or id=3)
  ```

1. 接口

   ```xml
   //查询第1,2,3号记录的博客
   List<Blog> queryBlogForeach(Map map);
   ```

2. 配置文件

   ```xml
   <!--SELECT * FROM blog WHERE 1=1 AND (id=1 OR id =2 OR id=3)
   传递一个万能Map，这个Map中可以存着一个集合
   -->
   <select id="queryBlogForeach" parameterType="map" resultType="Blog">
       select * from blog
   
       <where>
           <foreach collection="ids" item="id" open="and (" close=")" separator="or">
               id = #{id}
           </foreach>
       </where>
   </select>
   ```

3. 测试

   ```java
   @Test
   public void queryBlogForeach() {
       SqlSession sqlSession = MybatisUtils.getSqlSession();
       BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
       HashMap map = new HashMap();
   
       ArrayList<Integer> ids = new ArrayList<Integer>();
       ids.add(1);
       ids.add(2);
       ids.add(3);
       map.put("ids", ids);
       List<Blog> blogs = mapper.queryBlogForeach(map);
       for (Blog blog : blogs) {
           System.out.println(blog);
       }
       sqlSession.close();
   }
   ```

**动态SQL就是在拼接SQL语句，只要保证SQL的正确性，按照SQL的格式去排列组合就好了**

建议：

- 先在MySQL中写出完整的SQL语句，再对应地去修改成为我们的动态SQL实现通用即可

## 13、缓存

### 13.1、简介

```
查询：连接数据库、耗资源
	一次查询的结果，暂时存在一个可以直接取到的地方（内存）：缓存
	再次查询相同数据时，直接走缓存，不用走数据库了。
```

1. 什么是缓存【Cache】
   - 存在内存中的临时数据
   - 将用户经常查询的数据放在缓存（内存）中，用户去查询数据就不用从磁盘上（关系型数据库数据文件）查询。从缓存中查询，从而提高查询效率，解决了高并发系统的性能问题。
2. 为什么使用缓存
   - 减少和数据库的交互次数，减少系统开销，提高系统效率
3. 什么样的数据库能使用缓存
   - 经常查询并且不经常改变的数据

### 13.2、Mybatis缓存

- MyBatis 内置了一个强大的事务性查询缓存机制，它可以非常方便地配置和定制。缓存可以极大地提升查询效率。
- Mybatis系统默认定义了两级缓存：一级缓存和二级缓存
  - 默认情况下，只有一级缓存开启。（SqlSession级别的缓存，也成为本地缓存）
  - 二级缓存需要手动开启和配置，它是基于namespace级别的缓存。
  - 为了提高扩展性，MyBatis定义了缓存接口Cache。我们可以通过实现Cache接口来自定义二级缓存

### 13.3、一级缓存

- 一级缓存也叫本地缓存：
  - 与数据库同一次会话期间查询到的数据会放在本地缓存中。
  - 以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库

测试步骤：

1. 开启日志

2. 测试在一个Session中查询两次相同记录

3. 查看日志输出

   ![image-20200904134941984](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200904134941984.png)

缓存失效的情况：

1. 查询不同的东西

2. 增删改操作，可能会改变原来的数据，所以必定会刷新缓存！

3. 查询不同的Mapper.xml

4. 手动清理缓存

   ![image-20200904142308604](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200904142308604.png)

小结：一级缓存默认开启，只在一次SqlSession中有效，也就是拿到连接到关闭连接的这个区间段！

一级缓存就是一个Map

### 13.4、二级缓存

- 二级缓存也叫全局缓存，一级缓存作用域太低了，所以诞生了二级缓存

- 基于namespace级别的缓存，一个名称空间，对应一个二级缓存

- 工作机制

  - 一个会话查询一条语句，这个数据就会被放在当前会话的一级缓存中

  - 如果会话关闭了，这个会话对应的一级缓存就没了；但是我们想要的是一级缓存的数据被保存到二级缓存中

  - 新的会话查询信息，就可以从二级缓存中获取内容

    不同的mapper查出的数据会放在自己对应的缓存(map)中

默认情况下，只启用了本地的会话缓存，它仅仅对一个会话中的数据进行缓存。 要启用全局的二级缓存，只需要在你的 SQL 映射文件中添加一行：

```
<cache/>
```

步骤：

1. 开启全局缓存

   ```xml
   <settings>
           <setting name="logImpl" value="STDOUT_LOGGING"/>
           <setting name="mapUnderscoreToCamelCase" value="true"/>
           <!--显式开启全局缓存-->
           <setting name="cacheEnabled" value="true"/>
       </settings>
   ```

2. 在要使用二级缓存的Mapper.xml中开启

   ```xml
   <cache/>
   ```

   

3. 也可以自定义参数

   ```xml
   <!--在当前Mapper.xml使用二级缓存-->
   <cache
           eviction="FIFO"
           flushInterval="60000"
           size="512"
           readOnly="true"
   />
   ```

测试

- 问题

  - 我们需要将实体类序列化

    ```java
    Cause: java.io.NotSerializableException: com.theo.pojo.User
    ```

    ```java
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class User implements Serializable {
        private int id;
        private String name;
        private String pwd;
    }
    ```

- 小结:
  - 主要开启了二级缓存，在同一个Mapper下就有效
  - 所有的数据都会先放在一级缓存中
  - 只有当会话提交或者关闭的时候才会提交到二级缓存中

### 13.5、缓存原理

1. 第一次查询先看二级缓存中有没有数据

2. 再看一级缓存中有没有

3. 查询数据库

   ![image-20200904151702821](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200904151702821.png)

### 13.6、自定义缓存-ehcache

1. 导包

   ```xml
   <!-- https://mvnrepository.com/artifact/org.mybatis.caches/mybatis-ehcache -->
   <dependency>
       <groupId>org.mybatis.caches</groupId>
       <artifactId>mybatis-ehcache</artifactId>
       <version>1.2.1</version>
   </dependency>
   ```