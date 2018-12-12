package com.example.studia.projekt.ui.section;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.studia.projekt.db.model.Section;
import com.example.studia.projekt.ui.base.BaseViewModel;

import java.util.List;

public class SectionActivityViewModel extends BaseViewModel {

    public SectionActivityViewModel(Application application) {
        super(application);
    }

    public LiveData<List<Section>> getSectionByUserId(int userId) {
        return getAppDatabase().sectionDao().findSectionsByUserId(userId);
    }
}
