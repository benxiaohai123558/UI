package com.zmsoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zmsoft.widget.listener.IWidgetClickBack;
import com.zmsoft.widget.listener.IWidgetClickListener;
import com.zmsoft.ui.WidgetText;

public class MainActivity extends Activity implements IWidgetClickListener, IWidgetClickBack {

    private WidgetText widgetText, widgetText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.widgetText = findViewById(R.id.wt1);
        this.widgetText2 = findViewById(R.id.wt2);
        this.widgetText.setWidgetClickListener(this);
        this.widgetText2.setWidgetClickListener(this);
    }

    @Override
    public void onWidgetClick(View view) {
        if (view == widgetText) {
            this.show(((WidgetText) view).getTitleValue());
        } else if (view == widgetText2) {
            this.show(((WidgetText) view).getTitleValue());
        }
    }

    @Override
    public void onWidgetBack(int type, View view) {

    }

    private void show(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
