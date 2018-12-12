package com.example.studia.projekt.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.studia.projekt.db.model.Section;

import java.util.List;

@Dao
public interface SectionsDao {

    @Insert
    void insert(Section section);

    @Query("SELECT * FROM sections WHERE userId=:id")
    LiveData<List<Section>> findSectionsByUserId(int id);

    @Query("SELECT * FROM sections WHERE sectionId=:id")
    LiveData<Section> getSectionsById(int id);

}