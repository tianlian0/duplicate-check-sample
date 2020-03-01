package Sample;

import cn.papercheck.algorithm.PaperManager;
import cn.papercheck.algorithm.check.ClauseCheck;
import cn.papercheck.algorithm.pojo.Paper;
import cn.papercheck.algorithm.pojo.PaperLibrary;
import cn.papercheck.algorithm.pojo.PaperLibraryCore;
import cn.papercheck.algorithm.report.DefaultReporter;
import cn.papercheck.algorithm.report.DefaultTemplate;
import cn.papercheck.authentication.Auth;

import java.io.IOException;

/**
 * JDK使用范例
 */
public class Main {

    public static void main(String[] args) {
        //获取机器码
        System.out.println(Auth.getMachineCode());
        //设置注册码（获取注册码请联系QQ/微信654062779）
        PaperManager.INSTANCE.setRegCode("IdveqDt7rGSyzOIf4uZ7JwP3O9dbIZXe3HmfIAWg+uERyV2eWVxfIdHAah9gdAkEQmFDNnwY9n5aV0IYZNJxqcZGrl6quv4fwi0ezlNHoZQJ9Hs9wsrnrjw7nYUYphbdaDNrSP3ahTuRJLisBEafexNMIF/K7lHwwogFzhuMYzoO69RFk70x8REk3XRfUMkhUyfX4sNhODhKilW7uABJ+KLQYd2sivFsS7Qk5xi1fD7wOG7zFDT0qQpUZCYSZKXj5++GgBhbvDvMss2WOEuuE6diz/IqnHYrKjTDLreJJTdl4XWoAiYUfoqSqPdQB9hIkIqWHdgDNxD3+fFS8ZMPCFgyYqh/oXWQE0/8yzSgX4Bn9ttXdK8xTq7XN5bMenijlW+oV0F3gAYXUe5q2HQFuuccVK0AZ2cwOxjon8r2qTICm6yZo/muOHiXY95p9qD1HRbSmo8PTqhtRGrguU+w6wXcCmP2aA4UrC4bIQZ6scs=");
        //检查注册状态
        System.out.println(PaperManager.INSTANCE.regState());

        //SDK初始化
        PaperManager.INSTANCE.init(2);

        //构建论文库
        PaperLibraryCore paperLibrary = new PaperLibrary();
        try {
            paperLibrary.loadByFolderPath("C:\\Users\\mark_liu\\Desktop\\test2", false) //加载
                    .build(); //构建
        } catch (IOException e) {
            e.printStackTrace();
        }

        //将一篇论文转换为Paper，并保存到文件。（支持docx、doc、pdf、rtf、txt）
        try {
            Paper paper = PaperManager.INSTANCE.ConvertFileToPaper("C:\\Users\\mark_liu\\Desktop\\test.docx");
            paper.setId("001").setTitle("论文标题").setAuthor("论文作者").setYear("论文年份").setSource("论文来源");
            paper.saveAsFile("C:\\Users\\mark_liu\\Desktop\\test.paper");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //读取转换待查重的论文
        Paper toCheckPaper = new Paper();
        try {
            toCheckPaper.loadByPath("C:\\Users\\mark_liu\\Desktop\\test.paper");
            toCheckPaper.loadByText("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //构建并启动任务
        PaperManager.INSTANCE
                .getCheckTaskBuilder() //获取构建者
                .setUid("1") //设置任务id
                .setCheckState(new CheckStateImp()) //设置回调处理
                .setToCheckPaper(toCheckPaper) //设置待查论文
                .setLibrary(paperLibrary) //设置论文库
                .setThreshold(12) //设置阈值
                .setReporter(new DefaultReporter()) //设置自定义的查重报告构造器
                .setTemplate(new DefaultTemplate()) //设置查重报告样式模板
                .addCheckCore(new ClauseCheck()) //添加查重算法
                .build() //构建任务。如果论文库没有build，在这里会自动build。
                .submit(); //启动任务。submit：任务数超限将会排队。start：无视排队直接启动

        //关闭线程池
        PaperManager.INSTANCE.shutdown();
    }
}
