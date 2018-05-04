package com.example.vadym.test4ksoft.recycler.address;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.vadym.test4ksoft.R;
import com.example.vadym.test4ksoft.databinding.AddressLayoutBinding;
import com.example.vadym.test4ksoft.model.Address;
import com.example.vadym.test4ksoft.model.Coffee;
import com.example.vadym.test4ksoft.util.OnDeleteAddressListener;
import com.example.vadym.test4ksoft.util.OnEditAddressListener;

import java.util.ArrayList;
import java.util.List;

public class AddressRecyclerAdapter extends RecyclerView.Adapter<AddressViewHolder> {

    private List<Address> list = new ArrayList<>();
    private OnDeleteAddressListener listener;
    private OnEditAddressListener editListener;

    public AddressRecyclerAdapter() {
    }

    public void setOnDeleteListener(OnDeleteAddressListener listener){this.listener=listener;}

    public void setOnEditListener(OnEditAddressListener listener){editListener=listener;}

    public void addAddressToList(Address address) {
        list.add(address);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addAll(List<Address> addressList) {
        list.addAll(addressList);
        notifyDataSetChanged();

    }

    public void clear(){
        notifyItemRangeRemoved(0,getItemCount());
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
        AddressLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.address_layout,parent,false);
        return new AddressViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(AddressViewHolder holder, int position) {
        Address address = list.get(holder.getAdapterPosition());
        int number = holder.getAdapterPosition();
        if(address!=null){
            holder.setBinding(address);
            holder.setCountNumber(++number);
            holder.setText(address);
            holder.getBinding().delete.setOnClickListener(v -> deletePosition(holder.getAdapterPosition()));
            holder.getBinding().edit.setOnClickListener(v -> editPosition(holder.getAdapterPosition()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void deletePosition(int pos){
        if(listener!=null){
            listener.delete(pos);
        }
    }

    private void editPosition(int pos){
        if(editListener!=null){
            editListener.edit(pos);
        }
    }

}
