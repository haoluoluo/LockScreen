package task.job;

import comp.MainWindows;
import database.Database;
import dto.CardDto;
import dto.CardTypeEnum;
import dto.History;
import enums.CardType;
import enums.UserStatus;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import Config.Config;
import task.SchedulerMain;
import utils.ICUtils;
import utils.ThreadPoolUtils;
import utils.WindowOSUtils;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;


/**
 * @author luoluo.hao
 * @create 2022-02-18 14:26
 **/
public class OpenInfoCardJob implements Job{
    private static final AtomicReference<String> outCard = new AtomicReference<>();
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        MainWindows mainWindows = SchedulerMain.main;
        UserStatus userStatus = Config.getUserStatus();
        switch (userStatus){
            case NORMAL:{
                String id = ICUtils.readIDToNumber();
                if(id.length()>0){
                    CardDto cardDto = Database.cardInfo(id);
                    if(Objects.nonNull(cardDto)){
                        if(ICUtils.authentication()){
                            switch (cardDto.getPermission()){
                                case NORMAL: {
                                    Config.setUserStats(UserStatus.USER_LOGIN_ING);
                                    ThreadPoolUtils.executor(()->{
                                        mainWindows.minimize();
                                        ICUtils.beep();
                                        Database.insertLogin(id);
                                    });
                                    break;
                                }
                                case SUPER : {
                                    Config.setUserStats(UserStatus.ADMIN_LOGIN_ING);
                                }
                            }
                            return;
                        }
                    }
                }
                mainWindows.maximize();
                break;
            }
            case ADMIN_LOGIN_ING:{
                String id = ICUtils.readIDToNumber();
                if(id.length()<=0 ){
                    Config.setUserStats(UserStatus.ADMIN_LOGIN);
                    mainWindows.changeCard(CardType.BACKGROUND);
                    mainWindows.setAlwaysOnTop(false);
                }
                break;
            }
            case USER_LOGIN_ING:{
                String id = ICUtils.readIDToNumber();
                if(id.length() <=0 ){
                    Config.setUserStats(UserStatus.USER_LOGIN);
                }
                break;
            }
            case USER_LOGIN:{
                String id = ICUtils.readIDToNumber();
                if (id.length() > 0) {
                    CardDto cardDto = Database.cardInfo(id);
                    History history = Database.readLoginTime();
                    if(Objects.nonNull(cardDto) && Objects.nonNull(history) &&
                            (cardDto.getPermission()== CardTypeEnum.SUPER ||
                                    (StringUtils.equals(history.getCardId(), id) && StringUtils.equals(history.getCardId(), id)))){
                        outCard.set(id);
                        Config.setUserStats(UserStatus.USER_OUT_ING);
                        ThreadPoolUtils.executor(()->{
                            Database.insertOut(id, history.getInTime());
                            mainWindows.maximize();
                            ICUtils.beep();
                        });
                    }
                } else {
                    mainWindows.minimize();
                }
                break;
            }
            case USER_OUT_ING:{
                String id = ICUtils.readIDToNumber();
                if(id.length()<=0 ){
                    Config.setUserStats(UserStatus.NORMAL);
                    ThreadPoolUtils.executor(()->{
                        mainWindows.setAlwaysOnTop(true);
                        CardDto cardDto = Database.cardInfo(outCard.get());
                        if(Objects.nonNull(cardDto)
                                && cardDto.getPermission() == CardTypeEnum.NORMAL
                                && Boolean.parseBoolean(Config.PROPERTIES.getProperty("outShutdown"))){
                            WindowOSUtils.shutdown();
                        }
                    });

                }
               break;
            }
            case ADMIN_LOGIN:
            case ADMIN_OUT_ING: break;
        }
    }
}
