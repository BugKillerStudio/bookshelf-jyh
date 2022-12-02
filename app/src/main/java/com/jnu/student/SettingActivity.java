package com.jnu.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.HashMap;
import java.util.Map;


/**
 * about page
 * Created by smartjinyu on 2017/2/7.
 */

public class SettingActivity extends AppCompatActivity {
    private static final String TAG = "Setting";
    private Switch switch1;
    private TextView switch1_text;
    private boolean switch_stat=false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        Map<String, String> logEvents = new HashMap<>();

//        logEvents.clear();
//        logEvents.put("Name", "onCreate");

        Boolean switch_stat = this.getIntent().getBooleanExtra("switch_stat",false);
//        Toast.makeText(SettingActivity.this,""+switch_stat,Toast.LENGTH_SHORT).show();
        Toolbar mToolbar = (Toolbar) findViewById(R.id.second_toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Setting");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        switch1 = findViewById(R.id.setting_switch1);
        switch1_text = findViewById(R.id.setting_switch1_text);

        if(switch_stat){
            switch1.setChecked(true);
        }

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String result = String.format("Toast功能的状态是%s",
                        (switch1.isChecked()) ? "开" : "关");
                switch1_text.setText(result);


            }
        });
//        refreshResult(); // 刷新Switch按钮的开关说明
//        Intent intent=new Intent();
//        Bundle bundle=new Bundle();
//        switch_stat = switch1.isChecked();
//        Toast.makeText(SettingActivity.this,""+switch_stat,Toast.LENGTH_SHORT).show();
//        bundle.putBoolean("switch_stat",switch_stat);
//
//        intent.putExtras(bundle);
//        setResult(666,intent);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                switch_stat = switch1.isChecked();
                bundle.putBoolean("switch_stat",switch_stat);

                intent.putExtras(bundle);
                setResult(666,intent);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
