package com.qws.link.handler.manager;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author qiwenshuai
 * @note 报文解析管理者
 * @since 18-12-17 11:35 by jdk 1.8
 */
public class LinkDispatchManager {


    private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 20, 2, TimeUnit.SECONDS, new ArrayBlockingQueue(1024));


    public void addRunnable(Runnable runnable) {

        pool.execute(runnable);
    }


    private LinkDispatchManager() {

    }

    public static LinkDispatchManager getInstance() {
        return Singleton.dispatch;
    }


    private static class Singleton {
        private static final LinkDispatchManager dispatch = new LinkDispatchManager();
    }


}
