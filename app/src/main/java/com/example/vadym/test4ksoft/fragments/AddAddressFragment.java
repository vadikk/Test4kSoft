package com.example.vadym.test4ksoft.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vadym.test4ksoft.R;
import com.example.vadym.test4ksoft.databinding.FragmentAddAddressBinding;

public class AddAddressFragment extends DialogFragment {

    private FragmentAddAddressBinding binding;

    public AddAddressFragment() {
        // Required empty public constructor
    }


    public static AddAddressFragment newInstance() {
        AddAddressFragment fragment = new AddAddressFragment();
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
//        return inflater.inflate(R.layout.fragment_add_address, container, false);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_address,container,false);
        View view = binding.getRoot();

        binding.toolBar.setTitle(R.string.delivery);
        binding.toolBar.setNavigationOnClickListener(v -> {
            dismiss();
        });

        binding.save.setOnClickListener(v -> checkTextIsNotNull());

        return view;
    }

    private void checkTextIsNotNull(){

//        if(binding.region.getText().toString().trim())
    }

}
