## 注销

删除Session，返回登录

登录拦截优化

- 编写过滤器，并注册

```java
public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;

    //过滤器，从Session中获取用户
    User user = (User)request.getSession().getAttribute(Constants.USER_SESSION);

    if (user == null) {
        //已经被移除或者注销或者未登录
        response.sendRedirect("/smbms/error.jsp");
    } else {
        chain.doFilter(req, resp);
    }
}
```

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

## 密码修改

1. 导入前端素材
2. 写项目建议从底层向上写
3. UserDao接口

```java
//修改当前用户密码
public int updatePwd(Connection connection, int id, int password)throws Exception;
```

4. UserDao实现类

```java
//修改当前用户密码
@Override
public int updatePwd(Connection connection, int id, int password) throws Exception {
    PreparedStatement pstm = null;
    int execute = 0;
    if (connection != null) {

        String sql = "update smbms_user set userPassword = ? where id =?";
        Object[] params = {password, id};
        execute = BaseDao.execute(connection, pstm, sql, params);
        BaseDao.closeResource(null, pstm, null);
    }
    return execute;
}
```

5. UserServic 层

```java
//根据用户id修改密码
public boolean updatePwd(int id, int password);
```

6. UserService 实现类

```java
    @Override
    public boolean updatePwd(int id, int password) {

        Connection connection = null;
        boolean flag = false;
        //修改密码
        try {
            connection = BaseDao.getConnection();
            if (userDao.updatePwd(connection, id, password) > 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
    }
        return flag;
}
```



优化密码使用Ajax



## 用户管理：

### 1、获取用户数量

1、UserDao

```java
//查询用户总数（根据用户名或者角色）
public int getUserCount(Connection connection, String username, int userRole) throws Exception;
```

2、UserDaoImpl

```java
@Override
public int getUserCount(Connection connection, String username, int userRole) throws Exception {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    int count = 0;

    if (connection != null) {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole=r.id");

        ArrayList<Object> list = new ArrayList<>();//存放我们的参数

        if (!StringUtils.isNullOrEmpty(username)) {
            sql.append(" and u.userName like ?");
            list.add("%"+username+"%");
        }
        if (userRole > 0) {
            sql.append(" and u.userRole like ?");
            list.add(userRole);
        }

        //list转化为数组
        Object[] params = list.toArray();

        System.out.println(" UserDaoImpl"+sql.toString());

        rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);

        if (rs.next()) {
            count = rs.getInt("count");
        }
        BaseDao.closeResource(null, pstm, rs);
    }
    return count;
}
```

3、UserService

```java
//查询记录数
public int getUserCount(String username,int userRole);
```

4、UserServiceImpl

```
//查询记录数
    @Override
    public int getUserCount(String username, int userRole) {
        int count =0;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            count = userDao.getUserCount(connection, username, userRole);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return count;
    }
```



![image-20200701160153921](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200701160153921.png)

### 2、获取用户列表

1、userDao



2、userDaoImpl



3、userservice



4、userserviceImpl

### 3、获取角色列表

为了职责统一，可以把角色的操作单独放在一个包中，和pojo类对应

- RoleDao
- RoleDaoImpl
- RoleService
- RoleServiceImpl

### 4、用户显示的Servlet

1、获取用户前端的数据（查询）

```java
//查询用户列表
String queryUserName = req.getParameter("queryname");
String temp = req.getParameter("queryUserRole");
String pageIndex = req.getParameter("pageIndex");
int queryUserRole = 0;
```

2、判断请求是否需要执行，看参数的值判断

```java
//获取用户列表
UserService userService = new UserServiceImpl();
List<User> userList = null;
//第一次走这个请求，一定是第一页，页面大小固定
int pageSize = 5;//可以把这个写到配置文件，方便后期修改
int currentPageNo = 1;

if (queryUserName == null) {
    queryUserName = "";
}
if (temp != null && !temp.equals("")) {
    queryUserRole = Integer.parseInt(temp);//给查询赋值
}
if (pageIndex != null) {
    try {
        currentPageNo = Integer.valueOf(pageIndex);
    } catch (NumberFormatException e) {
        resp.sendRedirect("error.jsp");
    }
}
```

3、为了实现分页，需要计算当前页面和总页面，和总页面

```java
//获取用户的总数（分页：上一页，下一页的情况）
int totalCount = userService.getUserCount(queryUserName, queryUserRole);
//总页数支持
PageSupport pageSupport = new PageSupport();
pageSupport.setCurrentPageNo(currentPageNo);
pageSupport.setPageSize(pageSize);
pageSupport.setTotalCount(totalCount);

int totalPageCount = pageSupport.getTotalPageCount();
//int totalPageCount = ((int)(totalCount/pageSize))+1
```

4、获取用户列表展示

```java
//获取用户列表展示
userList = userService.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
req.setAttribute("userList", userList);

RoleServiceImpl roleService = new RoleServiceImpl();
List<Role> roleList = roleService.getRoleList();

req.setAttribute("roleList", roleList);
req.setAttribute("totalCount", totalCount);
req.setAttribute("currentPageNo", currentPageNo);
req.setAttribute("totalPageCount",totalPageCount);
req.setAttribute("queryUserName", queryUserName);
req.setAttribute("queryUserRole", queryUserRole);
```

5、返回前端

```java
//返回前端
try {
    req.getRequestDispatcher("userlist.jsp").forward(req, resp);
} catch (ServletException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```

















