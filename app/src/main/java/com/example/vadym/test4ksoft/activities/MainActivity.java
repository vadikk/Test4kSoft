package com.example.vadym.test4ksoft.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vadym.test4ksoft.R;
import com.example.vadym.test4ksoft.databinding.ActivityMainBinding;
import com.example.vadym.test4ksoft.fragments.BasketFragment;
import com.example.vadym.test4ksoft.fragments.ProductFragment;
import com.example.vadym.test4ksoft.fragments.ProfileFragment;
import com.example.vadym.test4ksoft.util.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    public static String ACTION = "BasketListener";
    private int[] tabIcons = {R.drawable.search, R.drawable.profile};
    private ActivityMainBinding activityMainBinding;
    private BasketView basketView;
    private BasketReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setTitle(getString(R.string.nameActivity));

        setSupportActionBar(activityMainBinding.toolBar);
        setupViewPager(activityMainBinding.viewPager);

        activityMainBinding.tabs.setupWithViewPager(activityMainBinding.viewPager);
        setupTabIcons();
        receiver = new BasketReceiver();

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, new IntentFilter(ACTION));
    }

    private void setupTabIcons() {
        if (basketView == null) {
            basketView = new BasketView(this);
        }

        for (int i = 0; i < activityMainBinding.tabs.getTabCount(); i++) {
            TabLayout.Tab currentTab = activityMainBinding.tabs.getTabAt(i);
            if (currentTab == null) continue;

            if (i == activityMainBinding.tabs.getTabCount() - 1) {
                currentTab.setCustomView(basketView);
            } else {
                currentTab.setIcon(tabIcons[i]);
            }
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

    public class BasketReceiver extends BroadcastReceiver {

        public BasketReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == ACTION) {
                int count = intent.getIntExtra("Count", 0);
                if (basketView != null)
                    basketView.setBasketCount(count);

            }
        }
    }
}
