package com.xinye.ui.widget;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;

import com.xinye.libs.widget.ProgressView;

/**
 *
 * 仿iReader进度条
 *
 * @author wangheng
 */
public class ProgressViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        float density =  getResources().getDisplayMetrics().density;

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,500);
        ProgressView view = new ProgressView(ProgressViewActivity.this);
        view.setText("正在下载...");
        view.setTextSize((int) (40 * density + 0.5f));
        view.setMaxProgress(10000);
        view.setProgress(5000);
        view.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        view.setLayoutParams(params);
        view.setProgressColor(Color.RED);
        setContentView(view);

    }
}
