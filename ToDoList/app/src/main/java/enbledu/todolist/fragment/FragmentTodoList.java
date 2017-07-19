package enbledu.todolist.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import enbledu.todolist.R;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class FragmentTodoList extends Fragment{
    private Context mContext;
    private LayoutInflater inflater;
    private View mView;
    public FragmentTodoList(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        mView= inflater.inflate(R.layout.fragment_todolist,container,false);
        return mView;
    }
}
