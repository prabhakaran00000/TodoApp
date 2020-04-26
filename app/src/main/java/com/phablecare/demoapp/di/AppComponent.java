package com.phablecare.demoapp.di;

import android.app.Application;

import com.phablecare.demoapp.TodoApplication;
import com.phablecare.demoapp.di.home.HomeViewModelModule;
import com.phablecare.demoapp.di.home.UserAddUpdateFragmentBuilderModule;
import com.phablecare.demoapp.di.home.UserListFragmentBuilderModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component (modules = {AndroidSupportInjectionModule.class,
                       UserListFragmentBuilderModule.class,
                       UserAddUpdateFragmentBuilderModule.class,
                       AppModule.class,
                       HomeViewModelModule.class,
                       ViewModelFactoryModule.class,
        })
public interface AppComponent extends AndroidInjector<TodoApplication> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
