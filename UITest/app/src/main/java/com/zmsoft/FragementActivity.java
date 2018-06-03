package com.zmsoft;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.zmsoft.fragment.LeftFragment;
import com.zmsoft.fragment.RightFragment;

public class FragementActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragement);

        LeftFragment leftFragment = new LeftFragment();
        addLeftFragement(leftFragment);
        RightFragment rightFragment = new RightFragment();
        addRightFragement(rightFragment);
    }

    private void addLeftFragement(Fragment fragment) {
        try {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (fragment.isHidden()) {
                ft.show(fragment);
            } else {
                if (!fragment.isAdded()) {
                    ft.add(R.id.left_fragment_container, fragment);
                } else {
                    ft.show(fragment);
                }
            }
            ft.commitAllowingStateLoss();
        } catch (Throwable e) {
        }
    }

    private void addRightFragement(Fragment fragment) {
        try {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (fragment.isHidden()) {
                ft.show(fragment);
            } else {
                if (!fragment.isAdded()) {
                    ft.add(R.id.right_fragment_container, fragment);
                } else {
                    ft.show(fragment);
                }
            }
            ft.commitAllowingStateLoss();
        } catch (Throwable e) {
        }
    }

    private void hideFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragment);
        transaction.commitAllowingStateLoss();
    }
}
