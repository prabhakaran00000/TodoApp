package com.phablecare.demoapp.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.phablecare.demoapp.R;
import com.phablecare.demoapp.databinding.ActivityHomeBinding;


public class HomeActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityHomeBinding activityHomeBinding  = DataBindingUtil
                .setContentView(this, R.layout.activity_home);
        getSupportFragmentManager()
                .beginTransaction()
                .add(activityHomeBinding.frameContainer.getId(),new UserListFragment())
                .commit();

    }
}
