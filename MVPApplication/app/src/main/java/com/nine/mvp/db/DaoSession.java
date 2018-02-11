package com.nine.mvp.db;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig pickHistroyDaoConfig;

    private final PickHistroyDao pickHistroyDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        pickHistroyDaoConfig = daoConfigMap.get(PickHistroyDao.class).clone();
        pickHistroyDaoConfig.initIdentityScope(type);

        pickHistroyDao = new PickHistroyDao(pickHistroyDaoConfig, this);

        registerDao(PickHistroy.class, pickHistroyDao);
    }
    
    public void clear() {
        pickHistroyDaoConfig.clearIdentityScope();
    }

    public PickHistroyDao getPickHistroyDao() {
        return pickHistroyDao;
    }

}