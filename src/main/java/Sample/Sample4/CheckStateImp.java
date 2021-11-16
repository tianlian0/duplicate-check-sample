package Sample.Sample4;

import cn.textcheck.CheckState;
import cn.textcheck.engine.pojo.Paper;
import cn.textcheck.engine.report.Reporter;
import cn.textcheck.engine.type.ReportType;

import java.io.IOException;
import java.util.List;

/**
 * CheckState实现范例
 */
public class CheckStateImp implements CheckState<Context> {

    /**
     * 查重任务开始前会回调此方法
     *
     * @param uid         任务唯一id
     * @param checkPapers 待查的文本对象列表
     * @param context     上下文信息
     */
    public void taskStart(String uid, List<Paper> checkPapers, Context context) {
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
    public void taskFinish(String uid, List<Paper> checkPapers, List<Reporter> reporters, List<Paper> failedPapers, Context context) {
        System.out.println("task finish:" + uid);
        try {
            //当全部任务结束后，保存查重报告统计表
            Reporter.saveAsCSV(reporters, context.reportPath + "/统计表.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查重任务中的某一篇文本开始查重前时会调此方法
     *
     * @param uid      任务唯一id
     * @param paper    被查Paper
     * @param context  上下文信息
     */
    public void paperStart(String uid, Paper paper, Context context) {
        System.out.println("paper start:" + uid);
    }

    /**
     * 查重任务中的某一篇文本查重成功后会调用此方法
     *
     * @param uid      任务唯一id
     * @param reporter 查重报告
     * @param context  上下文信息
     */
    public void paperSuccess(String uid, Reporter reporter, Context context) {
        System.out.println("paper success:" + uid);
        try {
            //保存两种类型的查重报告
            //通过getPayload方法可以取出查重前保存在Paper对象中的负载信息
            reporter.saveAsFile(context.reportPath  + "/" + reporter.getPaper().getPayload(String.class) + "-1.html", ReportType.TEXT_WITH_CITATION); //保存全文标红报告
            reporter.saveAsFile(context.reportPath  + "/" + reporter.getPaper().getPayload(String.class) + "-2.html", ReportType.TEXT_WITH_ORIGINAL); //保存原文对照报告
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
    public void paperFailed(String uid, Paper paper, int code, Throwable t, Context context) {
        System.out.println("paper failed:" + uid);
        t.printStackTrace();
    }
}
