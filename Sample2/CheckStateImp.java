package Sample;

import cn.papercheck.algorithm.CheckState;
import cn.papercheck.algorithm.pojo.Paper;
import cn.papercheck.algorithm.report.ReporterCore;

/**
 * CheckState实现范例
 */
public class CheckStateImp implements CheckState {

    /**
     * 开始查重时回调此函数
     *
     * @param uid   查重任务id
     * @param paper 论文
     */
    @Override
    public void start(String uid, Paper paper) {
        System.out.println("start:" + uid);
    }

    /**
     * 查重完毕回调此函数
     *
     * @param uid      查重任务id
     * @param paper    论文
     * @param reporter 查重报告
     */
    @Override
    public void finish(String uid, Paper paper, ReporterCore reporter) {
        System.out.println("finish:" + uid);
        reporter.saveAsFile("C:\\Users\\admin\\Desktop\\查重报告1.mht", 1); //保存查重报告1（全文标红）
        reporter.saveAsFile("C:\\Users\\admin\\Desktop\\查重报告2.mht", 2); //保存查重报告2（原文对照）
    }

    /**
     * 论文查重失败后回调
     *
     * @param uid   查重任务id
     * @param paper 失败的论文
     * @param code  错误码
     * @param e     错误信息
     */
    @Override
    public void fail(String uid, Paper paper, int code, Exception e) {
        e.printStackTrace();
        System.out.println("fail:" + uid + " cdoe:" + code + " msg:" + e.getMessage());
    }

}
