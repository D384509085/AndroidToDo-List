package enbledu.todolist.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import enbledu.todolist.R;
import enbledu.todolist.activity.EditActivity;
import enbledu.todolist.adapter.MyRecyclerVIewAdapter;
import enbledu.todolist.database.NoteDAOImpl;
import enbledu.todolist.entity.NoteEntity;
import enbledu.todolist.helper.RecyclerItemClickListener;
import enbledu.todolist.helper.SortHelper;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class FragmentTodoList extends Fragment {
    private static final String TAG = "FragmentTodoList";
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
        initView();

        return mView;
    }

    private void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerview);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initDatas() {
        //是否按优先级排序
       String sortMethod = SortHelper.load(mContext);
        noteDatas = new ArrayList<NoteEntity>();
        mDAO = new NoteDAOImpl(mContext);
        noteDatas =  mDAO.getNoteDatas(sortMethod);



    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onResume() {
        super.onResume();
        initDatas();
        myRecyclerVIewAdapter = new MyRecyclerVIewAdapter(mContext, noteDatas);
        mRecyclerView.setAdapter(myRecyclerVIewAdapter);
        //设置recycleview的布局管理
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        refleshVIew();
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void refleshVIew() {
        noteDatas.clear();
        initDatas();
        myRecyclerVIewAdapter = new MyRecyclerVIewAdapter(mContext, noteDatas);
        mRecyclerView.setAdapter(myRecyclerVIewAdapter);
        //设置recycleview的布局管理
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        myRecyclerVIewAdapter.notifyDataSetChanged();
        myRecyclerVIewAdapter.setListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, EditActivity.class);
                intent.putExtra("note", noteDatas.get(position));
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                builder.setTitle("删除吗？");
                builder.setMessage("你真的真的真的想删除吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NoteEntity noteEntity = noteDatas.get(position);
                        mDAO.deleteNote(noteEntity.getTitle());
                        refleshVIew();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        refleshVIew();
                        builder.show();
                    }
                });

            }
        });
    }
}
