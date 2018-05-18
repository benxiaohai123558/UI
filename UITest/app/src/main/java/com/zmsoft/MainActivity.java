package com.zmsoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zmsoft.ui.WidgetSwich;
import com.zmsoft.widget.listener.IWidgetClickBack;
import com.zmsoft.widget.listener.IWidgetClickListener;
import com.zmsoft.ui.WidgetText;

public class MainActivity extends Activity implements IWidgetClickListener, IWidgetClickBack {

    private WidgetText widgetText, widgetText2;

    private WidgetSwich widgetSwich, widgetSwich2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.widgetText = findViewById(R.id.wt1);
        this.widgetText2 = findViewById(R.id.wt2);
        this.widgetText.setWidgetClickListener(this);
        this.widgetText2.setWidgetClickListener(this);

        this.widgetSwich = findViewById(R.id.ws1);
        this.widgetSwich2 = findViewById(R.id.ws2);
        this.widgetSwich.setWidgetClickListener(this);
        this.widgetSwich2.setWidgetClickListener(this);
    }

    @Override
    public void onWidgetClick(View view) {
        if (view == widgetText) {
            this.show(((WidgetText) view).getTitleValue());
        } else if (view == widgetText2) {
            this.show(((WidgetText) view).getTitleValue());
        }else if (view == widgetSwich) {
            this.show(((WidgetSwich) view).getTitleValue());
        }else if (view == widgetSwich2) {
            this.show(((WidgetSwich) view).getTitleValue());
        }
    }

    @Override
    public void onWidgetBack(int type, View view) {

    }

    private void show(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
