package Sample;

import cn.papercheck.algorithm.PaperManager;
import cn.papercheck.algorithm.check.ClauseCheck;
import cn.papercheck.algorithm.pojo.Paper;
import cn.papercheck.algorithm.pojo.PaperLibrary;
import cn.papercheck.algorithm.pojo.PaperLibraryCore;
import cn.papercheck.algorithm.report.DefaultReporter;
import cn.papercheck.algorithm.report.DefaultTemplate;
import cn.papercheck.authentication.Auth;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SDK进阶使用范例
 */
public class Main {

    public static void main(String[] args) throws IOException {
        //获取机器码
        System.out.println(Auth.getMachineCode());
        //设置注册码（免费获取：https://dreamspark.com.cn/blog/?id=7）
        PaperManager.INSTANCE.setRegCode("5LMLeGvAAWXQz8wpYi/HEQtzcsbLHRJx2B6vvqG+xvDfitKHi2zayzfKyuG6kW/3iJvo=");
        //检查注册状态
        System.out.println(PaperManager.INSTANCE.regState());

        //加载、构建论文库
        PaperLibraryCore paperLibrary = new PaperLibrary("C:\\Users\\admin\\Desktop\\library"); //论文库所在文件夹（Paper序列化文件）
        paperLibrary.build(); //构建论文库

        //读取转换待查重的论文
        Paper toCheckPaper = new Paper(new File("C:\\Users\\admin\\Desktop\\test.docx"));

        //构建并启动任务
        PaperManager.INSTANCE
                .getCheckTaskBuilder() //获取构建者
                .setCheckState(new CheckStateImp()) //设置回调处理
                .setUid("1") //设置任务id
                .setLibrary(paperLibrary) //设置论文库
                .setToCheckPaper(toCheckPaper) //设置待查论文
                .setReporter(new DefaultReporter()) //设置自定义的查重报告构造器。如不设置，默认即为DefaultReporter
                .setTemplate(new DefaultTemplate()) //设置查重报告样式模板。如不设置，默认即为DefaultTemplate
                .addCheckCore(new ClauseCheck(0.85)) //添加查重算法
                .build() //构建任务
                .submit(); //启动任务。submit：将任务提交到线程池中，如果线程池繁忙将会排队。start：直接启动任务

    }
}
