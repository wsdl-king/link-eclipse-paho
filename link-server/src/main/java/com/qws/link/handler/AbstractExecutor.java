package com.qws.link.handler;

/**
 * @author qiwenshuai
 * @note
 * @since 18-12-19 11:06 by jdk 1.8
 */
public abstract class AbstractExecutor implements Runnable {


    @Override
    public void run() {

        this.action();

    }


    public abstract void action();
}
