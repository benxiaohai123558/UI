package com.zmsoft.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zmsoft.R;

/**
 * Author :Guazi
 * Time   :18/6/3
 * Desc   :带背景样式的ModuleFragment，内容自动滚动条
 */

public abstract class BaseModuleFragment extends Fragment {

    private TextView titleTxt;

    private ScrollView leftContainer;

    private FrameLayout rightContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_module_base, container, false);
        this.titleTxt = rootView.findViewById(R.id.txt_title);
        this.leftContainer = rootView.findViewById(R.id.left_fragment_container);
        this.rightContainer = rootView.findViewById(R.id.right_fragment_container);
        View childView = inflater.inflate(getLayoutId(), container, false);
        this.leftContainer.addView(childView);
        return rootView;
    }

    /**
     * 获取Layout资源
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitleValue(int title) {
        if (titleTxt != null) {
            this.titleTxt.setText(title);
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitleValue(String title) {
        if (titleTxt != null) {
            this.titleTxt.setText(title);
        }
    }

    /**
     * ScrollView滑动到顶部
     */
    protected void scrollToTop() {
        if (leftContainer != null) {
            leftContainer.post(new Runnable() {
                @Override
                public void run() {
                    leftContainer.scrollTo(0, 0);
                }
            });
        }
    }
}
