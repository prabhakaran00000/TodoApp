package com.phablecare.demoapp.di.home;

import com.phablecare.demoapp.ui.UserListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UserListFragmentBuilderModule {
    @ContributesAndroidInjector
    abstract UserListFragment contributeUserListFragment();
}
