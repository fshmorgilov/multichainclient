package com.themaker.fshmo.klassikaplus.data.persistence.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "photos",
        foreignKeys =
        @ForeignKey(entity = DbItem.class,
                parentColumns = "id",
                childColumns = "itemId"))
// TODO: 2/8/2019 провязать таблицу
public class DbPhoto {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "itemId")
    private String itemId;
    @ColumnInfo(name = "extId")
    private String extId;
    @ColumnInfo(name = "link")
    private String link;

    public int getId() {
        return id;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
