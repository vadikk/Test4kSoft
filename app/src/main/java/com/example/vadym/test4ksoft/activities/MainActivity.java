package com.example.vadym.test4ksoft.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vadym.test4ksoft.R;
import com.example.vadym.test4ksoft.databinding.ActivityMainBinding;
import com.example.vadym.test4ksoft.fragments.BasketFragment;
import com.example.vadym.test4ksoft.fragments.ProductFragment;
import com.example.vadym.test4ksoft.fragments.ProfileFragment;
import com.example.vadym.test4ksoft.model.Coffee;
import com.example.vadym.test4ksoft.recycler.CoffeeRecyclerAdapter;
import com.example.vadym.test4ksoft.util.ViewPagerAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int[] tabIcons = {R.drawable.search,R.drawable.profile,R.drawable.basket};
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setTitle(getString(R.string.nameActivity));

        setSupportActionBar(activityMainBinding.toolBar);
        setupViewPager(activityMainBinding.viewPager);

        activityMainBinding.tabs.setupWithViewPager(activityMainBinding.viewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {

        for(int i=0; i<activityMainBinding.tabs.getTabCount();i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(tabIcons[i]);
            activityMainBinding.tabs.getTabAt(i).setCustomView(imageView);
        }
    }

    private void setupViewPager(ViewPager pager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProductFragment());
        adapter.addFragment(new ProfileFragment());
        adapter.addFragment(new BasketFragment());
        activityMainBinding.viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter:
                Toast.makeText(this, "Filter click", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
