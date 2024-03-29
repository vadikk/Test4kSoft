package com.example.vadym.test4ksoft.recycler.product;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.vadym.test4ksoft.BR;
import com.example.vadym.test4ksoft.databinding.CoffeeCardBinding;
import com.example.vadym.test4ksoft.model.Coffee;

/**
 * Created by Vadym on 22.04.2018.
 */

public class CoffeeViewHolder extends RecyclerView.ViewHolder {

    public final CoffeeCardBinding binding;

    public CoffeeViewHolder(View view) {
        super(view);
        this.binding = DataBindingUtil.bind(view);
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, int v) {
        imageView.setImageResource(v);
    }

    public void setBinding(Coffee data) {
        binding.setVariable(BR.coffee, data);
//        binding.setCoffee(data);

    }
}
