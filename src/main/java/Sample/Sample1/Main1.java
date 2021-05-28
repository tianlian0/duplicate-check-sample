package Sample.Sample1;

import cn.textcheck.CheckManager;
import cn.textcheck.engine.algorithm.ContinuityCheck;
import cn.textcheck.engine.checker.CheckTask;
import cn.textcheck.engine.pojo.LocalPaperLibrary;
import cn.textcheck.engine.pojo.Paper;
import cn.textcheck.engine.type.CheckLevel;
import cn.textcheck.engine.type.ReportType;

import java.io.File;

/**
 * SDK同步调用使用范例
 *
 * 此应用程序使用了北京芯锋科技有限公司的TextCheck SDK许可软件，该许可软件版权归北京芯锋科技有限公司所有，且其所有权利由北京芯锋科技有限公司保留。许可证密钥的使用应遵守TextCheck软件许可使用协议，否则将违反中华人民共和国和国际版权法以及其他适用法律。
 */
public class Main1 {
    public static void main(String[] args) throws Exception {
        //获取机器指纹
        System.out.println(CheckManager.INSTANCE.getMachineCode());
        //设置授权许可证（免费获取评估许可证：https://xincheck.com/?id=7）。授权许可证只需要设置1次，整个程序运行周期内均有效
        CheckManager.INSTANCE.setRegCode("muQyymFW0ysAZZhKVOzkh/jbuGMMfBg9IihiT2Fq9xEZxfIA=");

        //通过<文件夹>加载本地比对库（支持pdf、txt、doc、docx）
        LocalPaperLibrary paperLibrary = LocalPaperLibrary.load(new File("比对库文件所在的<文件夹>路径"));//初始化对比库对象

        //读取待查重的<文件>（支持pdf、txt、doc、docx）
        Paper paper = Paper.load(new File("待查文件的<文件>路径")); //读取本地<文件>

        //注意：待查文本和比对库中的文本如果完全相同，将会自动跳过，不进行查重比对。测试时请不要使用完全相同的两个文本进行查重。

        //构建并启动任务
        CheckTask checkTask = CheckManager.INSTANCE
                .getCheckTaskBuilder() //获取查重任务构造器
                .addLibrary(paperLibrary) //添加比对库。可以添加多个
                .addCheckPaper(paper) //添加待查Paper。可以添加多个
                .addCheckCore(new ContinuityCheck(CheckLevel.STRICT))
                .build(); //构建任务，返回checkTask对象
        checkTask.start(); //启动任务
        checkTask.join(); //等待查重结束（阻塞）

        //保存查重报告
        checkTask.getReporters().get(0).saveAsFile("C:\\Report\\report1.html", ReportType.TEXT_WITH_CITATION); //保存查重报告（全文标红）
        checkTask.getReporters().get(0).saveAsFile("C:\\Report\\report2.html", ReportType.TEXT_WITH_ORIGINAL); //保存查重报告（原文对照）
    }
}
