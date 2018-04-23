package com.example.vadym.test4ksoft.recycler;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vadym.test4ksoft.R;
import com.example.vadym.test4ksoft.activities.MainActivity;
import com.example.vadym.test4ksoft.databinding.CoffeeCardBinding;
import com.example.vadym.test4ksoft.model.Coffee;
import com.example.vadym.test4ksoft.util.CoffeeClickHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym on 22.04.2018.
 */

public class CoffeeRecyclerAdapter extends RecyclerView.Adapter {

    private static final int STATIC_CARD = 1;
    private static final int DYNAMIC_CARD = 0;
    public static int COUNT = 0;
    private List<Coffee> coffeeList = new ArrayList<>();
    private Context context;


    public CoffeeRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void addToCoffeeList(Coffee coffee) {
        notifyItemInserted(getItemCount() - 1);
        coffeeList.add(coffee);
    }

    public void addAll(List<Coffee> coffeeList1) {
        coffeeList.addAll(coffeeList1);
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == STATIC_CARD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_card, parent, false);
            return new CoffeeViewHolderStatic(view);
        } else {
            CoffeeCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.coffee_card, parent, false);
            return new CoffeeViewHolder(binding.getRoot());
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CoffeeViewHolder) {
            final Coffee coffee = getItemByPosition(position);
            if (coffee != null) {
                ((CoffeeViewHolder) holder).setBinding(coffee);
                ((CoffeeViewHolder) holder).binding.setClick(new CoffeeClickHandler() {
                    @Override
                    public void onClick() {
                        if (!coffee.isClicked.get()) {
                            coffee.isClicked.set(true);
                            COUNT++;
                        } else {
                            coffee.isClicked.set(false);
                            COUNT--;
                        }
                        setBasketCount(COUNT);
                    }
                });
            }
        }
    }

    private void setBasketCount(int item) {
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);
        Intent intent = new Intent(MainActivity.ACTION);
        intent.putExtra("Count", item);
        manager.sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return coffeeList.isEmpty() ? 0 : coffeeList.size() + 1;
    }

    private Coffee getItemByPosition(int position) {
        if (position == 0) return coffeeList.get(position);
        else return coffeeList.get(position - 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 1) {
            return STATIC_CARD;
        } else {
            return DYNAMIC_CARD;
        }
    }
}
