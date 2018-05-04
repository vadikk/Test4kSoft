package com.example.vadym.test4ksoft.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vadym.test4ksoft.R;
import com.example.vadym.test4ksoft.databinding.FragmentListAdressBinding;


public class ListAddressFragment extends DialogFragment {

    private FragmentListAdressBinding binding;

    public ListAddressFragment() {
        // Required empty public constructor
    }

    public static ListAddressFragment newInstance() {
        ListAddressFragment fragment = new ListAddressFragment();
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
//        return inflater.inflate(R.layout.fragment_list_adress, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_adress, container, false);
        View view = binding.getRoot();

        binding.toolBar.setTitle(R.string.delivery);
        binding.toolBar.setNavigationOnClickListener(v -> {
            dismiss();
        });

        binding.floatingActionButton.setOnClickListener(v -> {
            goToNextFragment();
        });
        return view;
    }

    private void goToNextFragment() {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

//        AddAddressFragment fragment = AddAddressFragment.newInstance();
//        transaction.replace(R.id.fragment,fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();

        DialogFragment fragment = AddAddressFragment.newInstance();
        fragment.setStyle( DialogFragment.STYLE_NORMAL, R.style.My );
        fragment.show(fragmentManager, "dialog2");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
