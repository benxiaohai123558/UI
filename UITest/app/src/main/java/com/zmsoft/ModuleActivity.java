package com.zmsoft;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.zmsoft.fragment.ModuleFragment;

/**
 * Author :Guazi
 * Time   :18/6/3
 * Desc   :
 */

public class ModuleActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        ModuleFragment moduleFragment = new ModuleFragment();
        addFragement(moduleFragment);
    }

    private void addFragement(Fragment fragment) {
        try {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (fragment.isHidden()) {
                ft.show(fragment);
            } else {
                if (!fragment.isAdded()) {
                    ft.add(R.id.contaier, fragment);
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
