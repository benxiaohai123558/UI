package com.zmsoft.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 全部取餐的stamp
 * Created by danke on 2017/5/31
 */
public class FetchmentStampView {

    private Context context;

    private FrameLayout container, mainView;

    private TextView titleTxt, stampTxt;

    private ImageView bgImg;

    public FetchmentStampView(Context context, FrameLayout container) {
        this.context = context;
        this.container = container;
        init();
    }

    private void init() {
        this.initMainView();
    }

    private void initMainView() {
        this.mainView = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.fetchment_stamp_view, container);
        this.stampTxt = (TextView) mainView.findViewById(R.id.txt_stamp_time);
        this.titleTxt = (TextView) mainView.findViewById(R.id.txt_title);
        this.bgImg = (ImageView) mainView.findViewById(R.id.img_bg);
        this.mainView.setVisibility(View.INVISIBLE);
    }

    private void setVisibility(int visibility) {
        this.mainView.setVisibility(visibility);
    }

    public void show(String time) {
        this.stampTxt.setText(time);
        this.setVisibility(View.VISIBLE);
    }

    public void hide() {
        this.setVisibility(View.GONE);
    }

    public void setTitleTxt(int value) {
        this.titleTxt.setText(value);
    }

    public void showTitleTxt() {
        this.titleTxt.setVisibility(View.VISIBLE);
    }

    public void hideTitleTxt() {
        this.titleTxt.setVisibility(View.GONE);
    }

    public void setBgImg(int value) {
        this.bgImg.setBackgroundResource(value);
    }
}
