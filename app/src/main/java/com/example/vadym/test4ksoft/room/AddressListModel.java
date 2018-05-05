package com.example.vadym.test4ksoft.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.vadym.test4ksoft.model.Address;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class AddressListModel extends AndroidViewModel {

    private AddressDB db;

    public AddressListModel(@NonNull Application application) {
        super(application);

        db = AddressDB.getInstance(getApplication());
    }

    public Flowable<List<Address>> getAddress() {
        return db.addressDao().getAll();
    }

    public void insertItem(Address address) {
        Completable.fromAction(() -> {
            db.addressDao().insert(address);
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    public void deleteAll() {
        Completable.fromAction(() -> db.addressDao().deleteAll())
                .subscribeOn(Schedulers.io()).subscribe();
    }

    public void delete(Address address) {
        Completable.fromAction(() -> db.addressDao().delete(address))
                .subscribeOn(Schedulers.io()).subscribe();
    }

    public void update(Address address) {
        Completable.fromAction(() -> db.addressDao().update(address))
                .subscribeOn(Schedulers.io()).subscribe();
    }
}
