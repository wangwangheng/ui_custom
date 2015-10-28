package com.xinye.ui;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xinye.ui.widget.ProgressViewActivity;

/**
 *
 * Entry Point
 *
 * @author wangheng
 */
public class MainActivity extends ListActivity {

    private final String[] classArray = {
            ProgressViewActivity.class.getName()
    };
    private final String[] itemArray = {
            "仿iReader进度条效果"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.activity_list_item,
                android.R.id.text1,
                itemArray));


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(MainActivity.this, classArray[position]));
        startActivity(intent);
    }


}
