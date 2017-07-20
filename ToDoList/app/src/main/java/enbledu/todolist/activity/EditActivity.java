package enbledu.todolist.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import enbledu.todolist.R;

public class EditActivity extends AppCompatActivity {

    private String TAG = "EditActivity";
    private NumberPicker mNumberPicker;
    private CalendarView mCalendarView;
    private TimePicker mTimePicker;
    private EditText titleEdit;
    private EditText contextEdit;
    private Button okButton;
    private Toolbar editToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Log.d(TAG, String.valueOf(getIntent() == null));
        initToolbar();
        initPicker();
        init();
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text, title;
                text = contextEdit.getText().toString();
                title = titleEdit.getText().toString();
                 
            }
        });
    }

    private void init() {
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mTimePicker = (TimePicker) findViewById(R.id.timePicker);
        titleEdit = (EditText) findViewById(R.id.edit_title);
        contextEdit = (EditText) findViewById(R.id.edit_context);
        okButton = (Button) findViewById(R.id.edit_ok_button);
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
