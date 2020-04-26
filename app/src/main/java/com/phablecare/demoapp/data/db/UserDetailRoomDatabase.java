package com.phablecare.demoapp.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.phablecare.demoapp.helper.Constants;
import com.phablecare.demoapp.model.UserDetail;

@Database(entities = {UserDetail.class}, version = 1,exportSchema = false)
public abstract class UserDetailRoomDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile UserDetailRoomDatabase INSTANCE;

    public static UserDetailRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserDetailRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserDetailRoomDatabase.class, Constants.DB_NME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
