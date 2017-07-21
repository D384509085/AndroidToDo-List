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
import enbledu.todolist.database.NoteDAOImpl;
import enbledu.todolist.entity.NoteEntity;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class MyRecyclerVIewAdapter extends RecyclerView.Adapter<MyRecyclerVIewAdapter.MyViewHolder>{

    private NoteDAOImpl mDAO;
    private LayoutInflater mInflater;
    private Context mContext;
    private List<NoteEntity> noteDatas;

    public MyRecyclerVIewAdapter(Context context, List<NoteEntity> noteDatas) {
        mDAO = new NoteDAOImpl(context);
        this.noteDatas = noteDatas;
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
        NoteEntity noteInRecycleViewItem = noteDatas.get(position);
        holder.mTextView.setText(noteInRecycleViewItem.getTitle());


    }


    @Override
    public int getItemCount() {
        return noteDatas.size();
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
