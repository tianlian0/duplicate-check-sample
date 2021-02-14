package Sample2;

import cn.papercheck.engine.CheckState;
import cn.papercheck.engine.pojo.Paper;
import cn.papercheck.engine.report.ReporterCore;

import java.io.IOException;

/**
 * SDK进阶使用范例：CheckState实现范例
 */
public class CheckStateImp implements CheckState<String> {

    /**
     * 开始查重时回调此函数
     *
     * @param uid   查重任务id
     * @param paper 被查Paper
     */
    public void start(String uid, Paper paper, String info) {
        System.out.println("start:" + uid);
        System.out.println(info); //打印自定义信息
    }

    /**
     * 查重完毕回调此函数
     *
     * @param uid      查重任务id
     * @param paper    被查Paper
     * @param reporter 查重报告
     */
    public void finish(String uid, Paper paper, ReporterCore reporter, String info) {
        System.out.println("finish:" + uid);
        System.out.println(info); //打印自定义信息
        //如果使用本地比对库，可直接保存两种类型的查重报告
        try {
            reporter.saveAsFile("C:\\Users\\admin\\Desktop\\report1.mht", 1); //保存1型查重报告（全文标红）
            reporter.saveAsFile("C:\\Users\\admin\\Desktop\\report2.mht", 2); //保存2型查重报告（原文对照）
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查重失败后回调
     *
     * @param uid   查重任务id
     * @param paper 失败的Paper
     * @param code  错误码
     * @param e     错误信息
     */
    public void fail(String uid, Paper paper, int code, Throwable e, String info) {
        e.printStackTrace();
        System.out.println(info); //打印自定义信息
        System.out.println("fail:" + uid + " cdoe:" + code + " msg:" + e.getMessage());
    }

}
