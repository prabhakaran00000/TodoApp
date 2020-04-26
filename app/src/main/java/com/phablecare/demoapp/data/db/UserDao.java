package com.phablecare.demoapp.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.phablecare.demoapp.model.UserDetail;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * from USER_DETAILS")
    LiveData<List<UserDetail>> getAllUserDetails();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUserDetail(UserDetail userDetail);

    @Query("DELETE FROM USER_DETAILS")
    void deleteAll();

    @Update
    void updateUserDetail(UserDetail userDetail);

    @Delete
    void deleteUserDetail(UserDetail userDetail);
}
