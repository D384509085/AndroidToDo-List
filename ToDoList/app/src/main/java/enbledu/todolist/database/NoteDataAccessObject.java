package enbledu.todolist.database;

import java.util.ArrayList;

import enbledu.todolist.entity.NoteEntity;

/**
 * Created by Administrator on 2017/7/20 0020.
 */

public interface NoteDataAccessObject {
    /**
     * 插入note信息
     */
    public void insertNote(NoteEntity noteEntity);

    /**
     * 删除note信息
     */
    public void deleteNote(String title);

    /**
     * 更改note信息
     */
    public void updataNote(NoteEntity noteEntity);

    /**
     * 是否按优先级排列
     */
    public ArrayList<NoteEntity> getNoteDatas(boolean isSort);

}
