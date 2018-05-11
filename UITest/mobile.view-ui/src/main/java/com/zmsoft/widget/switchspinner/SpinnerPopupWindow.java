package com.zmsoft.widget.switchspinner;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.zmsoft.ui.R;

import java.util.List;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import static com.zmsoft.ui.R.dimen.px1;

/**
 * 功能：下拉弹出框的PopupWindow，主要用于显示ListView
 * <p>
 * Created by danke on 2017/7/10.
 */

public class SpinnerPopupWindow<T> extends PopupWindow {
    private final LayoutInflater inflater;
    private List<T> mList;
    private ListView mListView;
    private MyAdapter mAdapter;
    private ColorStateList mTextColor; // 文字的颜色
    private Drawable mBackground; // listView的背景颜色
    private Drawable mColorDivider; // 分割线颜色

    public SpinnerPopupWindow(Context context, List<T> list, OnItemClickListener clickListener) {
        super(context);
        inflater = LayoutInflater.from(context);
        this.mList = list;
        init(clickListener);
    }

    private void init(OnItemClickListener clickListener) {
        View view = inflater.inflate(R.layout.spinner_window_layout, null);
        setContentView(view);
        setWidth(LayoutParams.WRAP_CONTENT);
        setHeight(LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00);
        setBackgroundDrawable(dw);
        mListView = (ListView) view.findViewById(R.id.listview);
        mListView.setAdapter(mAdapter = new MyAdapter());
        mListView.setOnItemClickListener(clickListener);
        mListView.setFocusable(false);
    }

    public <T> void setList(List list) {
        this.mList = list;
        mAdapter.notifyDataSetChanged();
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (mList == null || mList.isEmpty()) {
                return 0;
            }
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            if (mList == null || mList.isEmpty()) {
                return null;
            }
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.spinner_item_layout, null);
                holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvName.setText(getItem(position).toString());
            // 设置字体颜色
            if (mTextColor != null) {
                holder.tvName.setTextColor(mTextColor);
            }
            return convertView;
        }
    }

    private class ViewHolder {
        private TextView tvName;
    }
    /**
     * 设置字体颜色
     * @param textColor
     */
    public void setTextColor(ColorStateList textColor) {
        this.mTextColor = textColor;
    }

    /**
     * 设置ListView的背景颜色
     * @param background
     */
    public void setBackground(Drawable background) {
        this.mBackground = background;
        if (mListView != null && background != null) {
            mListView.setBackground(background);
        }
    }

    /**
     * 设置分割线颜色
     * @param background
     */
    public void setColorDivider(Drawable background) {
        this.mColorDivider = background;
        if (mListView != null && mColorDivider!= null) {
            mListView.setDivider(mColorDivider);
            mListView.setDividerHeight((int) mListView.getResources().getDimension(R.dimen.px1));
        }
    }
}
