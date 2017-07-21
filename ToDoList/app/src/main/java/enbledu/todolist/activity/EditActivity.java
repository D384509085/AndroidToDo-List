package enbledu.todolist.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Date;

import enbledu.todolist.R;
import enbledu.todolist.entity.NoteEntity;

public class EditActivity extends AppCompatActivity {

    private String TAG = "EditActivity";
    private NumberPicker mNumberPicker;
    private CalendarView mCalendarView;
    private TimePicker mTimePicker;
    private EditText titleEdit;
    private EditText contextEdit;
    private Button okButton;
    private Toolbar editToolbar;
    private NoteEntity editNoteEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Log.d(TAG, String.valueOf(getIntent() == null));
        initToolbar();
        init();
        initPicker();
        //若intent没有传消息
        editNoteEntity = new NoteEntity();
        Log.i(TAG,editNoteEntity.toString());

        okButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                String text, title;
                text = contextEdit.getText().toString();
                title = titleEdit.getText().toString();
                int hour = mTimePicker.getHour();
                int minute = mTimePicker.getMinute();
                editNoteEntity.setTitle(title);
                editNoteEntity.setContext(text);
                editNoteEntity.setStopHour(hour);
                editNoteEntity.setStopMinute(minute);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日-HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());
                String str = formatter.format(curDate);
                editNoteEntity.setCreateTime(str);
                editNoteEntity.setFinished(false);
                int num = mNumberPicker.getValue();
                editNoteEntity.setPriorty(num);
                Log.i(TAG,editNoteEntity.toString());
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
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                editNoteEntity.setStopYear(year);
                editNoteEntity.setStopMonth(dayOfMonth);
                editNoteEntity.setStopdate(dayOfMonth);
            }
        });

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
