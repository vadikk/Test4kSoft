package com.example.vadym.test4ksoft.recycler;

import android.content.ClipData;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vadym.test4ksoft.R;
import com.example.vadym.test4ksoft.databinding.CoffeeCardBinding;
import com.example.vadym.test4ksoft.model.Coffee;
import com.example.vadym.test4ksoft.util.CoffeeClickHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym on 22.04.2018.
 */

public class CoffeeRecyclerAdapter extends RecyclerView.Adapter {

    private List<Coffee> coffeeList = new ArrayList<>();
    private static final int STATIC_CARD = 1;
    private static final int DYNAMIC_CARD = 0;

    public CoffeeRecyclerAdapter() {
    }

    public void addToCoffeeList(Coffee coffee){
        notifyItemInserted(getItemCount()-1);
        coffeeList.add(coffee);
    }

    public void addAll(List<Coffee> coffeeList1){
        int startPos = getItemCount();
        notifyItemRangeInserted(startPos,coffeeList1.size());
        coffeeList.addAll(coffeeList1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==STATIC_CARD){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_card,parent,false);
            return new CoffeeViewHolderStatic(view);
        }else {
            CoffeeCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.coffee_card, parent, false);
            return new CoffeeViewHolder(binding.getRoot());
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CoffeeViewHolder ) {
            final Coffee coffee = coffeeList.get(position);
            if (coffee != null) {
                ((CoffeeViewHolder) holder).setBinding(coffee);
                ((CoffeeViewHolder) holder).binding.setClick(new CoffeeClickHandler() {
                    @Override
                    public void onClick() {
                        if (!coffee.isClicked.get()) {
                            coffee.isClicked.set(true);
                        } else {
                            coffee.isClicked.set(false);
                        }

                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return coffeeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==1)
            return STATIC_CARD;

            return DYNAMIC_CARD;
    }
}
