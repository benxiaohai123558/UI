package com.zmsoft.widget.picker;

import android.content.Context;
import android.view.View;

import com.zmsoft.ui.R;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;


public class WheelMain {
    private View view;
    private WheelView wv_year;
    private WheelView wv_month;
    private WheelView wv_day;
    private WheelView wv_hours;
    private WheelView wv_mins;
    //private WheelView wv_second;
    public int screenheight;

    private Context context;

    private static int START_YEAR = 1900, END_YEAR = 2100;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public static int getSTART_YEAR() {
        return START_YEAR;
    }

    public static void setSTART_YEAR(int sTART_YEAR) {
        START_YEAR = sTART_YEAR;
    }

    public static int getEND_YEAR() {
        return END_YEAR;
    }

    public static void setEND_YEAR(int eND_YEAR) {
        END_YEAR = eND_YEAR;
    }

    public WheelMain(Context context, View view) {
        super();
        this.context = context;
        this.view = view;
        setView(view);
    }

    /**
     * @Description: TODO 弹出日期选
     */
    public void initDateTimePicker(int year, int month, int day) {
        //		int year = calendar.get(Calendar.YEAR);
        //		int month = calendar.get(Calendar.MONTH);
        //		int day = calendar.get(Calendar.DATE);

        // 添加大小月月份并将其转换为list,方便之后的判
        String[] months_big = {"1", "3", "5", "7", "8", "10", "12"};
        String[] months_little = {"4", "6", "9", "11"};

        final List<String> list_big = Arrays.asList(months_big);
        final List<String> list_little = Arrays.asList(months_little);

        // 年
        wv_year = (WheelView) view.findViewById(R.id.year);
        wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
        wv_year.setCyclic(true);// 可循环滚动
        wv_year.setLabel(context.getString(R.string.year));// 添加文字
        wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

        // 月
        wv_month = (WheelView) view.findViewById(R.id.month);
        wv_month.setAdapter(new NumericWheelAdapter(1, 12));
        wv_month.setCyclic(true);
        wv_month.setLabel(context.getString(R.string.month));
        wv_month.setCurrentItem(month);

        // 日
        wv_day = (WheelView) view.findViewById(R.id.day);
        wv_day.setCyclic(true);
        // 判断大小月及是否闰年,用来确定"日"的数据
        if (list_big.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new NumericWheelAdapter(1, 31));
        } else if (list_little.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new NumericWheelAdapter(1, 30));
        } else {
            // 闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                wv_day.setAdapter(new NumericWheelAdapter(1, 29));
            else
                wv_day.setAdapter(new NumericWheelAdapter(1, 28));
        }
        wv_day.setLabel(context.getString(R.string.day_day));
        wv_day.setCurrentItem(day - 1);

        // 添加监听
        OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int year_num = newValue + START_YEAR;
                // 判断大小月及是否闰年,用来确定的数
                if (list_big
                        .contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31));
                } else if (list_little.contains(String.valueOf(wv_month
                        .getCurrentItem() + 1))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30));
                } else {
                    if ((year_num % 4 == 0 && year_num % 100 != 0)
                            || year_num % 400 == 0)
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                    else
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                }
            }
        };
        // 添加监听
        OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int month_num = newValue + 1;
                // 判断大小月及是否闰年,用来确定的数
                if (list_big.contains(String.valueOf(month_num))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31));
                } else if (list_little.contains(String.valueOf(month_num))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30));
                } else {
                    if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
                            .getCurrentItem() + START_YEAR) % 100 != 0)
                            || (wv_year.getCurrentItem() + START_YEAR) % 400 == 0)
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                    else
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                }
            }
        };
        wv_year.addChangingListener(wheelListener_year);
        wv_month.addChangingListener(wheelListener_month);

        // 根据屏幕密度来指择器字体的不同屏幕可能不同)
        int textSize = 0;
        textSize = (screenheight / 100) * 4;
        wv_day.TEXT_SIZE = textSize;
        wv_month.TEXT_SIZE = textSize;
        wv_year.TEXT_SIZE = textSize;

    }

    /**
     * @Description: TODO 弹出日期选择器
     */
    public void initTimePicker(Context context, int hour, int minute/*, int second*/) {
        int textSize = 0;
        textSize = (screenheight / 100) * 4;

        // 时
        wv_hours = (WheelView) view.findViewById(R.id.hour);
        wv_hours.TEXT_SIZE = textSize;
        wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
        wv_hours.setCyclic(true);
        wv_hours.setLabel(context.getString(R.string.hour));
        wv_hours.setCurrentItem(hour);

        // 分
        wv_mins = (WheelView) view.findViewById(R.id.minites);
        wv_mins.TEXT_SIZE = textSize;
        wv_mins.setAdapter(new NumericWheelAdapter(0, 59, "%02d"));
        wv_mins.setCyclic(true);
        wv_mins.setLabel(context.getString(R.string.minute_minute));
        wv_mins.setCurrentItem(minute);

        // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)

        //        wv_second.TEXT_SIZE = textSize;

    }

    /**
     * 取得选择的日
     *
     * @return
     */
    public String getDate() {
        DecimalFormat decimal = new DecimalFormat("00");
        StringBuffer sb = new StringBuffer();
        sb.append(decimal.format((wv_year.getCurrentItem() + START_YEAR)))
                .append("-")
                .append(decimal.format((wv_month.getCurrentItem() + 1)))
                .append("-")
                .append(decimal.format((wv_day.getCurrentItem() + 1)));
        return sb.toString();
    }

    /**
     * 取得选择的日
     *
     * @return
     */
    public String getDate_month() {
        DecimalFormat decimal = new DecimalFormat("00");
        StringBuffer sb = new StringBuffer();
        sb.append(decimal.format((wv_year.getCurrentItem() + START_YEAR)))
                .append("-")
                .append(decimal.format((wv_month.getCurrentItem() + 1)));
        return sb.toString();
    }

    /**
     * 取得选择的时
     *
     * @return
     */
    public String getTime() {
        DecimalFormat decimal = new DecimalFormat("00");
        StringBuffer sb = new StringBuffer();
        sb.append(decimal.format(wv_hours.getCurrentItem())).append(":")
                .append(decimal.format(wv_mins.getCurrentItem()))/*.append(":")*/
        /*.append(decimal.format(wv_second.getCurrentItem()))*/;
        return sb.toString();
    }

    public void whellYear(int[] shadows_colors, int wheelBg, int wheelVal, int valueColor, int itemColor) {
        wv_year.setShadows_colors(shadows_colors);
        wv_year.setWheelBg(wheelBg);
        wv_year.setWheelVal(wheelVal);
        wv_year.setValue_txt_color(valueColor);
        wv_year.setItem_text_color(itemColor);
    }

    public void whellMonth(int[] shadows_colors, int wheelBg, int wheelVal, int valueColor, int itemColor) {
        wv_month.setShadows_colors(shadows_colors);
        wv_month.setWheelBg(wheelBg);
        wv_month.setWheelVal(wheelVal);
        wv_month.setValue_txt_color(valueColor);
        wv_month.setItem_text_color(itemColor);
    }

    public void whellDay(int[] shadows_colors, int wheelBg, int wheelVal, int valueColor, int itemColor) {
        wv_day.setShadows_colors(shadows_colors);
        wv_day.setWheelBg(wheelBg);
        wv_day.setWheelVal(wheelVal);
        wv_day.setValue_txt_color(valueColor);
        wv_day.setItem_text_color(itemColor);
    }

    public void whellHours(int[] shadows_colors, int wheelBg, int wheelVal, int valueColor, int itemColor) {
        wv_hours.setShadows_colors(shadows_colors);
        wv_hours.setWheelBg(wheelBg);
        wv_hours.setWheelVal(wheelVal);
        wv_hours.setValue_txt_color(valueColor);
        wv_hours.setItem_text_color(itemColor);
    }

    public void whellMins(int[] shadows_colors, int wheelBg, int wheelVal, int valueColor, int itemColor) {
        wv_mins.setShadows_colors(shadows_colors);
        wv_mins.setWheelBg(wheelBg);
        wv_mins.setWheelVal(wheelVal);
        wv_mins.setValue_txt_color(valueColor);
        wv_mins.setItem_text_color(itemColor);
    }

}
