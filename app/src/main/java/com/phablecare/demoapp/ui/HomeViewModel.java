package com.phablecare.demoapp.ui;

import android.app.Application;
import android.content.Context;
import android.util.Patterns;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.phablecare.demoapp.R;
import com.phablecare.demoapp.data.repository.UserListRepository;
import com.phablecare.demoapp.helper.Constants;
import com.phablecare.demoapp.model.UserDetail;

import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private Context mContext;
    private UserListRepository mUserListRepository;
    private UserDetail mUserDetail;

    public final ObservableField<String> errorUserName = new ObservableField<>();
    public final ObservableField<String> errorEmailId = new ObservableField<>();

    @Inject
    public HomeViewModel(Application application,UserListRepository userListRepository) {
        mContext = application;
        mUserListRepository = userListRepository;
    }

    public LiveData<Boolean> getAddUpdateRemoveStatus(){
        return mUserListRepository.getAddUpdateRemoveStatus();
    }

    public LiveData<List<UserDetail>> getAllUserDetials(){
        return mUserListRepository.getAllUserDetials();
    }

    public void validateUserDetail(String intentType) {
        errorUserName.set(null);
        errorEmailId.set(null);
        if (mUserDetail.getUserName() == null || mUserDetail.getUserName().isEmpty()) {
            errorUserName.set(mContext.getString(R.string.user_name_empty));
        }else if (mUserDetail.getEmailId() == null || mUserDetail.getEmailId().isEmpty()) {
            errorEmailId.set(mContext.getString(R.string.email_empty));
        } else if (!Patterns.EMAIL_ADDRESS.matcher(mUserDetail.getEmailId()).matches()) {
            errorEmailId.set(mContext.getString(R.string.email_empty));
        } else {
            errorUserName.set(null);
            errorEmailId.set(null);
            if(intentType.equals(Constants.INTENT_ADD)){
                mUserListRepository.insertUserDetail(mUserDetail);
            }else {
                mUserListRepository.updateUserDetail(mUserDetail);
            }
        }
    }

    public void setUserDetail(UserDetail userDetail) {
       mUserDetail =  userDetail;
    }

    public void deleteUserDetail(UserDetail userDetail) {
        mUserListRepository.deleteUserDetail(userDetail);
    }

    public void fetchUserDetails() {
        mUserListRepository.fetchUserDetails();
    }
}
