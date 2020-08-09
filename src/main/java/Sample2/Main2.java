package Sample2;

import cn.papercheck.engine.CheckManager;
import cn.papercheck.engine.algorithm.ContinuityCheck;
import cn.papercheck.engine.pojo.LocalPaperLibrary;
import cn.papercheck.engine.pojo.Paper;
import cn.papercheck.engine.report.DefaultTemplate;

import java.io.File;

/**
 * SDK进阶使用范例
 * 本实例主要演示：使用本地库的情况下，SDK进阶的使用方式，让您更大限度的发挥SDK的高性能和高灵活性。
 */
public class Main2 {

    public static void main(String[] args) throws Exception {
        //获取机器码
        System.out.println(CheckManager.INSTANCE.getMachineCode());
        //设置注册码（免费获取：https://dreamspark.com.cn/blog/?id=7）
        CheckManager.INSTANCE.setRegCode("muQyymFW0ysAZZhKVOzkh/jbuGMMfBg9IihiT2Fq9xEZxfIA=");
        //检查注册状态
        System.out.println(CheckManager.INSTANCE.regState());

        //加载比对库（如果使用本地比对库）
        LocalPaperLibrary paperLibrary = new LocalPaperLibrary("C:\\Users\\admin\\Desktop\\Library"); //比对库所在文件夹。如果文件夹中存放的是Paper的序列化对象文件，则加载过程将更快速
        //如果想设置对比库中文件的标题、作者、来源、年份四项信息有多种方法
        //1、如果不设置，将默认以文件名作为标题，其它信息为空
        //2、如果文件名符合以下两个规则，默认直接从文件名中读取四项信息
        //①文件名中四项信息依次用#号隔开。例：标题#作者#论文对比库#2018.docx
        //②文件名中四项信息依次用#号隔开，但第一项为自定义的id。例：001#标题#作者#论文对比库#2018.pdf
        //3、在代码中设置
        //Paper paper = new Paper(new File("文件路径")); //首先从本地文件中加载Paper对象
        //paper.setAuthor("作者").setTitle("标题").setSource("默认对比库").setYear("2018"); //设置各项属性
        //paperLibrary.addByPaper(paper); //添加到论文库
        paperLibrary.build(); //构建比对库

        //读取待查重的文件
        Paper toCheckPaper = new Paper(new File("C:\\Users\\admin\\Desktop\\1.docx")); //读取本地文件
        //也可以不从文件中读取，直接使用字符串加载
        //Paper toCheckPaper = new Paper("正文文本 正文文 本正文文本");
        //设置带查重文本的标题、作者、来源、年份四项信息。设置规则和加载论文库时一致
        toCheckPaper.setAuthor("作者").setTitle("标题").setSource("用户上传").setYear("2018"); //如果不设置则默认标题为文件名，其余项为空

        //构建并启动任务
        CheckManager.INSTANCE
                .getCheckTaskBuilder() //获取查重任务构造器
                .setUid("1") //设置任务id，不同任务的id不应重复。如果不设置将随机生成uuid作为id
                .setCheckState(new CheckStateImp(), "test info") //设置回调处理并传递自定义信息。可参考包中CheckStateImp的实现范例。
                .setLibrary(paperLibrary) //设置比对库
                .setToCheckPaper(toCheckPaper) //设置待查Paper
                .setTemplate(new DefaultTemplate()) //设置查重报告样式模板。如不设置，默认即为DefaultTemplate
                .addCheckCore(new ContinuityCheck(12)) //指定ContinuityCheck作为查重算法。如不指定则会自动使用ClauseCheck(0.85)+ContinuityCheck(12)，查重算法的选择与区别参见文档说明
                .build() //构建任务
                .submit(); //启动任务。submit：将任务提交到线程池中，如果线程池繁忙将会排队。start：直接启动任务

        //CheckManager.INSTANCE.shutdown(); //全部任务结束后关闭线程池，优雅退出。服务器程序通常不执行该方法

    }

}
