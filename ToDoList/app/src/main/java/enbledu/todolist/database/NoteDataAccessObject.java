package enbledu.todolist.database;

import java.util.List;

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
     * 按照指定顺序读取数据
     */
    public List<NoteEntity> getThreads(String colName);
}
