package com.member.test.ux;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.member.test.R;
import com.member.test.base.BaseActivity;
import com.member.test.databinding.ActivityLoginSimpleLightBinding;

public class LoginActivity extends BaseActivity {

    ActivityLoginSimpleLightBinding binding;

    @Override
    public void initContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_simple_light);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        binding.signIn.setOnClickListener(view -> startActivity(new Intent(this, MainActivity.class)));
    }
}
