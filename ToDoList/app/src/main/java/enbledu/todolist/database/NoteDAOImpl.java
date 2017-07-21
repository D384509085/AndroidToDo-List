package enbledu.todolist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

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
        db.execSQL("insert into note_info(title,context,createTime,stopYear,stopMonth,stopDate,stopHour,stopMinute,priorty,isFinished) values(?,?,?,?,?)",
                new Object[]{noteEntity.getTitle(),noteEntity.getContext(),noteEntity.getCreateTime(),noteEntity.getStopYear(),noteEntity.getStopMonth(),noteEntity.getS});
        db.close();
    }

    @Override
    public void deleteNote(String title) {

    }

    @Override
    public void updataNote(NoteEntity noteEntity) {

    }

    @Override
    public List<NoteEntity> getThreads(boolean isSort) {
        return null;
    }
}
