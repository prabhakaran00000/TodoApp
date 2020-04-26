package com.phablecare.demoapp.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.phablecare.demoapp.data.db.UserDao;
import com.phablecare.demoapp.helper.SingleLiveEvent;
import com.phablecare.demoapp.model.UserDetail;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;


public class UserListRepository {

    private Context mContext;
    private UserDao mUserDao;
    private LiveData<List<UserDetail>> mAllUserDetials;
    private Executor mDatabaseReadWriteExecutor;
    private SingleLiveEvent<Boolean> mAddUpdateRemoveStatus;

    @Inject
    public UserListRepository(Context context, UserDao userDao, Executor databaseReadWriteExecutor,
                              SingleLiveEvent<Boolean> addUpdateRemoveStatus) {
        mContext = context;
        mUserDao = userDao;
        mDatabaseReadWriteExecutor = databaseReadWriteExecutor;
        mAddUpdateRemoveStatus = addUpdateRemoveStatus;
    }
    public void updateUserDetail(final UserDetail userDetail){
       mDatabaseReadWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mUserDao.updateUserDetail(userDetail);
                mAddUpdateRemoveStatus.postValue(true);
            }
        });
    }

    public void deleteUserDetail(final UserDetail userDetail){
        mDatabaseReadWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mUserDao.deleteUserDetail(userDetail);
                mAddUpdateRemoveStatus.postValue(true);
            }
        });
    }

    public void insertUserDetail(final UserDetail userDetail){
        mDatabaseReadWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mUserDao.insertUserDetail(userDetail);
                mAddUpdateRemoveStatus.postValue(true);
            }
        });
    }

    public LiveData<List<UserDetail>> getAllUserDetials(){
        return mUserDao.getAllUserDetails();
    }

    public void fetchUserDetails(){
        mDatabaseReadWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mAllUserDetials = mUserDao.getAllUserDetails();
            }
        });
    }

    public SingleLiveEvent<Boolean> getAddUpdateRemoveStatus(){
        return mAddUpdateRemoveStatus;
    }

}
