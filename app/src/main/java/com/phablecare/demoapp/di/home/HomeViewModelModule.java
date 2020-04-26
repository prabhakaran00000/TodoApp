package com.phablecare.demoapp.di.home;

import androidx.lifecycle.ViewModel;

import com.phablecare.demoapp.di.ViewModelKey;
import com.phablecare.demoapp.ui.HomeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class HomeViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    public abstract ViewModel bindHomeViewModel(HomeViewModel viewModel);
}

