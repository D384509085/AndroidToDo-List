package enbledu.todolist.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

import enbledu.todolist.R;

public class EditActivity extends AppCompatActivity {

    private String TAG = "EditActivity";
    private NumberPicker mNumberPicker;
    Toolbar editToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Log.d(TAG, String.valueOf(getIntent() == null));
        initToolbar();
        initPicker();
    }

    private void initPicker() {
        mNumberPicker = (NumberPicker) findViewById(R.id.show_num_picker);
       // mNumberPicker.setFormatter(this);
       // mNumberPicker.setOnValueChangedListener(this);
        //mNumberPicker.setOnScrollListener(this);
        mNumberPicker.setMaxValue(23);
        mNumberPicker.setMinValue(0);
        mNumberPicker.setValue(10);


    }

    private void initToolbar() {
        editToolbar = (Toolbar) findViewById(R.id.edit_toolbar);
        //toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
        editToolbar.setTitle(getString(R.string.app_name));//设置主标题
        //返回键
        editToolbar.setNavigationIcon(R.mipmap.back_grey);
        editToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
