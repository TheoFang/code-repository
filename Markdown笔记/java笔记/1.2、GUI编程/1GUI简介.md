# GUI编程

怎么学：

- 这是什么
- 怎么用
- 如何在平时运用

组件

- 窗口
- 弹窗
- 面板
- 文本框
- 列表框
- 按钮
- 图片
- 监听事件
- 鼠标
- 键盘事件

## 1、简介

GUI核心技术：Swing AWT

1. 界面不美观
2. 需要JRE环境

为什么要学习？

1. 可以写出自己心中想要的一些小工具
2. 工作时候，可能需要维护swing界面（概率极小）
3. 了解MVC架构，了解监听

## 2、AWT

### 2.1、AWT（abstrace windows tools)介绍

1. 包含了很多的类和接口！GUI：图形用户界面编程

   Eclipse：Java写的

2. 元素：窗口，按钮，文本框

3. java.awt包

   ![image-20200612080653428](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200612080653428.png)

### 2.2、组件和容器   

#### 1、Frame           

```java
package com.theo.lesson01;

import java.awt.*;

//GUI的第一个界面
public class TestFrame {
    public static void main(String[] args) {

        //Frame对象：JDK，看源码（ctrl+鼠标左键点击类），看类的结构、构造器
        Frame frame = new Frame("我的第一个Java图像界面窗口");//调用Frame(String)构造器

        //需要设置可见性
        frame.setVisible(true);

        //设置窗口大小
        frame.setSize(400, 400);

        //设置背景颜色   Color
        //frame.setBackground(Color.BLACK);
        frame.setBackground(new Color(59, 40, 40));

        //弹出的初始位置(0,0)在左上角
        frame.setLocation(200,200);

        //设置大小固定(默认为ture)
        frame.setResizable(false);
    }
}
```

![运行效果图](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200612084727093.png)

问题：暂时发现窗口无法关闭，终止程序可以关闭

尝试回顾封装：

```java
package com.theo.lesson01;

import java.awt.*;

public class TestFrame02 {
    public static void main(String[] args) {
        //展示多个窗口
        new MyFrame(100,100,200,200,Color.blue);
        new MyFrame(300,100,200,200,Color.yellow);
        new MyFrame(100,300,200,200,Color.gray);
        new MyFrame(300,300,200,200,Color.red);
    }
}

class MyFrame extends Frame {
    static int id = 0;//可能存在多个窗口，需要一个计数器

    public MyFrame(int x, int y, int w, int h,Color color) {
        super("Myframe"+(++id));//调用构造器Frame(String)
        setBackground(color);
        setBounds(x,y,w,h);
        setVisible(true);
    }

}
```

![image-20200612090318474](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200612090318474.png)

#### 2、面板panel

解决了关闭事件！

```java
package com.theo.lesson01;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//Panel 可以看成是一个空间，但是不能单独存在
public class TestPanel {
    public static void main(String[] args) {
        Frame frame = new Frame();

        //布局
        Panel panel = new Panel();

        //设置布局
        frame.setLayout(null);

        //坐标
        frame.setBounds(300, 300, 500, 500);
        frame.setBackground(new Color(45, 213, 34));

        //panel 设置坐标，相对于frame
        panel.setBounds(50, 50, 400, 400);
        panel.setBackground(new Color(239, 6, 30));

        //frame.add(pannel())
        frame.add(panel);

        frame.setVisible(true);

        //监听事件，监听窗口关闭事件 System.exit(0)
        //适配器模式
        frame.addWindowListener(new WindowAdapter() {
            //窗口点击关闭的时候需要做的事情
            @Override
            public void windowClosing(WindowEvent e) {
                //结束程序
                System.exit(0);
            }
        });

    }
}

```

![image-20200612094221630](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200612094221630.png)

#### 3、布局管理器

- 流式布局

  ```java
  jpackage com.theo.lesson01;
  
  import java.awt.*;
  
  public class TestFlowLayout {
      public static void main(String[] args) {
          Frame frame = new Frame();
  
          //组件-按钮
          Button button1 = new Button(&quot;button1&quot;);
          Button button2 = new Button(&quot;button2&quot;);
          Button button3 = new Button(&quot;button3&quot;);
  
          //设置为流式布局
          //frame.setLayout(new FlowLayout());
          //frame.setLayout(new FlowLayout(FlowLayout.LEFT));
          frame.setLayout(new FlowLayout(FlowLayout.RIGHT));
  
          frame.setSize(200, 200);
  
          //把按钮添加上去
          frame.add(button1);
          frame.add(button2);
          frame.add(button3);
          frame.setVisible(true);
      }
  }
  
  ```

  

- 东西南北中

  ```java
  package com.theo.lesson01;
  
  import javax.swing.*;
  import java.awt.*;
  
  public class TestBorderLayout {
      public static void main(String[] args) {
          Frame frame = new Frame("TestBorderLayout");
  
          Button east = new Button("East");
          Button west = new Button("West");
          Button south = new Button("South");
          Button north = new Button("North");
          Button center = new Button("Center");
  
          frame.add(east,BorderLayout.EAST);
          frame.add(west,BorderLayout.WEST);
          frame.add(south,BorderLayout.SOUTH);
          frame.add(north,BorderLayout.NORTH);
          frame.add(center,BorderLayout.CENTER);
  
          frame.setSize(200,200);
          frame.setVisible(true);
      }
  }
  ```

- 表格布局 Grid

  ```java
  package com.theo.lesson01;
  
  import java.awt.*;
  
  public class TestGridLayout {
      public static void main(String[] args) {
          Frame frame = new Frame("TestBorderLayout");
  
          Button btn1 = new Button("btn1");
          Button btn2 = new Button("btn2");
          Button btn3 = new Button("btn3");
          Button btn4 = new Button("btn4");
          Button btn5 = new Button("bt51");
          Button btn6 = new Button("btn6");
          frame.setLayout(new GridLayout(3, 2));
          frame.add(btn1);
          frame.add(btn2);
          frame.add(btn3);
          frame.add(btn4);
          frame.add(btn5);
          frame.add(btn6);
  
          frame.pack();//Java函数；自动布局
          frame.setVisible(true);
      }
  }
  ```

![image-20200612102602854](F:%5C%E6%9C%AC%E6%A1%8C%E9%9D%A2%5CMarkdown%E7%AC%94%E8%AE%B0%5C%E5%9B%BE%E7%89%87%E5%AD%98%E6%94%BE%5Cimage-20200612102602854.png)

## 3、Swing