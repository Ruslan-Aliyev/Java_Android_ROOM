package com.example.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Entry.class, version = 1)
public abstract class DB extends RoomDatabase {

    public abstract EntryDao entryDao();

    private static volatile DB db;

    // Singleton
    static DB getDatabase(final Context context) {
        if (db == null) {
            synchronized (DB.class) {
                if (db == null) {
                    db = Room.databaseBuilder(context.getApplicationContext(),
                            DB.class, "entries_database")
                            .build();
                }
            }
        }
        return db;
    }
}
