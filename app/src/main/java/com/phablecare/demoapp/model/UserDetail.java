package com.phablecare.demoapp.model;



import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "user_details")
public class UserDetail extends BaseObservable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String userName;
    @NonNull
    private String emailId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @NonNull
    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        if(userName != null){
            this.userName = userName;
            notifyPropertyChanged(BR.userName);
        }
    }

    @NonNull
    @Bindable
    public String getEmailId() {
       return emailId;
    }

    public void setEmailId(@NonNull String emailId) {
        if(emailId != null){
            this.emailId = emailId;
            notifyPropertyChanged(BR.emailId);
        }
    }

}
