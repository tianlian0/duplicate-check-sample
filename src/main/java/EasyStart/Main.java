package EasyStart;

import cn.textcheck.CheckManager;
import cn.textcheck.engine.report.Reporter;
import cn.textcheck.starter.EasyStarter;

import java.io.File;
import java.util.List;

/**
 * SDK简易启动器使用示例
 * 此应用程序使用了北京芯锋科技有限公司的TextCheck SDK许可软件，该许可软件版权归北京芯锋科技有限公司所有，且其所有权利由北京芯锋科技有限公司保留。许可证密钥的使用应遵守TextCheck软件许可使用协议，否则将违反中华人民共和国和国际版权法以及其他适用法律。
 */
public class Main {
    public static void main(String[] args) throws Exception {
        //获取机器指纹
        System.out.println(CheckManager.INSTANCE.getMachineCode());
        //设置授权许可证（免费获取评估许可证：https://xincheck.com/?id=7）。授权许可证只需要设置1次，整个程序运行周期内均有效
        CheckManager.INSTANCE.setRegCode("muQyymFW0ysAZZhKVOzkh/jbuGMMfBg9IihiT2Fq9xEZxfIA=");

        //简易启动查重任务
        List<Reporter> reporters = EasyStarter.check(new File("待查文件所在的<文件夹>路径（只查一个文件可以是<文件>路径）"), new File("比对库所在的<文件夹>路径"), "保存查重报告的<文件夹>路径");
    }
}
