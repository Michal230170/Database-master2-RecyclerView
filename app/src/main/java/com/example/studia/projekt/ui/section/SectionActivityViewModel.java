package com.example.studia.projekt.ui.section;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.studia.projekt.db.AppDatabase;
import com.example.studia.projekt.db.Dao.SectionsDao;
import com.example.studia.projekt.db.model.Section;
import com.example.studia.projekt.ui.base.BaseViewModel;

import java.util.List;

public class SectionActivityViewModel extends BaseViewModel {

    AppDatabase database;

    public SectionActivityViewModel(Application application) {
        super(application);
    }

    public LiveData<List<Section>> getSectionByUserId(int userId) {
        return getAppDatabase().sectionDao().findSectionsByUserId(userId);
    }
    public LiveData<Section> getSectionById(int sectionId){
        return getAppDatabase().sectionDao().getSectionsById(sectionId);
    }
    public void delete (Section section){
        database.sectionDao().delete(section);

    }

}
