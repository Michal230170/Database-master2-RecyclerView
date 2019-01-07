package com.example.studia.projekt.db.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.studia.projekt.db.model.FieldRecord;
import java.util.List;

@Dao
public interface FieldsDao {

  @Insert
  void insert(FieldRecord record);

  @Query("SELECT * FROM fieldrecord WHERE sectionFieldId=:id")
  LiveData<List<FieldRecord>> findFieldRecordBySectionId(int id);

  @Query("SELECT * FROM fieldrecord WHERE fieldId=:id")
  LiveData<FieldRecord> findFieldRecordByFieldId(int id);

}
