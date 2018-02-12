package com.nine.mvp.model.db;

import com.nine.mvp.app.App;
import com.nine.mvp.config.AppConfig;
import com.nine.mvp.db.DaoMaster;
import com.nine.mvp.db.DaoSession;

import javax.inject.Inject;

/**
 * Created by Just For Mr.Jiu on 18/2/12.
 * Email yufan595@gmail.com
 */

public class GreenDaoHelper implements DBHelper {

    private final DaoSession mDaoSession;

    @Inject
    public GreenDaoHelper() {
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(App.getInstance(),
                AppConfig.DB_NAME, null);
        mDaoSession = new DaoMaster(helper.getWritableDatabase()).newSession();
    }

//    @Override
//    public Observable<Long> insertUser(final User user) {
//        return Observable.fromCallable(new Callable<Long>() {
//            @Override
//            public Long call() throws Exception {
//                return mDaoSession.getUserDao().insert(user);
//            }
//        });
//    }
//
//    @Override
//    public Observable<List<User>> getAllUsers() {
//        return Observable.fromCallable(new Callable<List<User>>() {
//            @Override
//            public List<User> call() throws Exception {
//                return mDaoSession.getUserDao().loadAll();
//            }
//        });
//    }
}
