package utils;


import Config.ThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程数工具类
 * 开启线程请调用该类里的方法，别另开线程
 * @author hll
 * @create 2021-12-08 13:43
 **/
public class ThreadPoolUtils {


    public static void executor(Runnable runnable) {
        getThreadPoolExecutor().execute(runnable);
    }

    public static <T> Future<T> submit(Callable<T> callable) {
        return getThreadPoolExecutor().submit(callable);
    }
    public static void shutDown() {
       ThreadPool.shutDown();
    }
    /**
     * 获取线程池对象
     *
     * @return 线程
     */
    static ThreadPoolExecutor getThreadPoolExecutor(){
        return ThreadPool.getThreadPoolExecutor();
    }
}
