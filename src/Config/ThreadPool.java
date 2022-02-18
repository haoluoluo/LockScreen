package Config;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程数工具类
 * 开启线程请调用该类里的方法，别另开线程
 * @author hll
 * @create 2021-12-08 13:43
 **/
public class ThreadPool{

    private volatile static ThreadPoolExecutor threadPool;
    public static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() + 1;
    public static final int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    public static final int KEEP_ALIVE_TIME = 1000;
    public static final int BLOCK_QUEUE_SIZE = 1000;


    /**
     * 获取线程池对象
     *
     * @return 线程
     */
    public static ThreadPoolExecutor getThreadPoolExecutor() {
        if (threadPool == null) {
            synchronized (ThreadPool.class) {
                if (threadPool == null) {
                    threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                            new LinkedBlockingQueue<>(BLOCK_QUEUE_SIZE), new ThreadPoolExecutor.CallerRunsPolicy());
                }
            }
        }
        return threadPool;
    }

    public static void shutDown() {
        if(threadPool != null){
            threadPool.shutdown();
        }
    }
}
