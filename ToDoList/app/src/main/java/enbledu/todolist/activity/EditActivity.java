package enbledu.todolist.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
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
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import enbledu.todolist.R;
import enbledu.todolist.Receiver.AlarmReceiver;
import enbledu.todolist.database.NoteDAOImpl;
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
    private NoteEntity oldEditNoteEntity;
    private NoteDAOImpl mDAO;
    private boolean isEdit = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Log.d(TAG, String.valueOf(getIntent() == null));
        init();
        initSavedData();
        initToolbar();

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
                Log.i(TAG, String.valueOf(num));
                editNoteEntity.setPriorty(num);
                Log.i(TAG, editNoteEntity.toString());
                mDAO = new NoteDAOImpl(EditActivity.this);
                if (isEdit) {
                    //删除之前的闹钟
                    mDAO.updataNote(editNoteEntity);
                } else if (!isEdit) {
                    mDAO.insertNote(editNoteEntity);
                }
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initSavedData() {
        Intent intent = getIntent();
        editNoteEntity = (NoteEntity) intent.getSerializableExtra("note");
        if ((editNoteEntity == null)) {
            editNoteEntity = new NoteEntity();
            //mNumberPicker.setOnScrollListener(this);
            mNumberPicker.setValue(10);
            isEdit = false;
        } else {
            //存储之前的数据，方便删除闹钟
            oldEditNoteEntity = editNoteEntity;
            isEdit = true;
            mTimePicker.setHour(editNoteEntity.getStopHour());
            mTimePicker.setMinute(editNoteEntity.getStopHour());
            titleEdit.setText(editNoteEntity.getTitle());
            contextEdit.setText(editNoteEntity.getContext());
            mNumberPicker.setValue(editNoteEntity.getPriorty());

        }
    }

    private void init() {
        mNumberPicker = (NumberPicker) findViewById(R.id.show_num_picker);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mTimePicker = (TimePicker) findViewById(R.id.timePicker);
        titleEdit = (EditText) findViewById(R.id.edit_title);
        contextEdit = (EditText) findViewById(R.id.edit_context);
        okButton = (Button) findViewById(R.id.edit_ok_button);
        mNumberPicker.setMaxValue(23);
        mNumberPicker.setMinValue(0);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                editNoteEntity.setStopYear(year);
                editNoteEntity.setStopMonth(month);
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

    private void startRemind(NoteEntity noteEntity){
        Calendar mCalendar;
        //得到日历实例，主要是为了下面的获取时间
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());

        //获取当前毫秒值
        long systemTime = System.currentTimeMillis();

        //是设置日历的时间，主要是让日历的年月日和当前同步
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        // 这里时区需要设置一下，不然可能个别手机会有8个小时的时间差
        mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        //设置在几点提醒  设置的为13点
        mCalendar.set(Calendar.YEAR, noteEntity.getStopYear());
        mCalendar.set(Calendar.MONTH, noteEntity.getStopMonth());
        mCalendar.set(Calendar.DAY_OF_MONTH, noteEntity.getStopdate())
        mCalendar.set(Calendar.HOUR_OF_DAY, noteEntity.getStopHour());
        //设置在几分提醒  设置的为25分
        mCalendar.set(Calendar.MINUTE, noteEntity.getStopMinute());
        //下面这两个看字面意思也知道
        mCalendar.set(Calendar.SECOND, 0);
        mCalendar.set(Calendar.MILLISECOND, 0);

        //上面设置的就是结束时间点

        //获取毫秒值
        long selectTime = mCalendar.getTimeInMillis();

        // 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
        if(systemTime > selectTime) {
            mCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        //AlarmReceiver.class为广播接受者
        Intent intent = new Intent(EditActivity.this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(EditActivity.this, 0, intent, 0);
        //得到AlarmManager实例
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);

        //**********注意！！下面的两个根据实际需求任选其一即可*********

        /**
         * 单次提醒
         * mCalendar.getTimeInMillis() 为之前设置的值
         */
        am.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pi);

        /**
         * 重复提醒
         * 第一个参数是警报类型；下面有介绍
         * 第二个参数网上说法不一，很多都是说的是延迟多少毫秒执行这个闹钟，但是我用的刷了MIUI的三星手机的实际效果是与单次提醒的参数一样，即设置的13点25分的时间点毫秒值
         * 第三个参数是重复周期，也就是下次提醒的间隔 毫秒值 我这里是一天后提醒
         *//*
        am.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), (1000 * 60 * 60 * 24), pi);*/

    }
    private void stopRemind(){

        Intent intent = new Intent(EditActivity.this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(EditActivity.this, 0,
                intent, 0);
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        //取消警报
        am.cancel(pi);


    }
}
