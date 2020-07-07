package Sample3;

import cn.papercheck.algorithm.PaperManager;
import cn.papercheck.algorithm.pojo.CloudPaperLibrary;
import cn.papercheck.algorithm.pojo.Paper;
import cn.papercheck.algorithm.report.DefaultTemplate;
import cn.papercheck.authentication.Auth;

import java.io.File;

/**
 * SDK使用范例（云比对库）
 * 本实例主要演示：使用云对比库的情况下，SDK的调用方式
 */
public class Main3 {

    public static void main(String[] args) throws Exception {
        //获取机器码
        System.out.println(Auth.getMachineCode());
        //设置注册码（免费获取：https://dreamspark.com.cn/blog/?id=7）
        PaperManager.INSTANCE.setRegCode("zmchT4HfrsgnU5QFgOEpdLvCkr8u3EFx2x0Xhr96mXA68Y=");

        //加载、构建比对库
        CloudPaperLibrary paperLibrary = new CloudPaperLibrary(); //使用云比对库
        paperLibrary.setUsername("cloudtest"); //设置云比对库用户名
        paperLibrary.setPassword("123456"); //设置云比对库密码
        paperLibrary.setBaseUrl("www.cnkikin.net"); //设置云比对库域名（不包含http和/）
        paperLibrary.setShop("cloud"); //设置二级域名

        //读取待查重的文件
        Paper toCheckPaper = new Paper(new File("C:\\Users\\admin\\Desktop\\test.docx")); //读取本地文件
        //也可以不从文件中读取，直接使用字符串加载
        //Paper toCheckPaper = new Paper("正文文本 正文文 本正文文本");
        //设置该文件的标题和作者信息。如果不设置 查重时将会报错
        toCheckPaper.setTitle("标题");
        toCheckPaper.setAuthor("作者");

        //构建并启动任务
        PaperManager.INSTANCE
                .getCheckTaskBuilder() //获取查重任务构造器
                .setUid("1") //设置任务id，不同任务的id不应重复。如果不设置将随机生成uuid作为id
                .setCheckState(new CheckStateImp()) //设置回调处理。可参考包中CheckStateImp的实现范例
                .setLibrary(paperLibrary) //设置比对库
                .setToCheckPaper(toCheckPaper) //设置待查Paper
                .setTemplate(new DefaultTemplate()) //设置查重报告样式模板
                .build() //构建任务
                .submit(); //启动任务
    }

}
