package com.example.vadym.test4ksoft.activities;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.vadym.test4ksoft.R;

/**
 * Created by Vadym on 23.04.2018.
 */

public class BasketView extends RelativeLayout {

    private TextView countView;
    public ImageView imageView;

    public BasketView(Context context) {
        this(context, null);
    }

    public BasketView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BasketView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BasketView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.basket_layout, this);
        countView = findViewById(R.id.count);
        imageView = findViewById(R.id.basket);
        setBasketCount(0);
    }

    public void setBasketCount(int count) {
        countView.setText(String.valueOf(count));
    }
}
