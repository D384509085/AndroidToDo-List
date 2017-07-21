package enbledu.todolist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/7/15 0015.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper sHelper = null;
    private static final String DB_NAME = "note.db";
    private static final int VERSION = 1;
    private static final String SQL_CREATE = "create table note_info(_id integer primary key autoincrement," +
            "title text," +
            "context text," +
            "createTime text," +
            "stopYear integer," +
            "stopMonth integer," +
            "stopDate integer," +
            "stopHour integer," +
            "stopMinute integer," +
            "priorty integer,"+
            "isFinished integer)";
    private static final String SQL_DROP = "drop table if exists note_info";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }
    public static DBHelper getInstance(Context context) {
        if (sHelper == null) {
            sHelper = new DBHelper(context);
        }
        return sHelper;
    }
}
