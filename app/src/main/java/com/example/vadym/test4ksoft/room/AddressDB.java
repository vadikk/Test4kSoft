package com.example.vadym.test4ksoft.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.vadym.test4ksoft.model.Address;

@Database(entities = Address.class, version = 1)
public abstract class AddressDB extends RoomDatabase {

    private static final String DB_NAME = "gitDB.db";
    private static AddressDB instance;

    public static AddressDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AddressDB.class, DB_NAME).build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    public abstract AddressDao addressDao();
}
