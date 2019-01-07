package com.example.studia.projekt.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "sections",
        foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId"))
public class Section {

    @PrimaryKey(autoGenerate = true)
    private int sectionId;   // ten nam zwraca recordFieldsId
    private String number;
    private String area;
    private String name;
    private int userId;

    // 2 konstruktory

    @Ignore
    public Section(String number, String area, String name, int userId) {
        this.number = number;
        this.area = area;
        this.name = name;
        this.userId = userId;
    }


    public Section(int sectionId, String number, String area, String name, int userId) {
        this.sectionId = sectionId;
        this.number = number;
        this.area = area;
        this.name = name;
        this.userId = userId;

    }

    public Section(){

    }


    //gettery i settery
    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
