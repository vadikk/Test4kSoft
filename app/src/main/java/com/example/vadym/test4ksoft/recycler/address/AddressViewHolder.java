package com.example.vadym.test4ksoft.recycler.address;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.example.vadym.test4ksoft.R;
import com.example.vadym.test4ksoft.databinding.AddressLayoutBinding;
import com.example.vadym.test4ksoft.model.Address;

public class AddressViewHolder extends RecyclerView.ViewHolder {

    private AddressLayoutBinding binding;

    public AddressViewHolder(View itemView) {
        super(itemView);
        this.binding = DataBindingUtil.bind(itemView);
    }

    public void setBinding(Address address){
        binding.setVariable(BR.address,address);
    }

    public void setCountNumber(int countNumber){
        binding.count.setText(String.valueOf(countNumber));
    }

    public void setText(Address address){
        binding.textView2.setText(itemView.getResources().getString(R.string.city_value,address.getCity()));
        binding.textView4.setText(itemView.getResources().getString(R.string.street_value,
                address.getStreet(),address.getBuilding(),address.getApartment()));
    }

    public AddressLayoutBinding getBinding() {
        return binding;
    }
}
