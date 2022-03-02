package enums;

/**
 * @create 2022-02-18 15:20
 **/
public enum UserStatus {
   /**
    * 普通模式，即锁屏模式
    */
   NORMAL,
   /**
    * 登录模式
    */
   ADMIN_LOGIN,

   /**
    * 用户登录模式
    */
   USER_LOGIN, USER_LOGIN_ING, USER_OUT_ING, ADMIN_LOGIN_ING, ADMIN_OUT_ING;
}
