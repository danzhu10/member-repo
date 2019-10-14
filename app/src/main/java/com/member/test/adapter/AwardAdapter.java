package com.member.test.adapter;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.member.test.R;
import com.member.test.item.AwardItem;

import java.util.List;

public class AwardAdapter extends BaseQuickAdapter<AwardItem, BaseViewHolder> {

    public AwardAdapter(int layoutResId, List<AwardItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AwardItem item) {

        helper.setText(R.id.voucher, item.getType())
                .setText(R.id.point, item.getPoint()+"")
                .setText(R.id.title, item.getTitle());
        ImageView imageView = helper.getView(R.id.image);
        Glide.with(mContext)
                .load("https://www.worldatlas.com/r/w728-h425-c728x425/upload/c3/55/d1/shutterstock-258841592.jpg")
                .fitCenter()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);

    }
}
