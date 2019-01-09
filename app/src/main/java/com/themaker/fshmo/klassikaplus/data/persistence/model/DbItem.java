package com.themaker.fshmo.klassikaplus.data.persistence.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "items")
public class DbItem {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @PrimaryKey
    @SerializedName("id")
    private Integer id;
}
