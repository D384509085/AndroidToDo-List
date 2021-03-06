package enbledu.todolist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;

import enbledu.todolist.entity.NoteEntity;

/**
 * Created by Administrator on 2017/7/21 0021.
 */

public class NoteDAOImpl implements NoteDataAccessObject {
    private static final String TAG = "NoteDAOImpl";
    private DBHelper mHelper;

    public NoteDAOImpl(Context context) {
        mHelper = DBHelper.getInstance(context);
    }

    @Override
    public void insertNote(NoteEntity noteEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int isFinished_int;
        if (noteEntity.isFinished()) {
            isFinished_int = 1;
        } else {
            isFinished_int = 0;
        }
        db.execSQL("insert into note_info(title,context,createTime,stopYear,stopMonth,stopDate,stopHour,stopMinute,priorty,isFinished) values(?,?,?,?,?,?,?,?,?,?)",
                new Object[]{noteEntity.getTitle(),
                        noteEntity.getContext(),
                        noteEntity.getCreateTime(),
                        noteEntity.getStopYear(),
                        noteEntity.getStopMonth(),
                        noteEntity.getStopdate(),
                        noteEntity.getStopHour(),
                        noteEntity.getStopMinute(),
                        noteEntity.getPriorty(),
                        isFinished_int
                });
        Log.i(TAG, String.valueOf(noteEntity.getPriorty()));
        db.close();
    }

    @Override
    public void deleteNote(String title) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("delete from note_info where title = ?",
                new String[]{title});

        db.close();
    }

    @Override
    public void updataNote(NoteEntity noteEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues edit_values = new ContentValues();
        edit_values.put("title", noteEntity.getTitle());
        edit_values.put("context", noteEntity.getContext());
        edit_values.put("createTime", noteEntity.getCreateTime());
        edit_values.put("stopYear", noteEntity.getStopYear());
        edit_values.put("stopMonth", noteEntity.getStopMonth());
        edit_values.put("stopDate", noteEntity.getStopdate());
        edit_values.put("stopHour", noteEntity.getStopHour());
        edit_values.put("stopMinute", noteEntity.getStopMinute());
        edit_values.put("priorty", noteEntity.getPriorty());
        if (noteEntity.isFinished()) {
            edit_values.put("isFinished", 1);
        } else {
            edit_values.put("isFinished", 0);
        }
        db.update("note_info", edit_values, "title = ?", new String[]{noteEntity.getTitle()});
        db.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public ArrayList<NoteEntity> getNoteDatas(String sortMethod) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        ArrayList<NoteEntity> list = new ArrayList<NoteEntity>();
        /*Cursor cursor = db.rawQuery("select * from thread_info where url = ?",
                 new String[]{url});*/
        Cursor cursor;
        if (Objects.equals(sortMethod, "优先级")) {
            cursor = db.query("note_info", null, null, null, null, null, "priorty");
        } else if (Objects.equals(sortMethod, "时间")){
            cursor = db.query("note_info", null, null, null, null, null, null);
        }
        else {
            cursor = db.query("note_info", null, null, null, null, null, "isFinished");
        }
        while (cursor.moveToNext()) {
            NoteEntity note = new NoteEntity();
            note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            note.setContext(cursor.getString(cursor.getColumnIndex("context")));
            note.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
            note.setStopYear(cursor.getInt(cursor.getColumnIndex("stopYear")));
            note.setStopMonth(cursor.getInt(cursor.getColumnIndex("stopMonth")));
            note.setStopdate(cursor.getInt(cursor.getColumnIndex("stopDate")));
            note.setStopHour(cursor.getInt(cursor.getColumnIndex("stopHour")));
            note.setStopMinute(cursor.getInt(cursor.getColumnIndex("stopMinute")));
            note.setPriorty(cursor.getInt(cursor.getColumnIndex("priorty")));
            note.setFinished(1 == cursor.getInt(cursor.getColumnIndex("isFinished")));
            list.add(note);
        }
        cursor.close();
        db.close();
        return list;
    }
}
