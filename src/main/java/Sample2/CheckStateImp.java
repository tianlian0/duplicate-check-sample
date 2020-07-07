package Sample2;

import cn.papercheck.algorithm.CheckState;
import cn.papercheck.algorithm.pojo.Paper;
import cn.papercheck.algorithm.report.CloudReporter;
import cn.papercheck.algorithm.report.ReporterCore;

/**
 * SDK进阶使用范例：CheckState实现范例
 */
public class CheckStateImp implements CheckState {

    /**
     * 开始查重时回调此函数
     *
     * @param uid   查重任务id
     * @param paper 被查Paper
     */
    public void start(String uid, Paper paper) {
        System.out.println("start:" + uid);
    }

    /**
     * 查重完毕回调此函数
     *
     * @param uid      查重任务id
     * @param paper    被查Paper
     * @param reporter 查重报告
     */
    public void finish(String uid, Paper paper, ReporterCore reporter) {
        System.out.println("finish:" + uid);
        //如果使用本地比对库，可直接保存两种类型的查重报告
        reporter.saveAsFile("C:\\Users\\admin\\Desktop\\查重报告1.mht", 1); //保存1型查重报告（全文标红）
        reporter.saveAsFile("C:\\Users\\admin\\Desktop\\查重报告2.mht", 2); //保存2型查重报告（原文对照）
    }

    /**
     * 查重失败后回调
     *
     * @param uid   查重任务id
     * @param paper 失败的Paper
     * @param code  错误码
     * @param e     错误信息
     */
    public void fail(String uid, Paper paper, int code, Exception e) {
        e.printStackTrace();
        System.out.println("fail:" + uid + " cdoe:" + code + " msg:" + e.getMessage());
    }

}
