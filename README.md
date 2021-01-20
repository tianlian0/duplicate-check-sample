#### 关联
查重系统；标书查重；项目申报书查重；论文查重系统；合同查重；文档查重；作业查重；文本去重；

#### 介绍
本项目为TextCheck文本查重SDK的一个使用示例
首先通过maven将本SDK引入到项目中，需要先在repositories中添加以下仓库

```xml
<repository>
    <id>dreamspark</id>
    <name>Dreamspark Public Repository</name>
    <url>https://maven.dreamspark.com.cn/repository/maven-releases/</url>
</repository>
```

然后在dependencies中添加以下依赖。依赖包大小约为55M，需要下载一小会。
```xml
<dependency>
    <groupId>cn.com.dreamspark</groupId>
    <artifactId>duplicate-check</artifactId>
    <version>0.4.0</version>
</dependency>
```

除maven外同样支持Gradle、lvy等，修改对应引入语法即可。如果您希望直接引入jar包，也可以在maven仓库直接下载jar包引入。

#### SDK详细文档
本SDK提供较为详细的开发文档，欢迎各位开发人员使用并提供反馈。  
[SDK详细文档](https://dreamspark.com.cn/blog/?id=16 "SDK详细文档")  

#### 查重报告示例截图
截图1：  
![image](https://github.com/tianlian0/duplicate-check-sample/blob/master/image/pic1.png)  

截图2：  
![image](https://github.com/tianlian0/duplicate-check-sample/blob/master/image/pic2.png)  

截图3：  
![image](https://github.com/tianlian0/duplicate-check-sample/blob/master/image/pic3.png)  

#### 应用场景
SDK应用场景：高校论文查重、项目申报书查重、企业内部文档查重、学生作业查重、文本去重、代码查重等。  
本SDK已用于多个商业项目，包括web网站和客户端程序。商业合作专用微信/QQ：654062779，添加好友烦请备注公司名称，不备注不通过；个人项目合作可备注您的姓氏。可以提供SDK相关技术支持，也可以进行查重系统定制开发相关技术支持，欢迎各企业、高校、机构研发人员合作。非商业合作勿扰。  

#### 软件许可使用协议
下载、使用或购买前，请阅读[软件许可使用协议](https://dreamspark.com.cn/blog/zb_users/upload/2020/11/202011041604469747392115.pdf "软件许可使用协议")  ，一旦您下载、使用或购买本软件，将被视为已经完整阅读并同意遵守《软件许可使用协议》。

#### 关联项目
c#编写的论文查重系统客户端版：https://github.com/tianlian0/paper_checking_system  