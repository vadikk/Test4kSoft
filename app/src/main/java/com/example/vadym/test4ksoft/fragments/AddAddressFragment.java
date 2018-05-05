package com.example.vadym.test4ksoft.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vadym.test4ksoft.R;
import com.example.vadym.test4ksoft.databinding.FragmentAddAddressBinding;
import com.example.vadym.test4ksoft.model.Address;
import com.example.vadym.test4ksoft.room.AddressListModel;
import com.example.vadym.test4ksoft.util.Constants;

public class AddAddressFragment extends DialogFragment {

    private FragmentAddAddressBinding binding;
    private AddressListModel viewModel;

    private Address addressEdit;
    private boolean isCanEdit = false;
    private boolean isSaveEdit = false;

    public AddAddressFragment() {
        // Required empty public constructor
    }


    public static AddAddressFragment newInstance(Address address, boolean isCanEdit) {
        AddAddressFragment fragment = new AddAddressFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.ADDRESS, address);
        args.putBoolean(Constants.EDIT, isCanEdit);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            addressEdit = (Address) getArguments().getSerializable(Constants.ADDRESS);
            isCanEdit = getArguments().getBoolean(Constants.EDIT);
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

            binding.region.addTextChangedListener(new MyTextWatcher(binding.region));
            binding.city.addTextChangedListener(new MyTextWatcher(binding.city));
            binding.index.addTextChangedListener(new MyTextWatcher(binding.index));
            binding.street.addTextChangedListener(new MyTextWatcher(binding.street));
            binding.building.addTextChangedListener(new MyTextWatcher(binding.building));
            binding.apartment.addTextChangedListener(new MyTextWatcher(binding.apartment));

        }

        binding.toolBar.setTitle(R.string.delivery);
        binding.toolBar.setNavigationOnClickListener(v -> {
            dismiss();
        });

        binding.save.setOnClickListener(v -> checkTextIsNotNull());

        return view;
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
        if (isSaveEdit) {
            isSaveEdit = false;
            viewModel.update(addressEdit);
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

    private class MyTextWatcher implements TextWatcher {

        private View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            isSaveEdit = true;
            switch (view.getId()) {
                case R.id.region:
                    addressEdit.setRegion(s.toString());
                    break;
                case R.id.city:
                    addressEdit.setCity(s.toString());
                    break;
                case R.id.index:
                    addressEdit.setIndex(s.toString());
                    break;
                case R.id.street:
                    addressEdit.setStreet(s.toString());
                    break;
                case R.id.building:
                    addressEdit.setBuilding(s.toString());
                    break;
                case R.id.apartment:
                    addressEdit.setApartment(s.toString());
                    break;
            }
        }
    }
}
