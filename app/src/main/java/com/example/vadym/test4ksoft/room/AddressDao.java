package com.example.vadym.test4ksoft.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.vadym.test4ksoft.model.Address;

import java.util.List;

import io.reactivex.Flowable;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface AddressDao {

    @Insert(onConflict = REPLACE)
    void insert(Address address);

    @Update
    void update(Address address);

    @Query("SELECT * FROM address")
    Flowable<List<Address>> getAll();

    @Query("DELETE FROM address")
    void deleteAll();

    @Delete
    void delete(Address address);
}
