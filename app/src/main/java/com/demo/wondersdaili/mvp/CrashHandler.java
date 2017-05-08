package com.demo.wondersdaili.mvp;

import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by daili on 2017/5/8.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final Object lock = new Object();
    private static CrashHandler mCrashHandler;

    public static CrashHandler getInstance(){
        synchronized (lock) {
            if (mCrashHandler == null) {
                synchronized (lock) {
                    if (mCrashHandler == null) {
                        mCrashHandler = new CrashHandler();
                    }
                }
            }
            return mCrashHandler;
        }
    }

    /* 初始化 */
    public void init(){
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, final Throwable ex) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(App.getApplication(),"程序发生了点小意外，即将关闭... "+ ex.getMessage(),Toast.LENGTH_LONG).show();
                Log.e("Mvp.ex",ex.getMessage());
                Looper.loop();
                try{
                    SystemClock.sleep(1000);
                }catch (Exception e){
                    Log.e("Mvp.e",e.getMessage());
                }
                // 退出程序
                android.os.Process.killProcess(android.os.Process.myPid());
                // 关闭虚拟机，彻底释放内存空间
                System.exit(1);
            }
        }).start();

    }
}
