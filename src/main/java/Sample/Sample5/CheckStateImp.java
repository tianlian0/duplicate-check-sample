package Sample.Sample5;

import cn.textcheck.CheckState;
import cn.textcheck.engine.pojo.Paper;
import cn.textcheck.engine.report.Reporter;
import cn.textcheck.engine.type.ReportType;

import java.io.IOException;
import java.util.List;

/**
 * CheckState实现范例
 */
public class CheckStateImp implements CheckState<String> {

    /**
     * 查重任务开始前会回调此方法
     *
     * @param uid         任务唯一id
     * @param checkPapers 待查的文本对象列表
     * @param context     上下文信息
     */

    public void taskStart(String uid, List<Paper> checkPapers, String context) {
        System.out.println("task start:" + uid);
    }

    /**
     * 查重任务中的全部文本均查重完毕后后回调此方法
     *
     * @param uid          任务唯一id
     * @param checkPapers  待查的文本对象列表
     * @param reporters    查重报告（只有查重成功的文本才会有reporter）
     * @param failedPapers 查重失败的文本对象
     * @param context      上下文信息
     */
    public void taskFinish(String uid, List<Paper> checkPapers, List<Reporter> reporters, List<Paper> failedPapers, String context) {
        System.out.println("task finish:" + uid);
    }

    /**
     * 查重任务中的某一篇文本开始查重前时会调此方法
     *
     * @param uid      任务唯一id
     * @param paper    被查Paper
     * @param context  上下文信息
     */
    public void paperStart(String uid, Paper paper, String context) {
        System.out.println("paper start:" + uid);
    }

    /**
     * 查重任务中的某一篇文本查重成功后会调用此方法
     *
     * @param uid      任务唯一id
     * @param reporter 查重报告
     * @param context  上下文信息
     */
    public void paperSuccess(String uid, Reporter reporter, String context) {
        System.out.println("paper success:" + uid);
        System.out.println(reporter.getReportId()); //打印查重报告id
        System.out.println(reporter.getCopyRate()); //打印总重复率
        System.out.println(reporter.getFrontCopyWords()); //打印前部重复字符数
        try {
            //保存两种类型的查重报告
            reporter.saveAsFile("保存html查重报告的文件路径（例 C:\\Desktop\\report1.html）", ReportType.TEXT_WITH_CITATION); //保存全文标明引文查重报告
            reporter.saveAsFile("保存html查重报告的文件路径（例 C:\\Desktop\\report2.html）", ReportType.TEXT_WITH_ORIGINAL); //保存原文对照查重报告
            reporter.saveAsFile("保存html查重报告的文件路径（例 C:\\Desktop\\report3.html）", ReportType.SAMPLE); //保存原文对照查重报告
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查重任务中的某一篇文本查重失败后会调用此方法
     *
     * @param uid      任务唯一id
     * @param paper    失败的Paper
     * @param code     错误码
     * @param t        错误信息
     * @param context  上下文信息
     */
    public void paperFailed(String uid, Paper paper, int code, Throwable t, String context) {
        System.out.println("paper failed:" + uid);
        t.printStackTrace();
    }
}
