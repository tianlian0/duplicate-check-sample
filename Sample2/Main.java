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
        //设置注册码
        PaperManager.INSTANCE.setRegCode("5LMLeGvAAWXQz8wpYi/HEQtzcsbLHRJx2B6vvqG+xvDfitKHi2zayzfKyuG6kW/3iJvo=");
        //检查注册状态
        System.out.println(PaperManager.INSTANCE.regState());

        //加载、构建论文库
        PaperLibraryCore paperLibrary = new PaperLibrary("C:\\Users\\admin\\Desktop\\library");
        paperLibrary.build(); //构建论文库

        //读取转换待查重的论文
        Paper toCheckPaper = new Paper(new File("C:\\Users\\admin\\Desktop\\test.docx"));

        //构建并启动任务
        PaperManager.INSTANCE
                .getCheckTaskBuilder() //获取构建者
                .setUid("1") //设置任务id
                .setCheckState(new CheckStateImp()) //设置回调处理
                .setToCheckPaper(toCheckPaper) //设置待查论文
                .setLibrary(paperLibrary) //设置论文库
                .setReporter(new DefaultReporter()) //设置自定义的查重报告构造器
                .setTemplate(new DefaultTemplate()) //设置查重报告样式模板
                .addCheckCore(new ClauseCheck()) //添加查重算法
                .build() //构建任务。如果论文库没有build，在这里会自动build
                .submit(); //启动任务。submit：将任务提交到线程池中，如果线程池繁忙将会排队。start：直接启动任务

    }
}
