package com.example.vadym.test4ksoft.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vadym.test4ksoft.R;
import com.example.vadym.test4ksoft.databinding.FragmentAddAddressBinding;
import com.example.vadym.test4ksoft.model.Address;
import com.example.vadym.test4ksoft.room.AddressListModel;

public class AddAddressFragment extends DialogFragment {

    private FragmentAddAddressBinding binding;
    private AddressListModel viewModel;

    private Address addressEdit;
    private boolean isCanEdit = false;

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
        if (getArguments() != null) {
            addressEdit = (Address) getArguments().getSerializable("address");
            isCanEdit = getArguments().getBoolean("edit");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add_address, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_address, container, false);
        View view = binding.getRoot();

        viewModel = ViewModelProviders.of(this).get(AddressListModel.class);

        if (isCanEdit) {
            isCanEdit = false;
            binding.setAddress(addressEdit);
        }

        binding.toolBar.setTitle(R.string.delivery);
        binding.toolBar.setNavigationOnClickListener(v -> {
            dismiss();
        });

        binding.save.setOnClickListener(v -> checkTextIsNotNull());

        return view;
    }

    private void editAddress() {

//            addressEdit.setRegion(binding.region.getText().toString());
//            addressEdit.setCity(binding.city.getText().toString());
//            addressEdit.setIndex(binding.index.getText().toString());
//            addressEdit.setStreet(binding.street.getText().toString());
//            addressEdit.setBuilding(binding.building.getText().toString());
//            addressEdit.setApartment(binding.apartment.getText().toString());

//        viewModel.update(addressEdit);
    }

    private void saveToBD() {
        Address address = new Address();
        address.setRegion(binding.region.getText().toString());
        address.setCity(binding.city.getText().toString());
        address.setIndex(binding.index.getText().toString());
        address.setStreet(binding.street.getText().toString());
        address.setBuilding(binding.building.getText().toString());
        address.setApartment(binding.apartment.getText().toString());
        viewModel.insertItem(address);
    }


    private void checkTextIsNotNull() {

        boolean hasError = checkTextEmpty(binding.region, binding.city, binding.index, binding.street, binding.building, binding.apartment);
        if (!hasError) {
            saveToBD();
            dismiss();
        }
    }

    private boolean checkTextEmpty(TextView... views) {
        boolean hasError = false;
        for (TextView textView : views) {
            if (textView.getText().toString().trim().length() <= 0) {
                textView.requestFocus();
                textView.setError(getString(R.string.error));
                if (!hasError) hasError = true;
            }
        }

        return hasError;
    }
}
