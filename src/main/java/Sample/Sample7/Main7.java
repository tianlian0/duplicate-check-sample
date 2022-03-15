package Sample.Sample7;

import cn.textcheck.CheckManager;
import cn.textcheck.engine.config.Config;
import cn.textcheck.engine.pojo.SogouPaperLibrary;
import cn.textcheck.engine.algorithm.SearchEngineCheck;
import cn.textcheck.engine.pojo.Paper;

import java.io.File;
import java.util.List;

/**
 * SDK进阶使用范例
 * 使用搜索引擎进行查重
 *
 * 此应用程序使用了北京芯锋科技有限公司的XINCHECK SDK许可软件，该许可软件版权归北京芯锋科技有限公司所有，且其所有权利由北京芯锋科技有限公司保留。许可证密钥的使用应遵守XINCHECK软件许可使用协议，否则将违反中华人民共和国和国际版权法以及其他适用法律。
 */
public class Main7 {

    public static void main(String[] args) throws Exception {
        //获取机器指纹
        System.out.println(CheckManager.INSTANCE.getMachineCode());
        //设置授权许可证（免费获取评估许可证：https://xincheck.com/?id=7）。授权许可证只需要设置1次，整个程序运行周期内均有效
        CheckManager.INSTANCE.setRegCode("muQyymFW0ysAZZhKVOzkh/jbuGMMfBg9IihiT2Fq9xEZxfIA=");

        //通过<文件夹>加载比对库
        SogouPaperLibrary paperLibrary = new SogouPaperLibrary();

        //通过<文件夹>批量加载带查重的文件。也可以通过for循环读取list
        File[] files = new File("待查文件所在的<文件夹>路径").listFiles();
        List<Paper> papers = Paper.load(files);

        //构建并启动任务
        CheckManager.INSTANCE
                .getCheckTaskBuilder() //获取查重任务构造器
                .setUid("1") //设置任务id，不同任务的id不应重复。如果不设置将随机生成uuid作为id
                .addCheckState(new CheckStateImp(), "上下文") //添加回调处理并传递上下文。上下文可以是一个自定义的类，这里以String为例
                .addLibrary(paperLibrary) //添加比对库，可以多次添加
                .addCheckPaper(papers) //添加待查Paper，可以多次添加
                .addCheckCore(new SearchEngineCheck())
                .build() //构建任务
                .submit(); //启动任务。submit：将任务提交到线程池中，如果线程池繁忙将会排队。start：直接启动任务

    }

}

