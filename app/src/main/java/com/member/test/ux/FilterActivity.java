package com.member.test.ux;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

import com.member.test.Arithmetic;
import com.member.test.R;
import com.member.test.base.BaseActivity;
import com.member.test.databinding.ActivityFilterBinding;

public class FilterActivity extends BaseActivity {

    ActivityFilterBinding binding;

    @Override
    public void initContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter);
    }

    int seektime;

    @Override
    public void initView(Bundle savedInstanceState) {
        binding.toolbarMain.setTitle("Filter");
        initSeekMoney();
        binding.seekbarMoney.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                moneyProgress = progress;
                money = Arithmetic.progressToMoney(moneyProgress, MIN_MONEY, MAX_MONEY);
                binding.maxPriceTV.setText(money + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.exit) {
            finish();
        } else {
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private static int money = 2000;
    private static int moneyProgress = 12;
    private static int MIN_MONEY = 10000;
    private static int MAX_MONEY = 1000000;

    public void initSeekMoney() {
        binding.seekbarMoney.setType(0);
        binding.seekbarMoney.setMONEY_MIN(MIN_MONEY);
        binding.seekbarMoney.setMONEY_MAX(MAX_MONEY);
        if (binding.seekbarMoney != null) {
            binding.seekbarMoney.setProgress(moneyProgress);
        }
        money = Arithmetic.progressToMoney(moneyProgress, MIN_MONEY, MAX_MONEY);
    }
}
