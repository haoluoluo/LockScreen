package task.job;

import comp.MainWindows;
import enums.CardType;
import enums.UserStatus;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import Config.Config;
import task.SchedulerMain;

/**
 * @author luoluo.hao
 * @create 2022-02-18 14:26
 **/
public class OpenInfoCardJob implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        MainWindows mainWindows = SchedulerMain.main;
        if(Config.getUserStatus() == UserStatus.NORMAL){
            mainWindows.maximize();
        }
    }
}
