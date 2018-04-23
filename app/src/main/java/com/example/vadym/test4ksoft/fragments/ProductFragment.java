package com.example.vadym.test4ksoft.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vadym.test4ksoft.R;
import com.example.vadym.test4ksoft.databinding.FragmentProductBinding;
import com.example.vadym.test4ksoft.model.Coffee;
import com.example.vadym.test4ksoft.recycler.CoffeeRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    private FragmentProductBinding binding;
    private CoffeeRecyclerAdapter adapter;

    public ProductFragment() {
        // Required empty public constructor
    }

    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false);
        View view = binding.getRoot();

        binding.recycler.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));

        adapter = new CoffeeRecyclerAdapter(getActivity().getApplicationContext());
        setItemToListAdapter();
        binding.recycler.setHasFixedSize(false);
        binding.recycler.setAdapter(adapter);

        return view;
    }

    private void setItemToListAdapter() {
        List<Coffee> coffeeList = new ArrayList<>();
        Coffee coffee1 = new Coffee("Late", R.drawable.coffee1, "150", false);
        coffeeList.add(coffee1);
        Coffee coffee2 = new Coffee("Americano", R.drawable.coffee1, "250", false);
        coffeeList.add(coffee2);
        Coffee coffee3 = new Coffee("Black White", R.drawable.coffee1, "50", false);
        coffeeList.add(coffee3);
        Coffee coffee4 = new Coffee("With Milk", R.drawable.coffee1, "130", false);
        coffeeList.add(coffee4);
        Coffee coffee5 = new Coffee("No Sugar", R.drawable.coffee1, "120", false);
        coffeeList.add(coffee5);
        Coffee coffee6 = new Coffee("Free", R.drawable.coffee1, "10", false);
        coffeeList.add(coffee6);

        adapter.addAll(coffeeList);
    }

}
