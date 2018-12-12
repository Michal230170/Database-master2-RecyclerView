package com.example.studia.projekt.ui.base;

import android.app.Application;
import android.arch.lifecycle.ViewModel;

import com.example.studia.projekt.db.AppDatabase;

public class BaseViewModel extends ViewModel {

    private AppDatabase appDatabase;

    public BaseViewModel(Application application) {
        this.appDatabase = AppDatabase.getInstance(application);
    }

    protected AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
