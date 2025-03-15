#### 介绍
本项目可直接运行，用于本地文档、论文、标书等材料查重的测试，白名单功能还可以对标书、论文等材料中允许重复的部分（技术规格说明书、专有名词等）进行排除。  
使用前修改项目中待查文件和论文库的路径即可。通过本项目可以了解TextCheck SDK的基本用法，并对其查重效果、性能进行测试。  
如遇到maven引用拉取失败问题，可以参考[maven拉取失败 issuse](https://github.com/tianlian0/duplicate-check-sample/issues/1 "maven拉取失败issuse")进行解决  

#### 关联
查重系统；标书查重；项目申报书查重；论文查重系统；合同查重；文档查重；作业查重；文本去重

#### 关于XINCheck SDK
SDK提供较为详细的开发文档，欢迎各位开发人员使用并提供反馈。  
[SDK详细教程](https://xincheck.com/?id=16 "SDK使用教程")  

首先通过maven将本SDK引入到项目中，需要先在repositories中添加以下仓库  
```xml
<repository>
    <id>XINCHECK</id>
    <name>XINCHECK Public Repository</name>
    <url>https://maven.xincheck.com/repository/maven-releases/</url>
</repository>
```

然后在dependencies中添加以下依赖。依赖包大小约为30MB，需要下载一小会。
```xml
<dependency>
    <groupId>com.xincheck</groupId>
    <artifactId>duplicate-check</artifactId>
    <version>0.5.14</version>
</dependency>
```
除maven外同样支持Gradle、lvy等，修改对应引入语法即可。如果您希望直接引入jar包，也可以在maven仓库直接下载jar包引入。  
引入SDK后按照示例代码或开发文档中写明的开发方式进行开发即可。  

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
下载、使用前，请阅读[软件许可使用协议](https://xincheck.com/zb_users/upload/2021/06/202106021622642456269325.pdf "软件许可协议")  ，一旦您下载、使用或购买本软件，将被视为已经完整阅读并同意遵守《软件许可协议》。

#### 关联项目
c#编写的论文查重系统客户端版：https://github.com/tianlian0/paper_checking_system  
