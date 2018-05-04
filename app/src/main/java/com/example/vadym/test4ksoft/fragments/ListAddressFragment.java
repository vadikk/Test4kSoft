package com.example.vadym.test4ksoft.fragments;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vadym.test4ksoft.R;
import com.example.vadym.test4ksoft.databinding.FragmentListAdressBinding;
import com.example.vadym.test4ksoft.model.Address;
import com.example.vadym.test4ksoft.recycler.address.AddressRecyclerAdapter;
import com.example.vadym.test4ksoft.room.AddressListModel;
import com.example.vadym.test4ksoft.util.OnDeleteAddressListener;
import com.example.vadym.test4ksoft.util.OnEditAddressListener;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ListAddressFragment extends DialogFragment implements OnDeleteAddressListener, OnEditAddressListener {

    private FragmentListAdressBinding binding;
    private AddressListModel viewModel;
    private CompositeDisposable compositeDisposable;
    private AddressRecyclerAdapter adapter;
    private boolean isFirstLoad = false;

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

        viewModel = ViewModelProviders.of(this).get(AddressListModel.class);

        compositeDisposable = new CompositeDisposable();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recycler.setLayoutManager(manager);

        adapter = new AddressRecyclerAdapter();
        adapter.setOnDeleteListener(this);
        adapter.setOnEditListener(this);
        binding.recycler.setAdapter(adapter);

        binding.toolBar.setTitle(R.string.delivery);
        binding.toolBar.setNavigationOnClickListener(v -> {
            dismiss();
        });

        binding.floatingActionButton.setOnClickListener(v -> {
            goToNextFragment();
        });

        subscribeUI();


        return view;
    }

    private void subscribeUI() {
        Flowable getList = viewModel.getAddress()
                .flatMap(Flowable::just)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapCompletable(this::addAllToAdapter).toFlowable();

        Disposable disposable1 = getList.subscribe();

        compositeDisposable.add(disposable1);

    }
    private Completable addAllToAdapter(List<Address> address) {
        return Completable.fromAction(() -> {
            adapter.clear();
            adapter.addAll(address);

        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null)
            compositeDisposable.clear();
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

    @Override
    public void delete(int pos) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Вы точно хотите удалить!")
                .setCancelable(false)
                .setPositiveButton("ДА", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Address address = adapter.getAddress(pos);
                        adapter.clear();
                        viewModel.delete(address);
                    }
                })
                .setNegativeButton("НЕТ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public void edit(int pos) {
        boolean isCanEdit = true;
        Address address = adapter.getAddress(pos);
        Bundle bundle = new Bundle();
        bundle.putSerializable("address",address);
        bundle.putBoolean("edit",isCanEdit);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

//        AddAddressFragment fragment = AddAddressFragment.newInstance();
//        transaction.replace(R.id.fragment,fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();

        DialogFragment fragment = AddAddressFragment.newInstance();
        fragment.setArguments(bundle);
        fragment.setStyle( DialogFragment.STYLE_NORMAL, R.style.My );
        fragment.show(fragmentManager, "dialog2");
        transaction.addToBackStack(null);
//        transaction.commit();
    }
}
