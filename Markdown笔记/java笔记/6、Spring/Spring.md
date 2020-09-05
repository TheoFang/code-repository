## 1、Spring

### 1.1、简介

- Spring：春天--->给软件行业带来了春天！
- Spring是一个轻量级控制反转(IoC)和面向切面(AOP)的容器框架。
- 2002年首次推出Spring框架的雏形：interface21框架
- *2004年3月24日,Spring*框架即以interface21框架为基础,经过重新设计,发布了1.0正式版。
- Rod Johnson
- Spring理念：使现有的技术更加容易使用，本身是一个大杂烩，整合了现有的技术框架
- SSH：Struct2 + Spring + Hibernate
- SSM:  SpringMvc + Spring + Mybatis

官网：https://spring.io/projects/spring-framework

官方下载地址：https://repo.spring.io/release/org/springframework/spring/

GitHub地址：https://github.com/spring-projects/spring-framework

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.8.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.2.8.RELEASE</version>
</dependency>
```

### 1.2、优点

- Spring是一个开源的免费的框架（容器）
- Spring是一个轻量级的、非入侵式的框架
- **控制翻转（IOC）、面向切面编程（AOP）**
- 支持事务的处理，对框架整合的支持

**Spring就是一个轻量级的控制翻转（IOC）和面向切面编程（AOP）的框架。**

### 1.3、Spring组成

![image-20200904164209446](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200904164209446.png)

### 1.4、扩展

现代化的Java开发，就是基于Spring的开发

![image-20200904164419125](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200904164419125.png)

- Spring Boot
  - 一个快速开发的脚手架
  - 基于Spring Boot可以快速地开发单个微服务
  - 约定大于配置
- Spring Cloud
  - 基于Spring Boot实现的

现在大多数公司都在使用Spring Boot进行快速开发，学习Spring Boot的前提，是要完全掌握Spring和Spring MVC。

**弊端：发展太久之后，违背了原来的理念。配置十分繁琐，人称“配置地狱”**

## 2、IOC理论推导

1. UserDao接口
2. UserDaoImpl实现类
3. UserService业务接口
4. UserServiceImpl业务实现类

在我们之前的业务中，用户的需求 可能会影响我们原来的代码，我们需要根据用户的需求修改源代码。如果程序代码量十分大，修改一次的成本代价十分昂贵。

使用一个Set接口实现，已经发生了革命性的变化！

```java
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
```

- 之前，程序是主动创建对象！控制权在程序猿手上！
- 使用set注入后，程序不再具有主动型，而变成了被动的接收对象

这种思想从本质上解决了问题，程序猿不用再去管理对象的创建了，系统的耦合性大大降低，可以更加专注在业务的实现上。这是IOC的原型。

对比：

![image-20200904203826431](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200904203826431.png)

![image-20200904204002290](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200904204002290.png)

- IOC本质：控制反转IoC(Inversion of Control)，是一种设计思想，DI(依赖注入)是实现IoC的一种方法，也有人认为DI只是IoC的另一种说法。没有IoC的程序中 , 我们使用面向对象编程 , 对象的创建与对象间的依赖关系完全硬编码在程序中，对象的创建由程序自己控制，控制反转后将对象的创建转移给第三方，个人认为所谓控制反转就是：获得依赖对象的方式反转了。

- IoC是Spring框架的核心内容，使用多种方式完美的实现了IOC，可以使用XML配置，也可以使用注解，新版本的Spring也可以零配置实现IOC。
- 采用XML方式配置Bean的时候，Bean的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，Bean的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的。
- **控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式。在Spring中实现控制反转的是IoC容器，其实现方法是依赖注入（Dependency Injection,DI）。**

到了现在 , 我们彻底不用再程序中去改动了 , 要实现不同的操作 , 只需要在xml配置文件中进行修改 , 所谓的IoC,一句话搞定 : 对象由Spring 来创建 , 管理 , 装配 !

## 4、ICO创建对象的方式

1. 使用无参构造创建对象（默认）

2. 使用有参构造创建对象

   - 下标赋值

     ```xml
     <!--第一种：下标赋值-->
     <bean id="user" class="com.theo.pojo.User">
         <constructor-arg index="0" value="theo"/>
     </bean>
     ```

   - 类型创建

     ```xml
     <!--第二种：通过类型创建，不建议使用-->
     <bean id="user" class="com.theo.pojo.User">
         <constructor-arg type="java.lang.String" value="theofang"/>
     </bean>
     ```

   - 参数名

     ```xml
     <!--第三种：直接通过参数名-->
     <bean id="user" class="com.theo.pojo.User">
         <constructor-arg name="name" value="fang"/>
     </bean>
     ```

总结：在配置文件加载的时候，容器中管理的对象就已经初始化了。

## 5、Spring配置

### 5.1、别名（alias）

### 5.2、Bean的配置

### 5.3、import