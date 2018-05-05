package com.example.vadym.test4ksoft.recycler.address;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.vadym.test4ksoft.R;
import com.example.vadym.test4ksoft.databinding.AddressLayoutBinding;
import com.example.vadym.test4ksoft.model.Address;
import com.example.vadym.test4ksoft.util.Constants;
import com.example.vadym.test4ksoft.util.OnDeleteAddressListener;
import com.example.vadym.test4ksoft.util.OnEditAddressListener;

import java.util.ArrayList;
import java.util.List;

public class AddressRecyclerAdapter extends RecyclerView.Adapter<AddressViewHolder> {

    private List<Address> list = new ArrayList<>();
    private OnDeleteAddressListener listener;
    private OnEditAddressListener editListener;
    private Context context;
    private boolean isRadioBtnChoose = false;
    private int posRadioBtn = 0;

    public AddressRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setOnDeleteListener(OnDeleteAddressListener listener) {
        this.listener = listener;
    }

    public void setOnEditListener(OnEditAddressListener listener) {
        editListener = listener;
    }

    public void setRadioBtnChoose(boolean isRadioBtnChoose, int posRadioBtn) {
        this.isRadioBtnChoose = isRadioBtnChoose;
        this.posRadioBtn = posRadioBtn;
    }

    public void addAddressToList(Address address) {
        list.add(address);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addAll(List<Address> addressList) {
        list.addAll(addressList);
        notifyDataSetChanged();

    }

    public void clear() {
        notifyItemRangeRemoved(0, getItemCount());
        list.clear();
    }

    @Nullable
    public Address getAddress(int position) {
        if (position < 0 || position >= getItemCount()) {
            return null;
        }
        return list.get(position);
    }

    @Override
    public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AddressLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.address_layout, parent, false);
        return new AddressViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(AddressViewHolder holder, int position) {
        Address address = list.get(holder.getAdapterPosition());
        int number = holder.getAdapterPosition();

        if (address != null) {
            holder.setBinding(address);
            holder.setCountNumber(++number);
            holder.setText(address);
            holder.getBinding().delete.setOnClickListener(v -> deletePosition(holder.getAdapterPosition()));
            holder.getBinding().edit.setOnClickListener(v -> editPosition(holder.getAdapterPosition()));
            if (isRadioBtnChoose && holder.getAdapterPosition() == posRadioBtn)
                holder.getBinding().radioButton.setChecked(isRadioBtnChoose);
            else if(!isRadioBtnChoose)
                holder.getBinding().radioButton.setChecked(false);
            holder.getBinding().radioButton.setOnClickListener(v -> choose(holder.getAdapterPosition(), holder.getBinding().radioButton, address));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void deletePosition(int pos) {
        if (listener != null) {
            listener.delete(pos);
        }
    }

    private void editPosition(int pos) {
        if (editListener != null) {
            editListener.edit(pos);
        }
    }

    private void choose(int pos, RadioButton radioButton, Address address) {
//        Log.d("TAG", "CLICK " + " pos " + pos);
        isRadioBtnChoose = !isRadioBtnChoose;
        radioButton.setChecked(isRadioBtnChoose);

        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);
        Intent intent = new Intent(Constants.ACTION);
        intent.putExtra(Constants.ADDRESS, address);
        intent.putExtra(Constants.RADIO_BUTTON_IS_CHOOSE, isRadioBtnChoose);
        intent.putExtra(Constants.RADIO_BUTTON_POSITION, pos);
        manager.sendBroadcast(intent);
    }

}
