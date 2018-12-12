package com.example.studia.projekt.ui.section;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.studia.projekt.db.model.FieldRecord;
import com.example.studia.projekt.ui.base.BaseViewModel;

import java.util.List;

public class MainActivityViewModel extends BaseViewModel {

    public MainActivityViewModel(Application application) {
        super(application);
    }

    public LiveData<List<FieldRecord>> findFieldRecordBySectionId(int sectionId) {
        return getAppDatabase().fieldDao().findFieldRecordBySectionId(sectionId);
    }
}
