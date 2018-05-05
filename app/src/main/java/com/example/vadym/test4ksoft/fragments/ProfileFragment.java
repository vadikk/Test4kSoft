package com.example.vadym.test4ksoft.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vadym.test4ksoft.R;
import com.example.vadym.test4ksoft.databinding.FragmentProfileBinding;
import com.example.vadym.test4ksoft.model.Address;
import com.example.vadym.test4ksoft.util.Constants;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private FragmentProfileBinding binding;
    private BasketReceiver receiver;
    private boolean isRadioBtnChoose = false;
    private int posRadioBtn = 0;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        View view = binding.getRoot();

        receiver = new ProfileFragment.BasketReceiver();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver, new IntentFilter(Constants.ACTION));

        binding.goToAddress.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        DialogFragment fragment = ListAddressFragment.newInstance(isRadioBtnChoose, posRadioBtn);
        fragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.My);
        fragment.show(fragmentManager, "dialog");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public class BasketReceiver extends BroadcastReceiver {

        public BasketReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) return;

            Address address = null;
            if (action.equals(Constants.ACTION)) {
                address = (Address) intent.getSerializableExtra(Constants.ADDRESS);
                isRadioBtnChoose = intent.getBooleanExtra(Constants.RADIO_BUTTON_IS_CHOOSE, false);
                posRadioBtn = intent.getIntExtra(Constants.RADIO_BUTTON_POSITION, 0);
                if(isRadioBtnChoose)
                    binding.adressTextView.setText(getResources().getString(R.string.street_value, address.getStreet(), address.getBuilding(), address.getApartment()));
                else
                    binding.adressTextView.setText(null);

            }
        }
    }
}
