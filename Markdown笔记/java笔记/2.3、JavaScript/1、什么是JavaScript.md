## 1、什么是JavaScript

### 1.1、概述

JavaScript是一门世界上最流行的脚本语言；

Java、JavaScript无关系。

**一个合格的后端人员，必须要精通JavaScript**

### 1.2、历史

https://blog.csdn.net/kese7952/article/details/79357868

**ECMAScript**可以理解为是JavaScript的一个标准

最新版本已经到es6版本，但是大部分浏览器还只停留在es5代码上！（开发环境--线上环境版本不一致问题）

## 2、快速入门

### 2.1、引入JavaScript

1. 内部标签

   ```javascript
   <script>
   	//......
   </script>
   ```

2. 外部引用

   abs.js

   ```
   //...
   ```

   test.html

   ```javascript
   <script src = "abc.js"></script>
   ```

   测试代码

   ```html
   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <title>Title</title>
   
       <!--script标签内，写JavaScript代码-->
       <!--<script>
           alert('hello,world');
       </script>-->
       
       <!--外部引入-->
       <!--注：script：必须成对出现-->
       <script src="js/qj.js"></script>
   
       <!--不用显示定义type，也默认就是JavaScript-->
      <!-- <script type="text/javascript"></script>-->
   </head>
   <body>
   
   </body>
   </html>
   ```

### 2.2、基本语法入门

```html
<!--JavaScript严格区分大小写！-->
<script>
    // 1.定义变量    变量类型    变量名 = 变量值；
    var score = 65;
    var name = "theofang";
    //alert(score);
    // 2、条件控制
    if (score > 60 && score < 70) {
        alert("60~70");
    }else if (score > 70 && score < 80) {
        alert("70~80");
    } else {
        alert("other");
    }
    //console.log(score) 在浏览器的控制台打印变量！ System.out.println()

</script>
```

**浏览器必备调试须知：**

![image-20200618075833338](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200618075833338.png)

![image-20200618075931595](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200618075931595.png)

### 2.3、数据类型

数值，文本，图像，音频，视频...

**变量**

```javascript
var 
```

**number**

js不区分小数和整数，统一使用number

```javascript
123 //整数123
123.1 //浮点数123.1
1.123e3 //科学计数法
-99  //负数
NaN  //not a number
Infinity //无限大
```

**字符串**

'abc' "abc"

**布尔值**

ture，false

**逻辑运算**

```javascript
&&  两个都为真，结果为真
||	一个为真，结果为真
!	真亦假，假亦真
```

**比较运算符**

```javascript
=		赋值
==		等于（类型不一样，值一样，也会判断为true）
===		绝对等于（类型一样，值一样，结果为true）
```

这是一个JS的缺陷，坚持不要使用 == 比较

**须知**

- NaN与所有的数值都不相等，包括自己
- 只能通过isNaN(NaN)来判断这个数是否为NaN

浮点数问题

```javascript
console.log((1 / 3) === (1 - 2 / 3));
```

尽量避免使用浮点数进行运算，存在精度问题。

```javascript
console.log(Math.abs((1/3)-(1-2/3))<0.00000001);
```

**null 和 undefined**

- null 空
- undefined 未定义

**数组**

Java的数组必须是一系列相同类型的对象，JS中不需要这样

```javascript
//保证代码的可读性，尽量使用[]形式
var arr = [1, 2, 3, 4, 5, "hello", null, true];

new Array(1, 12, 3, 4, "hello");
```

取数组下标：如果越界了，就会出现undefined

**对象**

数组是中括号，对象是大括号

>  每个属性之间使用逗号隔开，最后一个不用

```javascript
//Person person = new Person
var person ={
    name: "theofang",
    age:3,
    tags:['js','java','web','...']
}
```

取对象的值

```javascript
person.name
> "theofang"
person.age
> 3
```

### 2.4、严格检查格式

```hmtl
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script>
        /*
        前提：IDEA需要设置支持ES6语法
        严格检查模式：预防JavaScript的随意性产生的一些问题
        ‘use strict'必须写在JavaScript的第一行
        局部变量建议都使用let去生成
         */
        'use strict';
        //全局变量
        let i=1;
        //ES6 中用let定义局部变量
    </script>

</head>
<body>

</body>
</html>
```

## 3、数据类型

### 3.1、字符串

1. 正常字符串我们使用单引号或者双引号包裹

2. 注意使用转义字符  \

   ```bash
   \'
   \n
   \t
   \u4e2d		\u###	unicode字符
   \x41		\ascll字符
   ```

3. 多行字符串编写（倒引号）

   ```javascript
   //tab上面esc键下面
   <script>
   var msg = 
   		`hello
   		world
   		你好
   		ya`
   </script>
   ```

4. 模板字符串

   ```javascript
   <script>
       'use strict';
       let name = "htoe";
       let age = 3;
   
       let msg = `你好，${name}`
       console.log(msg);
   </script>
   ```

5. 字符串长度

   ```javascript
   console.log(student.length);
   ```

6. 字符串的可变性，（不可变）

   ![image-20200618095134589](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200618095134589.png)

7. 大小写转换

   ```javascript
   注意这里是方法而不是属性
   console.log(student.toUpperCase())
   console.log(student.tolowerCase())
   ```

   ![image-20200618095413528](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200618095413528.png)

8. 获取指定下标（student.indexOf("t")）

9. substring

   ```javascript
   [)
   student.substring(1)//从第一个字符串截取到最后一个字符串
   student.substring(1,3)//[1,3) 第一个到第三（包含第一个不包含第三个）
   ```

### 3.2、数组

**Array可以包含任意的数据类型**

```javascript
var arr = [1,2,3,4,5,6,"1","2"]//通过下标取值和赋值
arr[0]
> 1
arr[6]
> "1"
console.log(arr[0])
> 1
console.log(arr[6])
> 1
arr[0] = 1
```

1. 长度

   ```javascript
   arr.length
   ```

注意：假如给arr.length赋值，数组大小就会发生变化，如果赋值过小，元素就会丢失

2. indexOf，通过元素获得下标索引

   ```
   arr.indexOf(1)
   > 0
   arr.indexOf("1")
   > 6
   ```

字符串的 “1”和数字 1 是不同的

3. **slice () 截取 Array的一部分，返回一个新的数组，类似于String中的substring**

   ```javascript
   arr.slice(3)
   > [4,5,6,"1","2"]
   arr.slice(1,5)
   > [2,3,4,5]
   ```

4. **push()，pop()    尾部**

   ```
   push():	压入到尾部
   pop() :	弹出尾部的一个元素
   ```

   

   ![image-20200618103441963](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200618103441963.png)

5. **unshift(),shift()    头部**

   ```
   unshift: 压入到头部
   shift  : 弹出头部的一个元素
   ```

   ![image-20200618104102058](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200618104102058.png)

6. 排序 sort()

   ```javascript
   arr = ["B","C","A"]
   (3) ["B", "C", "A"]
   arr.sort()
   (3) ["A", "B", "C"]
   ```

7. 元素反转

   ```javascript
   (3) ["A", "B", "C"]
   arr.reverse()
   (3) ["C", "B", "A"]
   ```

8. **concat ()**

   ```javascript
   (3) ["C", "B", "A"]
   arr.concat([1,2,3])
   (6) ["C", "B", "A", 1, 2, 3]
   arr
   (3) ["C", "B", "A"]
   ```

注意：concat () 并没有修改数组，只是会返回一个新的数组。

9. 连接符 join

打印拼接数组，使用特定的字符串连接

```javascript
(3) ["C", "B", "A"]
arr.join('-')
"C-B-A"
```

10. 多维数组

    ```javascript
    arr=[[1,2],[3,4],["5","6"]]
    arr[1][1]
    4
    arr[2][0]
    "5"
    ```

数组：存储数据（核心：如何存取。其他方法都可以自己实现）

### 3.3、对象

若干个键值对

```javascript
var 对象名 = {
    属性名：属性值，
    属性名：属性值，
    属性名：属性值
}
//定义了一个person对象，它有四个属性
var person = {
            name: "theofang",
            age:3,
            email: "theofang@qq.com",
            score:100
        }
```

JS中对象，{....}表示一个对象，键值对描述属性 xxxx:xxxx，多个属性之间使用逗号隔开，最后一个属性不加逗号！

**JavaScript中的所有的键（属性）都是字符串，值是任意对象！**

1. 对象赋值

   ```javascript
   person.name
   "theofang"
   person.name="fang"
   "fang"
   person.name
   "fang"
   ```

2. 使用一个不存在的对象属性，不会报错1

   ```javascript
   person.hahaha
   > undefined
   ```

3. 动态地删减属性，通过delete删除对象的属性

   ```javascript
   delete person.name
   true
   person
   {age: 3, email: "theofang@qq.com", score: 100}
   ```

4. 动态地添加，直接给新的属性添加值即可

   ```javascript
   person.haha="haha"
   "haha"
   person
   {age: 3, email: "theofang@qq.com", score: 100, haha: "haha"}
   ```

5. 判断属性值是否在这个对象中！  xxx  in  xxx

   ```javascript
   'age' in person
   true
   //继承
   "toString" in person
   true
   ```

6. 判断一个属性是否是这个对象自身拥有的   hasOwnProperty()

   ```javascript
   person.hasOwnProperty("age")
   true
   person.hasOwnProperty("toString")
   false
   ```

### 3.4、流程控制

if判断

```javascript
<script>
        'use strict';
        let age = 3;
        if (age > 5) { //第一个判断
            alert("haha");
        }else if(age <=5 && age >3) //第二个判断
        {
            alert("hahaha")
        }else {  //否则
            alert("kuwa");
        }
</script>
```

while循环，避免程序死循环

```javascript
<script>
    'use strict';
    let age = 3;
    while (age < 100) {
        age = age + 1;
        console.log(age);
    }
	/*
	do{
		age = age + 1;
		console.log(age)
	}while(age < 100)
</script>
```

for循环

```javascript
for (let i = 0; i < 100; i++) {
    console.log(i);
}
```

**forEach循环**

> 5.1引入

```javascript
let age = [12,2,3,4,455,235,4];
age.forEach(function (value) {
    console.log(value)
});
```

for...in

```javascript
for (var num in age) {
    if (age.hasOwnProperty(num)) {
        console.log("存在")
        console.log(age[num])
    }
}
```

### 3.5、Map 和 Set

> ES6的新特性

Map：

```javascript
//ES6
//查询学生的成绩，学生的名字
//一般方法
//var names = ["tom","jack","haha"];
//var scores = [100,90,80];
let map = new Map([['tom',100],['jack',90],["haha",80]]);
let name = map.get('tom');	//通过key获得value
map.set("admin", 123);		//增加新的key、value
map.delete("tom");			//删除
console.log(name);
```

Set：无序不重复的集合

```javascript
let set = new Set([3,1,2,1,2]);	//set可以去重
set.add(4);						//添加
set.delete(1);					//删除
console.log(set.has(3));		//是否包含某个元素
```

### 3.6、iterator

作业：使用iterator来遍历迭代Map，Set

> ES6的新特性

遍历数组

```javascript
//for of ：遍历值
//for in ：遍历下标
let arr = [3, 4, 5];
for (let x of arr) {
    console.log(x);
}
```

遍历Map

```
let map = new Map([["tom", 100], ["jack", 90], ["haha", 80]]);
for (let x of map) {
    console.log(x);
}
```

遍历Set

```javascript
let set = new Set([5, 6, 7]);
for (let x of set) {
    console.log(x);
}
```

## 4、函数及面向对象

方法：对象（属性，方法）

函数：

### 4.1、函数定义及变量作用域

```java
//java中
public 返回值类型 方法名（）{
	return 返回值：
}
```

绝对值函数

> 定义方式一

```javascript
//JS中
function abs(x){
	if(x >= 0){
		return x;
	}else{
		return -x;
	}
}
```

一旦执行到return代表函数结束，返回结果！

如果没有执行return，函数执行完也会返回结果，结果undefined

> 定义方式二

```javascript
var abs = function(x){
    if(x >= 0){
		return x;
	}else{
		return -x;
	}
}
```

function(x){...}这是一个匿名函数，但是可以把结果赋值给abs，通过abs就可以调用函数！

方式一和方式二等价！

> 调用函数

```
abs(10)		//10
abs(-10)	//10
```

参数问题：JavaScript可以传任意个参数，也可以不传递参数

参数进来是否存在的问题？假设不存在怎么规避？

```javascript
<script>
    function abs(x){
        //手动抛出异常来判断
        if (typeof x !== 'number') {
            throw "Not a Number";
        }
        if(x >= 0){
            return x;
        }else{
            return -x;
        }
    }
</script>
```

> arguments

`arguments`是一个JS免费赠送的关键字；代表传递进来的所有的参数，是一个数组！

```javascript
<script>
    function abs(x){
        console.log("x=>" + x);
        for (let i = 0; i < arguments.length; i++) {
            console.log(arguments[i]);
        }
        if(x >= 0){
            return x;
        }else{
            return -x;
        }
    }
</script>
```

问题：arguments会包含所有的参数，我们有时候想使用多余的参数来进行附加操作，需要排除已有的参数。

> reset

以前：

```javascript
<script>
    function aaa(a, b) {
        console.log("a=>" + a);
        console.log("b=>" + b);
        if (arguments.length > 2) {
            for (let i = 2; i < arguments.length; i++) {

            }
        }
    }
</script>
```

ES6引入的新特性，获取除了已经定义的参数之外的所有参数：...

```javascript
<script>
    function aaa(a, b, ...rest) {
        console.log("a=>" + a);
        console.log("b=>" + b);
        console.log(rest);
    }
</script>
```

rest参数只能写在最后面，必须用 ... 标识

### 4.2、变量的作用域

在JS中，var定义变量实际上是有作用域的。

假设在函数体中声明，则在函数体外不可以使用。（研究闭包）

```javascript
<script>
    'use strict'
    function aj() {
        var x= 1;
        x = x + 1;
    }
    x = x + 2;//Uncaught ReferenceError: x is not defined
</script>
```

如果两个函数使用了相同的变量名，只要在函数内部，就不冲突。

内部函数可以访问外部函数的成员，反之不行。

```javascript
<script>
    'use strict'
    function aj() {
        var x= 1;
        console.log('x ='+x);   //1
        function aj2() {
            var y = x + 1;
            console.log('y ='+y);   //2
        }
        aj2();
    }
    aj();
    var z = y + 2;
    console.log("z =" + z); //Uncaught ReferenceError: y is not defined
</script>
```

假设内部函数变量和外部函数的变量重名

```javascript
<script>
    'use strict'
    function aj() {
        var x= 1;
        function aj2() {
            var x = 'A';
            console.log('inner' + x);//innerA
        }
        console.log('outer' + x);//outer1
        aj2();
    }
    aj();
</script>
```

假设在JavaScript中，函数查找变量从自身函数开始，**由内向外**查找，假设外部存在这个同名的函数变量，则内部函数会屏蔽外部函数的变量。

> 提升变量的作用域

```
function aj(){
	var x = "x" +y;
	console.log(x);
	var y = "y";
}
```

结果：xundefined

说明：JS执行引擎，自动提升了y的声明，但是不会提升变量y的赋值；

这个实在JavaScript建立之初就存在的特性，养成规范：所有的变量定义都放在函数的头部，不要乱放，便于代码维护。

> 全局变量

```javascript
<script>
    'use strict'
    //全局变量
    var x = 1;

    function f() {
        console.log(x);
    }
    f();
    console.log(x);
</script>
```

> 全局对象windows

```javascript
<script>
    'use strict'
    //全局变量
    var x = 1;
    alert(x);
    alert(window.x);
    window.alert(x);
    window.alert(window.x);
</script>
```

alert()这个函数本身也是一个`window`变量。

```javascript
<script>
    'use strict'
    //全局变量
    var x = 123;
    window.alert(x);

    var old_alert = window.alert;

    //old_alert(x);

    window.alert = function () {

    };

    //发现alert()失效了
    window.alert(123);

    //恢复
    window.alert = old_alert;
    window.alert(456);
</script>
```

JavaScript实际上只有一个全局作用域，任何变量（函数也可以视为变量），假设没有在函数作用范围内找到，就会向外查找，如果在全局作用域都没有找到，报错`ReferenceError`

> 规范

由于我们所有的全局变量都会绑定到我们的window上，如果不同的JS文件，使用了相同的全局变量，就会产生冲突——>如何减少冲突？

```javascript
<script>
    'use strict'
    //唯一全局变量
    var TheoFang = {};
    
    //定义全局变量
    TheoFang.name = 'theofang';
    TheoFang.add = function (a, button) {
        return a + b;
    };
</script>
```

把自己的代码全部放入自己定义的唯一空间名字中，降低全局命名冲突的问题。

JQuery

> 局部作用域let

```javascript
<script>
    'use strict'
    function aaa() {
        for (var i = 0; i < 100; i++) {
            console.log(i);
        }
        console.log(i+1)//产生问题：i出了作用域还能使用
    }
</script>
```

ES6  let关键字，解决局部作用域冲突问题！

```javascript
<script>
    'use strict'
    function aaa() {
        for (let i = 0; i < 100; i++) {
            console.log(i);
        }
        console.log(i+1)//Uncaught ReferenceError: i is not defined
    }
</script>
```

建议使用let定义局部作用域的变量；

> 常量 const

在ES6之前，定义常量的方式：只有用全部大写字母命名的变量就是常量；建议不要修改这样的值

```javascript
var PI = '3.14';

console.log(PI);
PI = '213';		//可以改变这个值
console.log(PI);
```

在ES6引入了常量关键字`const`

```javascript
<script>
    'use strict'
    const PI = '3.14';  //只读变量
    console.log(PI);
    PI = 123;         //报错，不允许修改
</script>
```

### 4.3、方法

> 定义方法

方法就是把函数放在对象的里面，对象只有两个东西：属性和方法

```javascript
<script>
    'use strict'
    var theofang = {
        name:'theofang',	//属性
        birth:2020,			//属性
        
        age:function () {	//方法
            //今年-出生的年
            var now = new Date().getFullYear();
            return now-this.birth;
        }
    }
</script>
```

```javascript
//调用
> theofang.age()			//方法一定要带括号()
< 0
```

this代表什么？拆开上面的代码一看：

```javascript
<script>
    'use strict'
    function getAge() {
        //今年-出生的年
        var now = new Date().getFullYear();
        return now-this.birth;
    }
    var theofang = {
        name:'theofang',
        birth:2020,
        age:getAge
    }
</script>
```

```javascript
> theofang.age()			//可以调用（theofang对象调用）
< 0
> getAge()					//不可以调用（window对象不能调用）
< NaN						//不加'use strict'的输出
< Uncaught TypeError		//加了'use strict'报错
```

一般this是无法指向的，默认始终指向调用它的对象

> apply	( 所有的函数都拥有的 )

在JS中可以控制`this`指向！

```javascript
<script>
    'use strict'
    function getAge() {
        //今年-出生的年
        var now = new Date().getFullYear();
        return now-this.birth;
    }
    var theofang = {
        name:'theofang',
        birth:2020,
        age:getAge
    }
    getAge.apply(theofang,[]);//this指向了theofang,参数为空
</script>
```

```javascript
> theofang.age()			//可以调用（theofang对象调用）
< 0
> getAge()					//不可以调用（window对象不能调用）
< NaN						//不加'use strict'的输出
< Uncaught TypeError		//加了'use strict'报错
> getAge.apply(theofang.[]);//可以调用（theofang对象调用）
< 0
```

## 5、常用对象

> 标准对象

```javascript
< typeof 123
> "number"
< typeof '123'
> "string"
< typeof "123"
> "string"
< typeof true
> "boolean"
< typeof NaN
> "number"
< typeof []
> "object"
< typeof {}
> "object"
< typeof Math.abs
> "function"
< typeof undefined
> "undefined"
```

### 5.1、Date

**基本使用**

```javascript
<script>
    let now = new Date();
    now.getFullYear();  //年
    now.getMonth();     //月
    now.getDate();      //日
    now.getHours();     //时
    now.getMinutes();   //分
    now.getSeconds();   //秒
    now.getDay();       //星期几
    now.getTime();      //时间戳（全世界统一：1970/01/01 00:00:00 起到现在的毫秒数）
    console.log(new Date(1592569426335)) //时间戳转为时间
</script>
```

转换

```javascript
> now = new Date(1592569426335)
< Fri Jun 19 2020 20:23:46 GMT+0800 (中国标准时间)
> ow.toLocaleDateString
< ƒ toLocaleDateString() { [native code] }
> now.toLocaleDateString()
< "2020/6/19"
> now.toGMTString()
< "Fri, 19 Jun 2020 12:23:46 GMT"
```

### 5.2、JSON

> JSON 是什么

早期，所有的数据传输习惯使用XML文件！

- `JSON`(JavaScript Object Notation, JS 对象简谱) 是一种轻量级的数据交换格式。
- 简洁和清晰的**层次结构**使得 JSON 成为理想的数据交换语言。
- 易于人阅读和编写，同时也易于机器解析和生成，并有效地提升网络传输效率。

在JavaScript中一切皆为对象，任何JS支持的类型都可以用JSON来表示！

格式

- 对象都用  {}
- 数组都用  []
- 所有的键值对都使用 key:value

JSON和JS对象的转化

```javascript
<script>
    var user ={
        name: "theofang",
        age : 3,
        sex : '男'
    }

    //对象转化为json字符串
    let jsonUser = JSON.stringify(user);

    //json字符串转化为对象，参数为json字符串
    let obj = JSON.parse('{"name": "theofang", "age": 3, "sex": "男"}');
</script>
```

很多人搞不清楚，JSON和JS对象的区别

```javascript
let obj = {a:'hello',b:'hellob'};
let json = '{"a":"hello","b":"hellob"}';
```

### 5.3、Ajax（此处了解）

- 原生的JS写法：xhr异步请求
- jQuery封装好的方法 $("#name").ajax("")
- axios请求

## 6、面向对象编程

### 6.1、什么是面向对象

JavaScript、Java、C#   ...   面向对象；

JavaScript有点区别！

- 类：模板（类是对象的抽象）

- 对象：具体的实例（对象是类的具体表现）

在JavaScript中需要换一下思维方式！

**原型：**

```javascript
<script>
    'use strict'
    let Student = {
        name: "theofang",
        age: 3,
        sex: '男',
        run: function () {
            console.log(this.name + "run ...");
        }
    };

    let xiaoming = {
        name: "xiaoming"
    };
    let Bird = {
        fly: function () {
            console.log(this.name + "fly ...")

        }
    }

    //小明的原型是Student
    xiaoming.__proto__ = Student;   //原型也可以是鸟Bird
</script>
```

> class 继承

`class`关键字实在ES6引入的

1、定义一个类，属性，方法

```javascript
<script>
    /*function Student(name) {
        this.name = name;
    }
    //给Student新增一个方法
    Student.prototype.hello = function () {
        alert("Hello")
    };*/

    //ES6之后
    //定义一个学生的类
    class Student{
        constructor(name) {
            this.name = name;
        }

        hello() {
            alert('hello');
        }
    }
    let xiaoming = new Student('xiaoming');
    let xiaoming = new Student('xiaohong');
</script>
```

2、继承

```javascript
<script>
    class Student{
        constructor(name) {
            this.name = name;   //初始化属性在哪里，这个就相当于初始化属性了嘛
        }

        hello() {
            alert('hello');
        }
    }

    class XiaoStuent extends Student {
        constructor(name, grade) {
            super(name);
            this.grade=grade;
        }

        myGrade() {
            alert('我是一名小学生');
        }
    }
    let xiaoming = new Student('xiaoming');
    let xiaohong = new XiaoStuent('xiaohong');
```

**本质：查看对象原型**

> 原型链

__ proto __:

## 7、操作Bom元素（重点）

> 浏览器介绍

JavaScript和浏览器的关系：

JavaScript诞生就是为了能够让它在浏览器中运行！

BOM：浏览器对象模型

- IE 
- Chrome
- Safari
- FireFox

第三方浏览器

- QQ浏览器
- 360浏览器

> **window对象**

windos代表浏览器窗口

```javascript
> window.alert(1)
< undefined
> window.innerHeight
< 438
> window.innerWidth
< 902
> window.outerHeight
< 819
> window.outerWidth
< 917
```

> Navigator

Navigator封装了浏览器的信息

```javascript
> navigator.appName
< "Netscape"
> navigator.userAgent
< "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36"
> navigator.appVersion
< "5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36"
> navigator.platform
< "Win32"
```

大多数时候，我们不会使用`navigator`对象，因为会被人为修改。

不建议使用这些属性来判断和编写代码

> screen

代表屏幕尺寸

```javascript
> screen.width
< 1536
> screen.height
< 864
```

> location（重要）

代表当前页面的URL信息

```
host:"www.baidu.com"
href:"https://ww.baidu.com/"
protocol:"https"
reload:f reload() //刷新网页（重新加载）
//设置新的地址
location.assign('www.taobao.com')
```

> document

代表当前的页面，HTML DOM文档树

```
> document.title
< "Google"
> document.title='theofang'
< "theofang"
```

获取具体的文档树节点（可以动态地增加和删除节点，即修改网页）

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<dl id="app">
    <dt>Java</dt>
    <dd>JavaSE</dd>
    <dd>JavaEE</dd>
</dl>
<script>
    var dl = document.getElementById('app');
</script>
</body>
</html>
```

获取cookie

```javascript
document.cookie				
```

劫持cookie原理

www.taobao.com

```
<script src = "aa.js"></script>
<!--恶意人员，获取你的cookie上传到他的服务器-->
```

服务器端可以设置cookie为：httpOnly

> history

代表浏览器的历史记录

```javascript
history.back()		//后退
history.forward()	//前进
```

## 8、操作DOM对象（重点）

DOM：文档对象模型

> 核心

浏览器网页就是一个DOM树形结构

- 更新：更新DOM节点
- 遍历DOM节点：得到DOM节点（通过ID）
- 删除：删除一个DOM节点
- 添加：添加一个新的节点

要操作一个DOM节点，就必须要先获得这个DOM节点

>  获得DOM节点

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div id="father">
    <h1>标题</h1>
    <p id="p1">p1</p>
    <p class="p2">p2</p>
</div>
<script>
    //对应css选择器
    var h1 = document.getElementsByTagName('h1');
    var p1 = document.getElementById('p1');
    var p2 = document.getElementsByClassName('p2');
    var father = document.getElementById("father");
    var childrens = father.children;    //获取父节点下所有的子节点

    // father.firstChild
    // father.lastChild
</script>
</body>
</html>
```

这是原生代码，之后使用jQuery

> 更新节点

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<div id="id1">

</div>
<script>
    var id1 = document.getElementById('id1');
    id1.innerText = 'abc';
</script>
</body>
</html>
```

操作文本

- `id1.innerText='353'`修改文本的值
- `id1.innerHTML='<strong>123</strong>'`可以解析HTML文本标签

操作JS

```javascript
id1.style.color = 'red'			//属性使用 字符串 （'')包裹
id1.style.fontSize = '20px'		//下划线转驼峰命名
id1.style.padding = '2em'
```

> 删除节点

删除节点的步骤：先获取**父节点**，然后通过父节点**删除自己**

```javascript
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div id="father">
    <h1>标题</h1>
    <p id="p1">p1</p>
    <p class="p2">p2</p>
</div>
<script>
    //对应css选择器
    var h1 = document.getElementsByTagName('h1');
    var p1 = document.getElementById('p1');
    var p2 = document.getElementsByClassName('p2');
	var father = p1.parentElement;
</script>
</body>
</html>
```

```javascript
father.removeChild(p1) 		//通过父节点father删除p1
							//这种方式只能删除通过id找到的子节点
father.removeChild(father.children[0])	//这种方式可以删除父节点下任意的子节点，需要注意的是：删除是动态的，删除了第一个子节点后，之前的第二个子节点就变成了现在的第一个子节点。
```

> 插入节点

我们获得了某个DOM节点，假如这个DOM节点是空的，我们通过innerHTML就可以增加一个元素，但如果这个DOM节点已经存在元素了，我们就不能这么操作了，会产生覆盖！

追加

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<p id="js">JavaScript</p>
<div id="list">
    <p id="se">JavaSE</p>
    <p id="ee">JavaEE</p>
    <p id="me">JavaME</p>
</div>
<script>
    'use strict'
    let js = document.getElementById('js');		//已经存在的节点
    let list = document.getElementById('list');	//已经存在的节点
</script>
</body>
</html>
```

```javascript
list.appendChild(js);							//追加到后面
```

效果：

![image-20200621092935810](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200621092935810.png)

> 创建一个新的标签，实现插入

```javascript
<script>
    'use strict'
    let js = document.getElementById('js');
    let list = document.getElementById('list');
    //通过JS创建一个新的节点
    let newP = document.createElement('p');//创建一个p标签
    newP.id = 'newP';
    newP.innerText = 'hello,theofang';
    list.appendChild(newP);
    //创建一个标签节点
    let myScript = document.createElement('script');
    myScript.setAttribute('type', 'text/javascript');
    //获得标签并设置
    //let body = document.getElementsByTagName('body');
    // body[0].setAttribute('style', 'background-color:wheat');
    // body[0].style.background = 'red';
    //可以创建一个Style标签
    let myStyle = document.createElement('style');//创建了一个空style标签
    myStyle.setAttribute('type', 'text/css');
    myStyle.innerHTML = 'body{background-color:chartreuse;}';//设置标签内容
    document.getElementsByTagName('head')[0].appendChild(myStyle);
</script>
```

> insert

```javascript
<script>
    'use strict'
    let ee = document.getElementById('ee');
    let js = document.getElementById('js');
    let list = document.getElementById('list');
    //要包含的节点.insertBefore(newNode,targetNode)
    list.insertBefore(js,ee);
</script>
```

## 9、操作表单（验证）

> 表单是什么	form DOM 树

- 文本框	text
- 下拉框    `<select>`
- 单选框    radio
- 多选框    checkbox
- 隐藏域    hidden
- 密码框    password
- ...

表单的目的：提交信息

> 获得要提交的信息

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<form action="#" method="post">
    <p>
        <span>用户名: </span><input type="text" id="username">
    </p>
    <!--多选框的值，就是定义好的value值-->
    <p>
        <span>性别：</span>
        <input type="radio" name="sex" value="man" id="boy">男
        <input type="radio" name="sex" value="women" id="girl">女
    </p>
</form>
<script>
    let input_text = document.getElementById('username');
    let boy_radio = document.getElementById('boy');
    let girl_radio = document.getElementById('girl');
    //得到输入框的值  input_text.value
    //修改输入框的值  input_text.value = '345'
    /*
    对于单选框，多选框等固定的值，用value只能取到当前的值
    boy_radio.value (man)
    girl_radio.value (women)
     */
    /*
    查看返回的结果是否为true，如果为true代表被选中 (boy_radio.checked)
    赋值 (girl_radio.checked = true )
     */
</script>
</body>
</html>
```

> 提交表单，MD5加密，表单优化

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://cdn.bootcss.com/blueimp-md5/2.10.0/js/md5.min.js"></script>
</head>
<body>
<!--表单绑定提交事件
onsubmit = 绑定一个提交检测的函数，true,false
将这个结果返回给表单，使用onsubmit接收
onsubmit = "return aaa()"
-->
    <form action="#" method="post" onsubmit="return aaa()">
    <p>
        <span>用户名: </span><input type="text" id="username" name="username">
    </p>
    <p>
        <span>密码: </span><input type="password" id="input-password">
    </p>
        <input type="hidden" id="md5-password" name="password">
    <!--绑定事件-->
    <!--<button type="submit" onclick="aaa()">提交</button> 用了表单绑定提交事件，这里就不用写onclick事件-->
    <button type="submit">提交</button>
    </form>
    <script>
        function aaa() {
            alert(1);
            let uname = document.getElementById('username');
            let upwd = document.getElementById('input-password');
            let md5pwd = document.getElementById('md5-password');

            //MD5 算法
           md5pwd.value = md5(upwd.value);

            //可以校验判断表单内容，true就是通过提交，false阻止提交
            return true;    //为true时表单绑定提交事件才能提交成功
        }
    </script>
</body>
</html>
```

## 10、jQuery (write less , do more )

JavaScript和jQuery库

jQuery库里面存着大量的JavaScript函数

> 获取jQuery

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!--cdn引入-->
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
</head>
<body>

</body>
</html>
```

**万能公式：$(selector).action()**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!--cdn引入-->
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <!--导入本地下载好的-->
    <!--<script src="lib/jquery-3.5.1.js"></script>-->
</head>
<body>
<!--
公式：$(selector).action
-->
<a href="#" id="test-jquery">点我</a>
<script>
    //选择器就是CSS选择器
    $('#test-jquery').click(function () {
        alert('hello,jQuery');
    });
</script>

</body>
</html>
```

> 选择器

```javascript
//原生JS，选择器少，不好记
//标签
document.getElementsByTagName();
//id
document.getElementById();
//类
document.getElementsByClassName();

//jQuery    css 中的选择器它全部都能用
$('p').click()      //标签选择器
$('#id1').click()    //id选择器
$('class1').click()    //class选择器
```

文档工具站：https://jquery.cuishifeng.cn

> 事件

鼠标事件，键盘事件，其他事件

- mouse
  - mousedown()	**按下**
  - mouseenter()     
  - mouseleave()     离开
  - mousemove()    **移动**
  - mouseout()
  - mouseover()      点击结束
  - mouseup()

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="lib/jquery-3.5.1.js"></script>
    <style>
        #divMove {
            width: 500px;
            height: 500px;
            border: 1px solid red;
        }
    </style>
</head>
<body>
<!--获取鼠标当前的坐标-->
mouse: <span id="mouseMove"></span>
<div id="divMove">
    在这里移动鼠标试试
</div>

<script>
    //当网页元素加载完毕之后，响应事件
   /* $(document).ready(function () {

    })*/
    //简写
    $(function () {
        $('#divMove').mousemove(function (e) {
            $('#mouseMove').text('x : ' + e.pageX + ' y : ' + e.pageY);
        })
    });
</script>
</body>
</html>
```

> 操作DOM

节点文本操作

```
$('#test-ul li[name=python]').text();		//获得值
$('#test-ul li[name=python]').text('123');	//设置值
$('#test-ul').html();						//获得值
$('#test-ul').html('<strong>123</strong>');	//设置值
```

CSS的操作

```javascript
$('#test-ul li[name=python]').css({'color': 'red','background':'yellow'});
$('#test-ul').css('color', 'grey');
```

元素的显示和隐藏：本质 `display:none`

```javascript
$('#test-ul li[name=python]').hide()		//隐藏
$('#test-ul li[name=python]').show()		//显示
$('#test-ul li[name=python]').toggle();		//切换状态，如果显示就隐藏，如果隐藏就显示
```

娱乐测试

```javascript
$(document).width()
$(document).height()
$(window).width()
$(window).height()
```

**未来ajax()；**

```javascript
$('#form').ajax()
$.ajax({ url: "test.html", context: document.body, success: function(){
    $(this).addClass("done");
}});
```



> 学习技巧

1. 如何巩固JS（看jQuery源码）
2. 巩固HTML和CSS（扒网站）





























