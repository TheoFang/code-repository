## SMBMS

功能

- 登录注销
- 用户管理（增删改查）
- 订单管理（增删改查）
- 供应商管理（增删改查）

数据库

![image-20200826092939788](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200826092939788.png)

项目如何搭建？

是否使用Maven？（依赖 or jar包）

## 项目搭建准备工作

1、使用Maven模板创建新项目

![image-20200826093350078](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200826093350078.png)

2、配置web.xml为最适合（可以取tomcat的ROOT目录中找）

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0"
         metadata-complete="true">

  <display-name>Welcome to Tomcat</display-name>
  <description>
    Welcome to Tomcat
  </description>

</web-app>
```

3、main目录下添加 java 和 resources目录，并分别右键-->Mark Directory as "Sources Root"和"Resources Root"（新版IDEA会有自动提示）

4、配置Tomcat并测试项目能否跑起来

![image-20200826095258298](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200826095258298.png)

![image-20200826095718048](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200826095718048.png)

- 问题及解决方案![image-20200826095851698]

![image-20200826100001182](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200826100001182.png)

​	4.1、

![image-20200826095914585](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200826095914585.png)![image-20200826100032609](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200826100032609.png)

![image-20200826100957351](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200826100957351.png)

5、导入项目中需要的jar包

配置pom.xml

```xml
<dependencies>
    <!-- servlet标签库 -->
  <dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>servlet-api</artifactId>
    <version>2.5</version>
  </dependency>
    <!-- jsp依赖 -->
  <dependency>
    <groupId>javax.servlet.jsp</groupId>
    <artifactId>jsp-api</artifactId>
    <version>2.3.3</version>
  </dependency>
    <!-- 数据库连接 （Tomcat中也要导入）-->
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.47</version>
  </dependency>
    <!-- JSTL表达式的依赖 （Tomcat中也要导入）-->
    <dependency>
      <groupId>javax.servlet.jsp.jstl</groupId>
      <artifactId>jstl-api</artifactId>
      <version>1.2</version>
    </dependency>
    <!-- standard标签库（Tomcat中也要导入） -->
    <dependency>
      <groupId>taglibs</groupId>
      <artifactId>standard</artifactId>
      <version>1.1.2</version>
    </dependency>
</dependencies>
```

6、创建项目包结构

![image-20200826105717770](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200826105717770.png)

7、编写实体类

ORM：对象关系映射

- 表 --- 类
- 字段 ---  属性
- 行记录 --- 对象

编写实体类中，为何整数数据要用 **Integer** 类型而不用 **int **

- int 的默认值为0，而Integer默认值为null。数据库中数据存在为空的情况，返回数据库字段值是null的话，int 类型会报错。
- int 是基本数据类型，其声明的是变量，而null则是对象。

8、编写基础公共类

1. 数据库配置文件

   ```properties
   driver=com.mysql.jdbc.Driver
   url=jdbc:mysql://localhost:3306/smbms?useUnicode=true&characterEncoding=utf8&useSSl=false
   username=root
   password=theofang
   ```

2. 编写数据库的公共类

   ```java
   package com.theo.dao;
   
   
   import java.io.IOException;
   import java.io.InputStream;
   import java.sql.*;
   import java.util.Properties;
   
   //操作数据库的基类（公共类）
   public class BaseDao {
       private static String driver;
       private static String url;
       private static String username;
       private static String password;
   
       //静态代码块，类加载的时候就初始化了
       static {
           Properties properties = new Properties();
           //通过类加载器读取对应的资源
           InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
           try {
               properties.load(is);
           } catch (IOException e) {
               e.printStackTrace();
           }
           driver = properties.getProperty("driver");
           url = properties.getProperty("url");
           username = properties.getProperty("username");
           password = properties.getProperty("password");
       }
   
       //获取数据库的连接
       public static Connection getConnection() {
           Connection connection = null;
           try {
               Class.forName(driver);
               connection = DriverManager.getConnection(url, username, password);
           } catch (Exception e) {
               e.printStackTrace();
           }
           return connection;
       }
   
       //编写查询公共方法
       public static ResultSet execute(Connection connection, String sql, Object[] params, ResultSet resultSet,PreparedStatement preparedStatement) throws SQLException {
           //预编译的sql,在后面直接执行就可以了
           preparedStatement = connection.prepareStatement(sql);
   
           for (int i = 0; i < params.length; i++) {
               //setObject，占位符从1开始
               preparedStatement.setObject(i + 1, params[i]);
           }
           resultSet = preparedStatement.executeQuery();
           return resultSet;
       }
       //编写增删改公共方法
       public static int execute(Connection connection, String sql, Object[] params, PreparedStatement preparedStatement) throws SQLException {
           preparedStatement = connection.prepareStatement(sql);
   
           for (int i = 0; i < params.length; i++) {
               //setObject，占位符从1开始
               preparedStatement.setObject(i + 1, params[i]);
           }
           int updateRows = preparedStatement.executeUpdate();
           return updateRows;
       }
   
       //释放资源
       public static boolean closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
           boolean flag = true;
           if (resultSet != null) {
               try {
                   resultSet.close();
                   //关闭完若还存在，GC回收（垃圾回收期自动清理null的对象）
                   resultSet = null;
               } catch (SQLException throwables) {
                   throwables.printStackTrace();
                   flag = false;
               }
           }
           if (preparedStatement != null) {
               try {
                   preparedStatement.close();
                   //关闭完若还存在，GC回收（垃圾回收期自动清理null的对象）
                   preparedStatement = null;
               } catch (SQLException throwables) {
                   throwables.printStackTrace();
                   flag = false;
               }
           }
           if (connection != null) {
               try {
                   connection.close();
                   //关闭完若还存在，GC回收（垃圾回收期自动清理null的对象）
                   connection = null;
               } catch (SQLException throwables) {
                   throwables.printStackTrace();
                   flag = false;
               }
           }
           return flag;
       }
   }
   ```

3. 编写字符编码过滤器

   ```java
   package com.theo.filter;
   
   import javax.servlet.*;
   import java.io.IOException;
   
   public class CharacterEncodingFilter implements Filter {
       @Override
       public void init(FilterConfig filterConfig) throws ServletException {
   
       }
       @Override
       public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
           request.setCharacterEncoding("utf-8");
           response.setCharacterEncoding("utf-8");
   
           chain.doFilter(request,response);
       }
       @Override
       public void destroy() {
   
       }
   }
   ```

9、导入静态资源

## 登录功能实现

![image-20200827093159073](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200827093159073.png)

1. 编写前端页面

2. 设置首页

   ```xml
   <welcome-file-list>
       <welcome-file>login.jsp</welcome-file>
   </welcome-file-list>
   ```

3. 从底层开始，编写Dao层用户登录的接口

   ```java
   public interface UserDao {
   
       //得到要登录的用户
       public User getLoginUser(Connection connection, String userCode) throws SQLException;
   }
   ```

4. 编写Dao层接口的实现类

   ```java
   public class UserDaoImpl implements UserDao {
       @Override
       public User getLoginUser(Connection connection, String userCode) throws SQLException {
   
           PreparedStatement pstm = null;
           ResultSet rs = null;
           User user = null;
   
           if (connection != null) {
               String sql = "select * from smbms_user where userCode = ?";
               Object[] params = {userCode};
               rs = BaseDao.execute(connection, pstm, rs, sql, params);
               user = new User();
               if (rs.next()) {
                   user = new User();
                   user.setId(rs.getInt("id"));
                   user.setUserCode(rs.getString("userCode"));
                   user.setUserName(rs.getString("userName"));
                   user.setUserPassword(rs.getString("userPassword"));
                   user.setGender(rs.getInt("gender"));
                   user.setBirthday(rs.getDate("birthday"));
                   user.setPhone(rs.getString("phone"));
                   user.setAddress(rs.getString("address"));
                   user.setUserRole(rs.getInt("userRole"));
                   user.setCreatedBy(rs.getInt("createdBy"));
                   user.setCreationDate(rs.getTimestamp("creationgDate"));
                   user.setModifyBy(rs.getInt("modifyBy"));
                   user.setModifyDate(rs.getTimestamp("modifyDate"));
               }
              // BaseDao.closeResource(null, pstm, rs);
               //这里应该是
               BaseDao.closeResource(connection,null,rs);
           }
           return user;
       }
   }
   ```

5. 编写业务层接口（调用、操作Dao层）

   ```java
   public interface UserService {
       //用户登录业务
       public User login(String userCode,String password) throws SQLException;
   }
   ```

6. 业务层实现类

   ```java
   public class UserServiceImpl implements UserService {
       
       //业务层都会调用dao层，所以我们要引入Dao层
       private UserDao userDao;
       public UserServiceImpl() {
           userDao = new UserDaoImpl();
       }
   
       @Override
       public User login(String userCode, String password) {
           Connection connection = null;
           User user = null;
   
           //通过业务层调用对应的具体的数据库操作
           try {
               connection = BaseDao.getConnection();
               user = userDao.getLoginUser(connection, userCode);
           } catch (SQLException throwables) {
               throwables.printStackTrace();
           }finally {
               BaseDao.closeResource(connection,null,null);
           }
           return user;
       }
   }
   ```

7. 编写Servlet(调用业务层)

   ```java
   public class LoginServlet extends HttpServlet {
   
       //Servlet：控制层，调用业务层代码
   
       @Override
       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           System.out.println("进入LoginServlet--start....");
   
           //获取用户名和密码
           String userCode = req.getParameter("userCode");
           String userPassword = req.getParameter("userPassword");
   
           //和数据库中的密码进行对比：调用业务层
           UserServiceImpl userService = new UserServiceImpl();
           User user = userService.login(userCode, userPassword);//这里已经把登录的人查出来了
   
           if (user != null) {//查有此人，可以登录
               //将用户的信息放到Session中；
               req.getSession().setAttribute(Constants.USER_SESSION, user);
               //跳转到内部主页
               resp.sendRedirect("jsp/frame.jsp");
           } else {//查无此人，无法登陆
               //转发回登录页面，顺带提示它，用户名或者密码错误
               req.setAttribute("name","用户名或者密码错误");
               req.getRequestDispatcher("login.jsp").forward(req,resp);
           }
       }
   
       @Override
       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           doGet(req, resp);
       }
   }
   ```

8. 注册Servlet

   ```xml
   <!--Servlet-->
   <servlet>
       <servlet-name>LoginServlet</servlet-name>
       <servlet-class>com.theo.servlet.user.LoginServlet</servlet-class>
   </servlet>
   <servlet-mapping>
       <servlet-name>LoginServlet</servlet-name>
       <url-pattern>/login.do</url-pattern>
   </servlet-mapping>
   ```

9. 测试访问，确保以上功能成功

## 登录功能优化

注销功能：

思路：移除Session，返回登录页面

1. 编写Servlet

   ```java
   public class LogoutServlet extends HttpServlet {
       @Override
       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           //移除用户的Session
           req.getSession().removeAttribute(Constants.USER_SESSION);
           System.out.println(req.getContextPath());
           resp.sendRedirect(req.getContextPath()+"/login.jsp");//返回登录页面
   
       }
   
       @Override
       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           doGet(req, resp);
       }
   }
   ```

2. 注册xml

   ```xml
   <servlet>
       <servlet-name>LogoutServlet</servlet-name>
       <servlet-class>com.theo.servlet.user.LogoutServlet</servlet-class>
   </servlet>
   <servlet-mapping>
       <servlet-name>LogoutServlet</servlet-name>
       <url-pattern>/jsp/logout.do</url-pattern>
   </servlet-mapping>
   ```

   到现在为止，如果登录到主页退出后直接访问主页，是可以访问的，但是不会显示用户（因为移除了Session）

3. 登录拦截优化

   ```java
   public class SysFilter implements Filter {
       @Override
       public void init(FilterConfig filterConfig) throws ServletException {
   
       }
   
       @Override
       public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
           HttpServletRequest request = (HttpServletRequest) req;
           HttpServletResponse response = (HttpServletResponse) resp;
   
           //过滤器，从Session中获取用户
           User user = (User)request.getSession().getAttribute(Constants.USER_SESSION);
   
           if (user == null) {//用户已注销（Session已移除）或者未登录
               response.sendRedirect("/smbms/error.jsp");
           } else {
               chain.doFilter(req,resp);
           }
       }
   
       @Override
       public void destroy() {
   
       }
   }
   ```

4. 注册xml

   ```xml
   <!--用户登录过滤器-->
   <filter>
       <filter-name>SysFilter</filter-name>
       <filter-class>com.theo.filter.SysFilter</filter-class>
   </filter>
   <filter-mapping>
       <filter-name>SysFilter</filter-name>
       <url-pattern>/jsp/*</url-pattern>
   </filter-mapping>
   ```

测试登录，注销，权限验证功能

## 密码修改

1. 导入前端素材

   ```html
   <li><a href="${pageContext.request.contextPath }/jsp/pwdmodify.jsp">密码修改</a></li>
   ```

2. 写项目，建议从底层向上写

   ![image-20200828142354394](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200828142354394.png)

3. UserDao 接口

   ```java
   //修改当前用户密码
   public int updatePwd(Connection connection,int id,String password)throws SQLException;
   ```

4. UserDao 接口实现类

   ```java
   //修改当前用户密码
   @Override
   public int updatePwd(Connection connection, int id, String password) throws SQLException {
       PreparedStatement pstm = null;
       int execute = 0;
       if (connection != null) {
           String sql = "update smbms_user set userPassword = ? where id = ?";
           Object[] params = {password, id};
           execute = BaseDao.execute(connection, pstm, sql, params);
           System.out.println(connection.isClosed());
           BaseDao.closeResource(connection, null, null);
           System.out.println(connection.isClosed());
       }
       return execute;
   }
   ```

5. UserService 层

   ```java
   //根据用户id修改密码
   public boolean updatePwd(int id, String pwd);
   ```

6. UserService 接口实现类

   ```java
   @Override
   public boolean updatePwd(int id, String pwd) {
       Connection connection = null;
       boolean flag = false;
       //修改密码
       try {
           connection = BaseDao.getConnection();
           if (userDao.updatePwd(connection, id, pwd) > 0) {
               flag = true;
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }finally {
           BaseDao.closeResource(connection, null, null);
       }
       return flag;
   }
   ```

