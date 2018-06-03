package com.zmsoft.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zmsoft.R;

/**
 * Author :Guazi
 * Time   :18/6/3
 * Desc   :带背景样式左侧Fragment，内容带滚动条
 */

public abstract class BaseLeftFragment extends Fragment {

    private ScrollView scrollContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_left_base, container, false);
        ViewStub viewStub = rootView.findViewById(R.id.viewstub);
        if (isScrollView()) {
            viewStub.setLayoutResource(R.layout.view_scroll);
        } else {
            viewStub.setLayoutResource(R.layout.view_frame);
        }
        viewStub.inflate();
        View childView = inflater.inflate(getLayoutId(), container, false);
        if (isScrollView()) {
            this.scrollContainer = rootView.findViewById(R.id.scroll_container);
            this.scrollContainer.addView(childView);
        } else {
            FrameLayout frameLayout = rootView.findViewById(R.id.frame_container);
            frameLayout.addView(childView);
        }
        return rootView;
    }

    /**
     * 获取Layout资源
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 内容是否自带滚动
     *
     * @return true 滚动
     */
    protected boolean isScrollView() {
        return true;
    }

    /**
     * ScrollView滑动到顶部
     */
    protected void scrollToTop() {
        if (isScrollView() && scrollContainer != null) {
            scrollContainer.post(new Runnable() {
                @Override
                public void run() {
                    scrollContainer.scrollTo(0, 0);
                }
            });
        }
    }
}
