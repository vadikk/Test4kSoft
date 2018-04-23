package com.example.vadym.test4ksoft.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;

/**
 * Created by Vadym on 22.04.2018.
 */

public class Coffee extends BaseObservable {

    private String name;
    private int image;
    private String price;
    @Bindable
    public final ObservableBoolean isClicked = new ObservableBoolean(false);

    public Coffee(String name, int image, String price, boolean isClicked) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.isClicked.set(isClicked);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
