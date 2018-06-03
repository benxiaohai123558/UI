package com.zmsoft.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zmsoft.R;

/**
 * Author :Guazi
 * Time   :18/6/3
 * Desc   :
 */

public class RightFragment extends BaseRightFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_right;
    }

    @Override
    public boolean isScrollView() {
        return true;
    }
}
