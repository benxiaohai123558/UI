package com.zmsoft.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zmsoft.R;


public class ModuleFragment extends BaseModuleFragment {

    public ModuleFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitleValue("设置");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_left;
    }

}
