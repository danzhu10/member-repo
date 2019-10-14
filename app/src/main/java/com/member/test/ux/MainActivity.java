package com.member.test.ux;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.member.test.R;
import com.member.test.adapter.AwardAdapter;
import com.member.test.base.BaseActivity;
import com.member.test.databinding.ActivityMainBinding;
import com.member.test.item.AwardItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;
    AwardAdapter adapter;
    List<AwardItem> awardItemList = new ArrayList<>();

    @Override
    public void initContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        initListAward();
        initToolbar();
    }

    private void initToolbar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbarMain, R.string.app_name, R.string.app_name);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.burger);
        toggle.setToolbarNavigationClickListener(v -> binding.drawerLayout.openDrawer(GravityCompat.START));
        toggle.syncState();
    }

    private void initListAward() {
        adapter = new AwardAdapter(R.layout.item_award, getData());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    private List<AwardItem> getData() {
        List<AwardItem> awardItemList = new ArrayList<>();
        awardItemList.add(new AwardItem("Vouchers", 500000, "Gift Card IDR 1.000.000", ""));
        awardItemList.add(new AwardItem("Vouchers", 250000, "Gift Card IDR 500.000", ""));
        awardItemList.add(new AwardItem("Products", 100000, "Old Fashion Cake", ""));
        return awardItemList;
    }

    private void closeDrawer() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.filter) {
            startActivity(new Intent(this, FilterActivity.class));
        } else {
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
