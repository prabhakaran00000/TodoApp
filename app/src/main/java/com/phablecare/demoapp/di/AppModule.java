package com.phablecare.demoapp.di;

import android.app.Application;
import android.content.Context;

import com.phablecare.demoapp.data.db.UserDao;
import com.phablecare.demoapp.data.db.UserDetailRoomDatabase;
import com.phablecare.demoapp.helper.SingleLiveEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/*This modules provides dependencies for HomeViewModel, UserListRepository and UserDetailRoomDatabase*/

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    UserDetailRoomDatabase provideDb(Context context){
        return UserDetailRoomDatabase.getDatabase(context);
    }

    @Provides
    UserDao provideUserDao(UserDetailRoomDatabase database){
        return database.userDao();
    }

    @Provides
    Executor provideExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    SingleLiveEvent<Boolean> provideSingleLiveEvent(){
        return new SingleLiveEvent<Boolean>();
    }
}
