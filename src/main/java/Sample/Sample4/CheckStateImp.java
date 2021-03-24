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

    public void taskStart(String uid, List<Paper> toCheckPapers, Context context) {
        System.out.println("task start:" + uid);
    }

    public void taskFinish(String uid, List<Reporter> reporters, List<Paper> failedPapers, Context context) {
        System.out.println("task finish:" + uid);
        try {
            //当全部任务结束后，保存查重报告统计表
            Reporter.saveAsCSV(reporters, context.reportPath + "/统计表.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paperStart(String uid, Paper paper, Context context) {
        System.out.println("paper start:" + uid);
    }

    public void paperSuccess(String uid, Reporter reporter, Context context) {
        System.out.println("paper success:" + uid);
        try {
            //保存两种类型的查重报告
            reporter.saveAsFile(context.reportPath  + "/" + reporter.getPaper().getPayload(String.class) + "-1.html", ReportType.TEXT_WITH_CITATION); //保存全文标红报告
            reporter.saveAsFile(context.reportPath  + "/" + reporter.getPaper().getPayload(String.class) + "-2.html", ReportType.TEXT_WITH_ORIGINAL); //保存原文对照报告
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paperFailed(String uid, Paper paper, int code, Throwable t, Context context) {
        System.out.println("paper failed:" + uid);
        t.printStackTrace();
    }
}
