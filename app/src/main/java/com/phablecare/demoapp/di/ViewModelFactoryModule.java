package com.phablecare.demoapp.di;

import androidx.lifecycle.ViewModelProvider;

import com.phablecare.demoapp.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;


@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);

}
