## 5、Maven

为什么要学这个技术？

1. 在Javaweb开发中，需要使用大量的jar包，我们手动去导入；

2. 如何能够让一个东西自动帮我导入和配置jar包。

   因此，Maven诞生了

### 5.1、Maven项目架构管理工具

我们目前就是用来方便导入jar包的！

Maven的核心思想：**约定大于配置**

- 有约束，不要去违反

Maven会规定好该如何编写Java代码，必须要按照这个规范来

### 5.2、下载安装Maven

官网：http://maven.apache.org/

下载完成后解压即可：

建议：电脑上的所有环境都放在一个文件夹下，方便管理

### 5.3、配置环境变量

在我们的系统环境变量中，配置如下配置：

- M2_HOME			(MAVEN安装目录下的bin目录)
- MAVEN_HOME    (MAVEN的目录)
- 在系统的path中配置MAVEN_HOME\bin

测试Maven是否安装成功，保证必须配置完毕！（mvn -version)

### 5.4、配置镜像

- 镜像：mirrors
  - 作用：加速下载

- 国内建议使用阿里云镜像

```
<mirror>
      <id>nexus-aliyun</id>
	  <mirrorOf>*,!jeecg,!jeecg-snapshots</mirrorOf>  
      <name>Nexus aliyun</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
</mirror>
```

### 5.5、本地仓库

建立一个本地仓库：localRepository

```
<localRepository>F:\Environment\apache-maven-3.6.1\maven-repo</localRepository>
```

### 5.6、在IDEA中使用Maven

1、启动IDEA

2、创建一个Maven web项目

3、等待项目初始化完毕

4、观察Maven仓库中多了什么东西

5、IDEA的Maven配置

![image-20200629141223613](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200629141223613.png)

6、到这里，Maven在IDEA中的配置和使用就OK了

### 5.7、创建一个普通的Maven项目

![image-20200629144321276](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200629144321276.png)

### 5.8、标记文件夹功能

![image-20200629144642943](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200629144642943.png)

### 5.9、在IDEA中配置Tomcat



### 5.10、 pom文件

pom.xml是Maven的核心配置文件

由于Maven的约定大于配置，我们之后可能会遇到我们写的配置文件无法被导出或生效。

解决方案：



```xml
<build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
```



## 6、Servlet

### 6.1、Servlet简介

- Servlet就是sun公司开发动态web的一门技术
- sun公司在这些API中提供了一个借口叫做Servlet，如果你想开发一个Servlet程序，只需要完成两个小步骤：
  - 编写一个类，实现Servlet接口
  - 把开发好的java类部署到web服务器中

**把实现了Servlet接口的java程序叫做Servlet**

### 6.2、HelloServlet

Servlet接口在sun公司有两个默认的实现类：HttpServlet、GenericServlet

1、构建一个普通的Maven项目，删掉里面的src目录，以后的学习就在这个里，这个空的工程就是Maven的主工程

2、关于Maven父子工程的理解

父项目中会有

```xml
<modules>
    <module>servlet-01</module>
</modules>
```

子项目中会有

```xml
<parent>
    <artifactId>javaweb-02-servlet</artifactId>
    <groupId>com.theo</groupId>
    <version>1.0-SNAPSHOT</version>
</parent>
```

父项目中的Jar包，子项目可以直接使用，反之不然。

3、Maven环境优化

- 修改web.xml为最匹配的
- 将Maven的结构搭建完整

4、编写一个Servlet程序

1. 编写一个普通类
2. 实现Servlet接口（这里直接继承HttpServlet）

```java
package com.theo.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {

    //由于Get或者Post只是请求实现的不同的方式，所以可以相互调用，业务逻辑都一样。
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ServletOutputStream outputStream = resp.getOutputStream();
        PrintWriter writer = resp.getWriter();//响应流

        writer.print("Hello,Servlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
```

5、编写Servlet的映射

为什么需要映射：我们写的是java程序，但是要通过浏览器访问，而浏览器需要连接web服务器，所以我们需要在web服务中注册我们写的Servlet，还需要给他一个浏览器能够访问的路径；

```xml
<!--注册Servlet-->
<servlet>
    <servlet-name>hello</servlet-name>
    <servlet-class>com.theo.servlet.HelloServlet</servlet-class>
</servlet>
<!--Servlet请求（映射）路径-->
<servlet-mapping>
    <servlet-name>hello</servlet-name>
    <url-pattern>/hello</url-pattern>
</servlet-mapping>
```

6、配置Tomcat

7、启动测试

### 6.3、Servlet原理

Servlet是由web服务器调用，web服务器在收到浏览器请求之后

![image-20200629204815790](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200629204815790.png)

### 6.4、Mapping

1、一个Servlet可以指定一个映射路径

```xml
<servlet-mapping>
    <servlet-name>hello</servlet-name>
    <url-pattern>/hello</url-pattern>
</servlet-mapping>
```

2、一个Servlet可以指定多个映射路径

```xml
<servlet-mapping>
    <servlet-name>hello</servlet-name>
    <url-pattern>/hello</url-pattern>
</servlet-mapping>
<servlet-mapping>
    <servlet-name>hello</servlet-name>
    <url-pattern>/hello2</url-pattern>
</servlet-mapping>
```

3、一个Servlet可以指定通用映射路径

```xml
<servlet-mapping>
    <servlet-name>hello</servlet-name>
    <url-pattern>/hello/*</url-pattern>
</servlet-mapping>
```

4、默认映射路径

```xml
<servlet-mapping>
    <servlet-name>hello</servlet-name>
    <url-pattern>/*</url-pattern>
</servlet-mapping>
```

5、自定义后缀实现请求映射

```xml
<!--注意：url-pattern里*前面不能加项目映射的路径
	hello/sasdlf.do
-->
<<servlet-mapping>
    <servlet-name>hello</servlet-name>
    <url-pattern>*.do</url-pattern>
</servlet-mapping>
```

6、优先级问题

指定了固有的映射路径优先级最高，如果找不到就会走默认的处理请求

```xml
<!--404-->
<servlet>
    <servlet-name>error</servlet-name>
    <servlet-class>com.theo.servlet.ErrorServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>error</servlet-name>
    <url-pattern>/*</url-pattern>
</servlet-mapping>
```

### 6.5、ServletContext

web容器在启动的时候，它会为每个web程序都创建一个对应的ServletContext对象，它代表了当前的web应用。

#### **1、共享数据**

我在这个Servlet中保存的数据可以在另一个Servlet中获得

```java
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //this.getInitParameter();  初始化参数
        //this.getServletConfig()   Servlet配置
        //this.getServletContext()  Servlet上下文
        ServletContext context = this.getServletContext();

        String username = "含章"; //数据
        //将一个数据保存在ServletContext中，名字为：username，值为 `username`
        context.setAttribute("username", username);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

```java
public class GetServlet extends HelloServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        String username = (String)context.getAttribute("username");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print("名字"+username);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

```xml
<servlet>
    <servlet-name>hello</servlet-name>
    <servlet-class>com.theo.servlet.HelloServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>hello</servlet-name>
    <url-pattern>/hello</url-pattern>
</servlet-mapping>
<servlet>
    <servlet-name>getc</servlet-name>
    <servlet-class>com.theo.servlet.GetServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>getc</servlet-name>
    <url-pattern>/getc</url-pattern>
</servlet-mapping>
```

#### 2、获取初始化参数

```xml
<!--配置一些web应用的初始化参数-->
    <context-param>
        <param-name>url</param-name>
        <param-value>jdbc:mysql://localhost:3306/mybatis</param-value>
    </context-param>
```

```java
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServletContext context = this.getServletContext();
    String url = context.getInitParameter("url");
    resp.getWriter().print(url);
}
```



#### 3、请求转发

```java
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServletContext context = this.getServletContext();
    System.out.println("进入了demo04");
    /*//转发的请求路径
    RequestDispatcher requestDispatcher = context.getRequestDispatcher("/gp");
    //调用forward实现请求转发
    requestDispatcher.forward(req, resp);*/
    context.getRequestDispatcher("/gp").forward(req, resp);

}
```

转发和重定向

![image-20200629224906508](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200629224906508.png)

#### 4、读取资源文件

Properties

- 在java目录下新建properties
- 在resources目录下新建properties

发现：都被打包到了同一个路径下：classes，我们俗称这个路径为类路径

思路：需要一个文件流

db.properties

```properties
username=root
password=theofang
```

```java
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");

        Properties prop = new Properties();
        prop.load(is);
        String user = prop.getProperty("username");
        String pwd = prop.getProperty("password");

        resp.getWriter().print(user+":"+pwd);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

访问测试即可

### 6.6、HttpServletResponse

web服务器接收到客户端的http请求，针对这个请求，分别创建一个代表请求的HttpServletRequest对象，代表响应的一个HttpServletResponse对象；

- 如果要获取客户端请求过来的参数：找HttpServletRequest

- 如果要给客户端响应一些信息：找HttpServletResponse

#### 1、简单分类

**负责向浏览器发送数据的方法**

```java
ServletOutputStream getOutputStream() throws IOException;
PrintWriter getWriter() throws IOException;
```

**负责向浏览器发送响应头的方法**

```java
void setCharacterEncoding(String var1);

void setContentLength(int var1);

void setContentLengthLong(long var1);

void setContentType(String var1);

void setDateHeader(String var1, long var2);

void addDateHeader(String var1, long var2);

void setHeader(String var1, String var2);

void addHeader(String var1, String var2);

void setIntHeader(String var1, int var2);

void addIntHeader(String var1, int var2);
```

响应的状态码

```java
int SC_CONTINUE = 100;
int SC_SWITCHING_PROTOCOLS = 101;
int SC_OK = 200;
int SC_CREATED = 201;
int SC_ACCEPTED = 202;
int SC_NON_AUTHORITATIVE_INFORMATION = 203;
int SC_NO_CONTENT = 204;
int SC_RESET_CONTENT = 205;
int SC_PARTIAL_CONTENT = 206;
int SC_MULTIPLE_CHOICES = 300;
int SC_MOVED_PERMANENTLY = 301;
int SC_MOVED_TEMPORARILY = 302;
int SC_FOUND = 302;
int SC_SEE_OTHER = 303;
int SC_NOT_MODIFIED = 304;
int SC_USE_PROXY = 305;
int SC_TEMPORARY_REDIRECT = 307;
int SC_BAD_REQUEST = 400;
int SC_UNAUTHORIZED = 401;
int SC_PAYMENT_REQUIRED = 402;
int SC_FORBIDDEN = 403;
int SC_NOT_FOUND = 404;
int SC_METHOD_NOT_ALLOWED = 405;
int SC_NOT_ACCEPTABLE = 406;
int SC_PROXY_AUTHENTICATION_REQUIRED = 407;
int SC_REQUEST_TIMEOUT = 408;
int SC_CONFLICT = 409;
int SC_GONE = 410;
int SC_LENGTH_REQUIRED = 411;
int SC_PRECONDITION_FAILED = 412;
int SC_REQUEST_ENTITY_TOO_LARGE = 413;
int SC_REQUEST_URI_TOO_LONG = 414;
int SC_UNSUPPORTED_MEDIA_TYPE = 415;
int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
int SC_EXPECTATION_FAILED = 417;
int SC_INTERNAL_SERVER_ERROR = 500;
int SC_NOT_IMPLEMENTED = 501;
int SC_BAD_GATEWAY = 502;
int SC_SERVICE_UNAVAILABLE = 503;
int SC_GATEWAY_TIMEOUT = 504;
int SC_HTTP_VERSION_NOT_SUPPORTED = 505;
```

#### 2、常见应用

1、向浏览器输出消息

2、下载文件

1. 要获取下载文件的路径
2. 下载的文件名是啥
3. 设置，想办法让浏览器能够支持下载我们需要的东西
4. 获取下载文件的输入流
5. 创建缓冲区
6. 获取OutputStream对象
7. 将FileOutputStream流写入到buffer缓冲区
8. 使用OutputStream将缓冲区中的数据输出到客户端

```java
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //1. 要获取下载文件的路径
    String realPath = "F:\\Java code\\Javaweb\\javaweb-02-servlet\\response\\target\\classes\\1.png";
    System.out.println("要下载的文件的路径："+realPath);
    //2. 下载的文件名是啥
    String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
    //3. 设置，想办法让浏览器能够支持下载我们需要的东西
    resp.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8") );
    //4. 获取下载文件的输入流
    FileInputStream in = new FileInputStream(realPath);
    //5. 创建缓冲区
    int len = 0;
    byte[] buffer = new byte[1024];
    //6. 获取OutputStream对象
    ServletOutputStream out = resp.getOutputStream();
    //7. 将FileOutputStream流写入到buffer缓冲区
    while ((len = in.read(buffer))>0) {
            out.write(buffer,0,len);
    }
    in.close();
    out.close();
}
```

3、验证码功能

验证怎么来的？

- 前端实现
- 后端实现，需要用到java的图片类，生成一个图片

4、**实现重定向（重点）**

一个web资源收到客户端请求后，它会通知客户端去访问另外一个web资源，这个过程叫做重定向。

常见场景：

- 用户登录

  ```java
  public void sendRedirect(String location) throws IOException;
  ```

```java
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    /*
    resp.setHeader("Location","/r/img");
    resp.setStatus(302);
     */
    resp.sendRedirect("/r/img");//重定向
}
```

面试题：重定向和转发的区别？

相同点：

- 页面都会实现跳转

不同点：

- 请求转发的时候，url不会产生变化
- 重定向的时候，url地址栏会发生变化

### 6.7、HttpServletRequest

HttpServletRequest代表客户端的请求，用户通过Http协议访问服务器，HTTP请求中的所有信息被封装到HttpServletRequest，通过这个HttpServletRequest方法，获得客户端的所有信息

```java
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //处理请求
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    System.out.println(username+":"+password);
    //重定向注意路径问题，否则就会404
    resp.sendRedirect("/r/success.jsp");
}
```

#### 1、获取前端 传递的参数

![image-20200630101126220](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200630101126220.png)



#### 2、请求转发

```java
Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    resp.setCharacterEncoding("utf-8");

    String username = req.getParameter("username");
    String password = req.getParameter("pwd");
    String[] hobbies = req.getParameterValues("hobby");
    System.out.println("==============================");
    //后台接收中文乱码问题
    System.out.println(username);
    System.out.println(password);
    System.out.println(Arrays.toString(hobbies));
    System.out.println("==============================");

    //通过请求转发
    //这里的斜杠代表当前web应用
    req.getRequestDispatcher("/success.jsp").forward(req,resp);

}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
}
```

**面试题：重定向和转发的区别？**

相同点：

- 页面都会实现跳转

不同点：

- 请求转发的时候，url不会产生变化：307
- 重定向的时候，url地址栏会发生变化：302

## 7、Cookie、Session

### 7.1、会话

**会话**：用户打开一个浏览器，点击了很多超链接，访问多个web资源，关闭浏览器，这个过程，可以称之为会话。

**有状态会话：**一个同学来过教室，下次再来的时候，我们知道他曾经来过

**你怎么能证明你是学生？**

- 发票：		学校给你发票
- 学校登记：学校标记你来过了

**一个网站，怎么证明你来过**

客户端				服务端

- 服务端给客户端一个信件，客户端下次访问服务端带上信件就可以了（Cookie）
- 服务器登记你来过了，下次你来的时候我来匹配你（Session）

### 7.2、保存会话的两种技术

**Cookie**

- 客户端技术（响应，请求）

**Session**

- 服务器技术，利用这个技术，可以保存用户的会话信息，我们可以把信息或者数据放在Session中

常见场景：网站登录之后，第二次访问直接就上去了。

### 7.3、Cookie

1、从请求中拿到cookie信息

2、服务器响应给客户端cookie

```java 
Cookie[] cookies = req.getCookies();//获取cookie，这里返回数组，说明Cookie可能存在多个
cookie.getName()	//获得cookie中的key
cookie.getValue()	  //获得cookie中的值
new Cookie("lastLoginTime", System.currentTimeMillis()+"");//新建一个cookie
cookie.setMaxAge(24 * 60 * 60);//设置cookie有效期
resp.addCookie(cookie);//响应给客户端一个cookie


```

cookie一般会保存在本地目录下appdata；

一个网站cookie是否存在上限！

- 一个cookie只能保存一个信息；
- 一个web站点可以给浏览器发送多个cookie，最多存放20个cookie
- cookie大小限制4kb
- 300个cookie浏览器上限

删除cookie

- 不设置有效期，关闭浏览器，自动删除
- 设置有效期为0

编码解码

```java
Cookie cookie = new Cookie("name", URLEncoder.encode("含章","utf-8"));
 out.write(URLDecoder.decode(cookie.getValue(), "utf-8"));
```

### 7.4、Session（重点）

什么是session：

- 服务器会给每个用户（浏览器）创建一个session对象
- 一个Session独占一个浏览器，只要浏览器没有关闭，这个Session就存在
- 用户登录之后，整个网站它都可以访问。保存用户的信息



- Cookie是把用户的数据写给用户的浏览器，浏览器保存（可以保存多个）
- Session把用户的数据写到用户独占的Session中，服务器端保存（保存重要信息，减少服务器资源浪费）
- Session对象由服务器创建

使用场景：

- 保存一个登陆用户的信息
- 购物车信息
- 在整个网站中，经常会使用的数据，我们将它保存在Session

使用Session

会话自动过期：web.xml配置

```xml
<session-config>
    <!--1分钟后Session自动失效，以分钟为单位-->
    <session-timeout>1</session-timeout>
</session-config>
```



## 8、JSP

### 8.1、什么是JSP

Java Server Pages：java服务器端页面，也和Servlet用于开放动态web

最大的特点：

- 写JSP就像是在写HTML
- 区别：
  - HTML只给用户提供静态的数据
  - JSP中可以嵌入Java代码，为用户提供动态数据

### 8.2、JSP原理

思路：JSP怎么执行的

- 代码层面没有任何问题
- 服务器内工作：
  - Tomcat中有一个work目录
  - IDEA中使用Tomcat的话，会在IDEA的Tomcat中产生一个work目录

浏览器向服务器发送请求，不管访问什么资源，都是在访问Servlet！

JSP最终也会被转化为一个java类

JSP本质就是一个Servlet

在JSP页面中，只要是java代码，会原封不动地输出

如果是HTML代码，就会被转化

### 8.3、JSP基础语法

任何语言都有自己的语法，Java有，JSP作为Java技术的一种应用，它拥有自己扩充的一些语法，Java所有语法都支持。

#### JSP表达式：

```jsp
<%--JSP表达式 <%= 变量或表达式%>
作用：将程序的输出结果输出到客户端
--%>
<%= new java.util.Date()%>
```

#### JSP脚本片段

```jsp
<%--JSP脚本片段--%>
<%
    int sum = 0;
    for (int i = 0; i < 100; i++) {
        sum+=i;
    }
    out.println("<h1>Sum="+sum+"</h1>");
%>
```

#### 脚本片段的再实现

```jsp
<%
    int x = 10;
    out.print(x);
%>
<p>这是一个JSP文档</p>
<%
    int y = 20;
    out.print(y);
%>
<hr>

<%--在代码中嵌入HTML元素--%>
<%
    for (int i = 0; i < 5; i++) {
%>
<h1>Hello World <%=i%></h1>
<%
    }
%>
```

#### JSP声明

```jsp
<%!
    static{
        System.out.println("Loading Servlet");
    }
    private int globatVar = 0;
    public void theo() {
        System.out.println("进入了方法theo");
    }
%>
```

JSP声明：会被编译到JSP生成的Java类的类中，其他的就会被生成到jspService方法里。

在JSP中嵌入Java代码

总结：

```jsp
<% %>			JSP脚本片段

<%= %>			JSP表达式

<%! %>			JSP声明

<%-- --%>	    JSP的注释（不会在客户端显示）

<!-- -->		HTML的注释
```

### 8.4、JSP指令

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--定制错误页面--%>
<%@ page errorPage="error/500.jsp" %>
<%@ page pageEncoding="UTF-8"%>
```

```jsp
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="common/header.jsp"%>
<h1>网页主体</h1>
<%@ include file="/common/footer.jsp"%>

<hr>
<%--JSP标签--%>
<jsp:include page="/common/header.jsp"/>
<h1>网页主体</h1>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>
```

### 8.5、九大内置对象

- PageContext    存东西
- Request    存东西
- Response    
- Session       存东西
- Application   [ServletContext]：存东西
- config   [ServletConfig]
- out
- page     不用了解
- exception

```jsp
<%
    //保存的数据只在一个页面中有效
    pageContext.setAttribute("name1","含章1号");
    //在一次请求中有效，请求转发会携带这个数据
    request.setAttribute("name2","含章2号");
    //在一次会话中有效，从打开浏览器到关闭浏览器
    session.setAttribute("name3","含章3号");
    //只在服务器中有效，从打开服务器到关闭服务器
    application.setAttribute("name4","含章4号");
%>

```

request：客户端向服务器发送请求，产生的数据，用户看完就没用了

- 新闻

session：客户端向服务器发送请求，产生的数据，用户用完一会还有用

- 购物车

application：客户端向服务器发送请求，产生的数据，一个用户用完，其他用户还可能使用

- 聊天数据

### 8.6、JSP标签、JSTL标签、EL表达式

EL表达式：${}

- 获取数据
- 执行运算
- 获取Web开发的常用对象

Maven导入包

```xml
<dependency>
  <groupId>javax.servlet.jsp.jstl</groupId>
  <artifactId>jstl-api</artifactId>
  <version>1.2</version>
</dependency>
<dependency>
  <groupId>taglibs</groupId>
  <artifactId>standard</artifactId>
  <version>1.1.2</version>
</dependency>
```

JSP标签

```jsp
<%--<jsp:include page="jsptag2.jsp"></jsp:include>--%>
<jsp:forward page="/jsptag2.jsp">
    <jsp:param name="name" value="value1"/>
    <jsp:param name="age" value="12"/>
</jsp:forward>
```

JSTL表达式

JSTL标签库的使用就是为了弥补HTML标签的不足；它自定义了许多标签可以供我们使用，标签的功能和Java代码一样

核心标签（掌握部分）



格式化标签

SQL标签

XML标签

**JSTL标签库使用步骤**

- 引入对应的taglib
- 使用其中的方法
- 在Tomcat也需要映入jstl和standard包



## 9、JavaBean

实体类

JavaBean有特定的写法：

- 必须有一个无参构造
- 属性必须私有化
- 必须有对应的get、set方法

一般用来和数据库的字段做映射：ORM

ORM：对象关系映射

- 表 --- 类
- 字段 ---  属性
- 行记录 --- 对象

| id   | name    | age  | address |
| ---- | ------- | ---- | ------- |
| 1    | 含章1号 | 18   | 地球    |
| 2    | 含章2号 | 59   | 地球    |
| 3    | 含章3号 | 120  | 地球    |

```JAVA
class People{
    private int id;
    private String name;
    private int age;
    private String address;
}
class A{
    new People(1,"含章1号",18,"地球");
    new People(2,"含章2号",59,"地球");
    new People(3,"含章3号",120,"地球");
}
```

## 10、MVC三层架构

什么是MVC：Model	View	Control	模型、视图、控制器

### 10.1、最初

![image-20200630190049212](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200630190049212.png)

用户直接访问控制层，控制层就可以直接操作数据库：

```
servlet--CRUD--数据库
弊端：程序臃肿，不利于维护
Servlet代码中：处理请求、响应、视图跳转、处理JDBC、处理业务代码、处理逻辑代码
架构：没有什么是加一层解决不了的
```

10.2、MVC三层架构

![image-20200630190801412](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200630190801412.png)

Model

- 业务处理：业务逻辑（Service）
- 数据持久层：CRUD（Dao）

View：

- 展示数据
- 提供链接发起Servlet请求（a，form，img ...）

Controller（Servlet）

- 接收用户的请求：（req：请求参数、Session信息 ...）
- 交给业务层处理对应的代码
- 控制视图的跳转

```
登录---接收用户的登录请求---处理用户的请求（获取用户登录的参数：username,password）---交给业务层处理登录业务（判断用户名密码是否正确：事务）---Dao层查询用户名和密码是否正确--数据库
```

## 11、Filter

Filter：过滤器，用来过滤网站的数据；

- 处理中文乱码
- 登录验证

![image-20200630191456835](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200630191456835.png)

Filter开发步骤：

1、导入包

2、编写过滤器

- 导包不要错
- 实现Filter接口，重写对应的方法

```java
public class CharacterEncodingFilter implements Filter {
    //初始化：web服务器启动，就已经初始化了，随时等待过滤对象出现
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter初始化");
    }
    //Chain ：链
    /*
    1、过滤器中所有代码，在过滤特定请求的时候都会执行
    2、必须要让过滤器继续执行
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("CharacterEncodingFilter执行前...");
        //让我们的请求继续走，如果不写，程序到这里就被拦截
        chain.doFilter(request, response);
        System.out.println("CharacterEncodingFilter执行后...");
    }
    //销毁：web服务器关闭的时候，过滤器会销毁
    public void destroy() {
        System.out.println("CharacterEncodingFilter销毁");
    }
}
```

3、在web.xml中配置Filter

```xml
<filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>com.theo.filter.CharacterEncodingFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <!--只要是 /Servlet的任何请求，都会经过这个过滤器-->
    <url-pattern>/servlet/*</url-pattern>
</filter-mapping>
```



## 14、JDBC

什么是JDBC：Java连接数据库

导包，这个包是Java连接数据库用的，不是IDEA连接数据库用的

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.47</version>
</dependency>
```



步骤总结：

1. 加载驱动
2. 连接数据库 DriverManager
3. 获取执行sql的对象 Statement
4. 编写SQL（根据业务，不同的SQL）
5. 执行SQL
6. 获得返回的结果集
7. 释放连接

**事务**

要么都成功，要么都失败

ACID原则：保证数据的安全

```
开启事务
事务提交 commit()
事务回滚 rollback()
关闭事务

转账：
A:1000
B:1000

A(900)  -->100  B(1100)
```

Junit单元测试

依赖

```
dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
</dependency>
```

简单使用

@Test注解只在方法上有效

事务：

```java
package com.theo.test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJdbc3 {
    @Test
    public void test()  {
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf8&useSSL=false";
        String username = "root";
        String password = "theofang";
        Connection conn = null;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //连接数据库，conn代表数据库
            
            conn = DriverManager.getConnection(url, username, password);
            //开启事务
            conn.setAutoCommit(false);

            String sql1 = "update account set money = money-100 where `name`='fang'";
            conn.prepareStatement(sql1).executeUpdate();

            //制造错误
            int i = 1/0;

            String sql2 = "update account set money = money-100 where `name`='theo'";
            conn.prepareStatement(sql2).executeUpdate();
            //以上两条SQL都执行成功则提交事务
            conn.commit();
            System.out.println("success");
        } catch (Exception throwables) {
            try {
                //异常通知回滚
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
        
}
```









