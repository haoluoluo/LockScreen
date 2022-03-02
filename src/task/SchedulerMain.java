package task;

import Config.ThreadPool;
import comp.MainWindows;
import org.quartz.*;
import task.job.*;
import org.quartz.impl.StdSchedulerFactory;


/**
 * @author luoluo.hao
 * @create 2022-02-18 14:50
 **/
public class SchedulerMain {

    public static MainWindows main;

    private static Scheduler scheduler;

    private static JobDetail infoCardJob;


    public static void run(MainWindows mainWindows)  {
        main = mainWindows;

        infoCardJob = JobBuilder.newJob(OpenInfoCardJob.class)
                .withIdentity("infoCard", "group1").build();


        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("oneSec", "group")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                .build();
        try {
            Scheduler s = getScheduler();
            s.start();
            s.scheduleJob(infoCardJob, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    public static void shutdown() throws SchedulerException {
        scheduler.shutdown();
    }
    public static Scheduler getScheduler(){
        if (scheduler == null) {
            synchronized (ThreadPool.class) {
                if (scheduler == null) {
                    try {
                        scheduler = new StdSchedulerFactory().getScheduler();
                    } catch (SchedulerException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return scheduler;
    }

    public static void pauseInfo() {
        try {
            pauseJob(infoCardJob.getKey());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    public static void resumeInfo() {
        try {
            resumeJob(infoCardJob.getKey());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void pauseJob(JobKey jobKey) throws SchedulerException {
        getScheduler().pauseJob(jobKey);
    }
    public static void resumeJob(JobKey jobKey) throws SchedulerException {
        getScheduler().resumeJob(jobKey);
    }
}
