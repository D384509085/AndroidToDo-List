package enbledu.todolist.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import enbledu.todolist.R;
import enbledu.todolist.adapter.MyRecyclerVIewAdapter;
import enbledu.todolist.database.NoteDAOImpl;
import enbledu.todolist.entity.NoteEntity;
import enbledu.todolist.helper.SortHelper;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class FragmentTodoList extends Fragment {
    private Context mContext;
    private LayoutInflater inflater;
    private View mView;
    private RecyclerView mRecyclerView;
    private ArrayList<NoteEntity> noteDatas;
    private MyRecyclerVIewAdapter myRecyclerVIewAdapter;
    private NoteDAOImpl mDAO;

    public FragmentTodoList(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        mView = inflater.inflate(R.layout.fragment_todolist, container, false);
        initDatas();

        initView();

        myRecyclerVIewAdapter = new MyRecyclerVIewAdapter(mContext, noteDatas);
        mRecyclerView.setAdapter(myRecyclerVIewAdapter);
        //设置recycleview的布局管理
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        return mView;
    }

    private void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerview);
    }

    private void initDatas() {
        //是否排序
        boolean isSort = Boolean.parseBoolean(SortHelper.load(mContext));
        noteDatas = new ArrayList<NoteEntity>();
        mDAO = new NoteDAOImpl(mContext);
        noteDatas =  mDAO.getNoteDatas(isSort);


    }

}
