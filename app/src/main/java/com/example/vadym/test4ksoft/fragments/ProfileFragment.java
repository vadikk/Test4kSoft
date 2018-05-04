package com.example.vadym.test4ksoft.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vadym.test4ksoft.R;
import com.example.vadym.test4ksoft.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private FragmentProfileBinding binding;

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

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false);
        View view = binding.getRoot();

        binding.goToAddress.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

//        ListAddressFragment fragment = ListAddressFragment.newInstance();
//        transaction.replace(R.id.fragment,fragment);
//        Fragment prev = fragmentManager.findFragmentById(R.id.fragment);
//        if(prev!=null)
//            transaction.remove(prev);

        DialogFragment fragment = ListAddressFragment.newInstance();
        fragment.setStyle( DialogFragment.STYLE_NORMAL, R.style.My );
        fragment.show(fragmentManager,"dialog");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
