package com.zmsoft.widget.popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.zmsoft.ui.R;


/**
 * 订单页批量操作按钮弹框
 * Created by jihuo on 2017/12/29.
 */

public class InstanceBatchOperPopup extends PopupWindow {

    private LayoutInflater inflater;

    private Context context;

    private View.OnClickListener clickListener;

    public InstanceBatchOperPopup(Context context, View.OnClickListener clickListener) {
        super(context);
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
        init();
    }

    private void init(){
        View view = inflater.inflate(R.layout.instance_batch_oper_popup, null);
        view.setFocusable(true);
        setContentView(view);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(context.getResources().getDimensionPixelSize(R.dimen.px170));
        setBackgroundDrawable(context.getResources().getDrawable(R.drawable.popup_bg));
        setOutsideTouchable(true);
        Button btnEditNum = (Button) view.findViewById(R.id.edit_num);
        Button btnBack = (Button) view.findViewById(R.id.back);
        Button btnSplit = (Button) view.findViewById(R.id.split);
        btnEditNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                clickListener.onClick(v);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                clickListener.onClick(v);
            }
        });
        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                clickListener.onClick(v);
            }
        });
    }
}
