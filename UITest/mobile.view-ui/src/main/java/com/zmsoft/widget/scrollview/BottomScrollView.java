package com.zmsoft.widget.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 滑动底部监听ScrollView.
 *
 * @author Shengcai
 */
public class BottomScrollView extends ScrollView {

    private ScrollBottomListener scrollBottomListener;

    public BottomScrollView(Context context) {
        super(context);
    }

    public BottomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomScrollView(Context context, AttributeSet attrs,int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt){
        if(t + getHeight() >=  computeVerticalScrollRange()){
            scrollBottomListener.scrollBottom();
        }
    }

    public void setScrollBottomListener(ScrollBottomListener scrollBottomListener){
        this.scrollBottomListener = scrollBottomListener;
    }

    public interface ScrollBottomListener{
        void scrollBottom();
    }

}