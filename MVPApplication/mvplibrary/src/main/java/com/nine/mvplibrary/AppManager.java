package com.nine.mvplibrary;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Stack;

/**
 * activity堆栈式管理
 */
public class AppManager {

    private Stack<WeakReference<Activity>> activityStack;
    private static AppManager instance;

    private AppManager() {

    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            synchronized (AppManager.class) {
                instance = new AppManager();
            }
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<WeakReference<Activity>>();
        }
        activityStack.add(new WeakReference<>(activity));
    }

    /**
     * 检查弱引用是否释放，若释放，则从栈中清理掉该元素
     */
    public void checkWeakReference() {
        if (activityStack != null) {
            // 使用迭代器进行安全删除
            for (Iterator<WeakReference<Activity>> it = activityStack.iterator(); it.hasNext(); ) {
                WeakReference<Activity> activityReference = it.next();
                Activity temp = activityReference.get();
                if (temp == null) {
                    it.remove();
                }
            }
        }
    }
    /**
     * 获取当前Activity（栈中最后一个压入的）
     * @return
     */
    public Activity currentActivity() {
        checkWeakReference();
        if (activityStack != null && !activityStack.isEmpty()) {
            return activityStack.lastElement().get();
        }
        return null;
    }
    /**
     * 关闭当前Activity（栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = currentActivity();
        if (activity != null) {
            finishActivity(activity);
        }
    }
    /**
     * 关闭指定的Activity
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && activityStack != null) {
            // 使用迭代器进行安全删除
            for (Iterator<WeakReference<Activity>> it = activityStack.iterator(); it.hasNext(); ) {
                WeakReference<Activity> activityReference = it.next();
                Activity temp = activityReference.get();
                // 清理掉已经释放的activity
                if (temp == null) {
                    it.remove();
                    continue;
                }
                if (temp == activity) {
                    it.remove();
                }
            }
            activity.finish();
        }
    }
    /**
     * 关闭指定类名的所有Activity
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        if (activityStack != null) {
            // 使用迭代器进行安全删除
            for (Iterator<WeakReference<Activity>> it = activityStack.iterator(); it.hasNext(); ) {
                WeakReference<Activity> activityReference = it.next();
                Activity activity = activityReference.get();
                // 清理掉已经释放的activity
                if (activity == null) {
                    it.remove();
                    continue;
                }
                if (activity.getClass().equals(cls)) {
                    it.remove();
                    activity.finish();
                }
            }
        }
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (activityStack != null) {
            for (WeakReference<Activity> activityReference : activityStack) {
                Activity activity = activityReference.get();
                if (activity != null) {
                    activity.finish();
                }
            }
            activityStack.clear();
        }
    }
    /**
     * 退出应用程序
     */
    public void exitApp() {
        try {
            finishAllActivity();
            // 退出JVM,释放所占内存资源,0表示正常退出
            System.exit(0);
            // 从系统中kill掉应用程序
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}