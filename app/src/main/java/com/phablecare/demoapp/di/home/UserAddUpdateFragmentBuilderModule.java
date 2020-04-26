package com.phablecare.demoapp.di.home;

import com.phablecare.demoapp.ui.UserAddUpdateFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UserAddUpdateFragmentBuilderModule {
    @ContributesAndroidInjector
    abstract UserAddUpdateFragment contributeUserAddUpdateFragment();
}
