package com.example.studia.projekt.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "fieldrecord",
        foreignKeys = @ForeignKey(entity = Section.class, parentColumns = "sectionId", childColumns = "sectionFieldId"))
//Section.class w entity parentColumns to id z usera chilf id powiazuje nam z ta tabela
public class FieldRecord {

    //foreign key muszą być intami
    @PrimaryKey(autoGenerate = true)
    private int fieldId; // ten nam zwraca fieldId;
    private String date;
    private String area;
    private String plant;
    private String substance;
    private String usageReason;
    private String dose;
    private int sectionFieldId;

    @Ignore
    public FieldRecord(String date, String area, String plant, String substance,
                       String usageReason, String dose, int sectionFieldId) {
        this.date = date;
        this.area = area;
        this.plant = plant;
        this.substance = substance;
        this.usageReason = usageReason;
        this.dose = dose;
        this.sectionFieldId = sectionFieldId;
    }

    public FieldRecord(int fieldId, String date, String area, String plant, String substance,
                       String usageReason, String dose, int sectionFieldId) {
        this.fieldId = fieldId;
        this.date = date;
        this.area = area;
        this.plant = plant;
        this.substance = substance;
        this.usageReason = usageReason;
        this.dose = dose;
        this.sectionFieldId = sectionFieldId;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setfieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public String getUsageReason() {
        return usageReason;
    }

    public void setUsageReason(String usageReason) {
        this.usageReason = usageReason;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getSectionFieldId() {
        return sectionFieldId;
    }

    public void setSectionFieldId(int sectionFieldId) {
        this.sectionFieldId = sectionFieldId;
    }
}
