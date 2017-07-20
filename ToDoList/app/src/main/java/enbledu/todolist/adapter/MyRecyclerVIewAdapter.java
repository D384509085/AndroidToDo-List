package enbledu.todolist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import enbledu.todolist.R;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class MyRecyclerVIewAdapter extends RecyclerView.Adapter<MyRecyclerVIewAdapter.MyViewHolder>{

    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mDatas;

    public MyRecyclerVIewAdapter(Context context, List<String> mDatas) {
        this.mDatas = mDatas;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.note_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.setIsRecyclable(false);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mTextView.setText(mDatas.get(position));
        holder.mCheckBox.setTag(new Integer(position));
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        CheckBox mCheckBox;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text_view);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox);

        }
    }
}
