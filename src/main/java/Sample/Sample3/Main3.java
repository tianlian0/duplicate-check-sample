package Sample.Sample3;

import cn.textcheck.CheckManager;
import cn.textcheck.engine.pojo.LocalPaperLibrary;
import cn.textcheck.engine.pojo.Paper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 多个比对库的纵向查重的实现实例
 *
 * 此应用程序使用了北京芯锋科技有限公司的XINCHECK SDK许可软件，该许可软件版权归北京芯锋科技有限公司所有，且其所有权利由北京芯锋科技有限公司保留。许可证密钥的使用应遵守XINCHECK软件许可使用协议，否则将违反中华人民共和国和国际版权法以及其他适用法律。
 */
public class Main3 {

    public static void main(String[] args) throws Exception {
        //设置授权许可证（免费获取评估许可证：https://xincheck.com/?id=7）
        CheckManager.INSTANCE.setRegCode("muQyymFW0ysAZZhKVOzkh/jbuGMMfBg9IihiT2Fq9xEZxfIA=");

        //通过<文件夹>加载比对库。假设我们有三个不同的比对库
        LocalPaperLibrary paperLibrary1 = LocalPaperLibrary.load(new File("比对库<文件夹>路径，文件夹中存放作为比对库的文件"));
        LocalPaperLibrary paperLibrary2 = LocalPaperLibrary.load(new File("比对库<文件夹>路径，文件夹中存放作为比对库的文件"));
        LocalPaperLibrary paperLibrary3 = LocalPaperLibrary.load(new File("比对库<文件夹>路径，文件夹中存放作为比对库的文件"));

        //通过<文件夹>批量加载带查重的文件。也可以通过for循环读取list
        File[] files = new File("待查重文件所在的<文件夹>路径").listFiles();
        List<Paper> papers = new ArrayList<>();
        for (File file : files) {
            Paper paper = Paper.load(file);
            paper.setPayload(file.getName()); //将文件名存入待查paper的payload，保存查重报告时将使用。
            papers.add(paper);
        }

        //实例化一个用于保存上下文的实例
        Context context = new Context();
        context.reportPath = "查重报告保存的<文件夹>路径（例D:\\Report）";

        //构建并启动任务
        CheckManager.INSTANCE
                .getCheckTaskBuilder() //获取查重任务构造器
                .setUid("1") //设置任务id
                .addCheckState(new CheckStateImp(), context) //添加回调处理并传递上下文
                .addLibrary(paperLibrary1) //添加比对库。假设本次查重只需要用到1和3两个比对库
                .addLibrary(paperLibrary3)
                .addCheckPaper(papers) //添加待查Paper
                .build() //构建任务
                .submit(); //启动任务。submit：将任务提交到线程池中，如果线程池繁忙将会排队。start：直接启动任务

    }

}
