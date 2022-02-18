package task.job;

import comp.MainWindows;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

/**
 * @author luoluo.hao
 * @create 2022-02-18 14:26
 **/
public class OpenInfoCardJob implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap triggerMap = jobExecutionContext.getTrigger().getJobDataMap();
        WindowsJobDataMap windowsJobDataMap = new WindowsJobDataMap();
        if(triggerMap instanceof WindowsJobDataMap){
            windowsJobDataMap = (WindowsJobDataMap) triggerMap;
        }
        MainWindows mainWindows = windowsJobDataMap.getMainWindows();
        mainWindows.maximize();
    }
}
