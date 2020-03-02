package Sample;

import cn.papercheck.algorithm.CheckState;
import cn.papercheck.algorithm.pojo.Paper;
import cn.papercheck.algorithm.report.ReporterCore;

/**
 * CheckState实现范例
 */
public class CheckStateImp implements CheckState {

    /**
     * 开始查重时会调此函数
     *
     * @param uid   查重任务id
     * @param paper 论文
     */
    @Override
    public void start(String uid, Paper paper) {
        System.out.println("start:" + uid);
    }

    /**
     * 查完一篇论文会调此函数
     *
     * @param uid    查重任务id
     * @param paper  论文
     * @param reporter 查重报告
     */
    @Override
    public void finish(String uid, Paper paper, ReporterCore reporter) {
        System.out.println("finish:" + uid);
        reporter.saveAsFile("C:\\Users\\mark_liu\\Desktop\\test1.mht", 1);
        System.out.println("finish1:" + uid);
        reporter.saveAsFile("C:\\Users\\mark_liu\\Desktop\\test2.mht", 2);
        System.out.println("finish2:" + uid);
    }

    /**
     * 论文查重失败后回调
     *
     * @param uid   查重任务id
     * @param paper 失败的论文
     * @param code  错误码
     * @param msg   错误信息
     */
    @Override
    public void fail(String uid, Paper paper, int code, String msg) {
        System.out.println("fail:" + uid + " cdoe:" + code + " msg:" + msg);
    }

}
