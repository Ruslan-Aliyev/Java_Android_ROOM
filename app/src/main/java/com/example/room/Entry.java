package com.example.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "entries")
public class Entry {

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    @ColumnInfo(name = "destination")
    private String destination;

    @NonNull
    @ColumnInfo(name = "country")
    private String country;

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getDestination() {
        return this.destination;
    }

    @NonNull
    public String getCountry() {
        return this.country;
    }

    public Entry(String id, String destination, String country) {
        this.id = id;
        this.destination = destination;
        this.country = country;
    }
}
