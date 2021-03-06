package enbledu.todolist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import enbledu.todolist.R;
import enbledu.todolist.database.NoteDAOImpl;
import enbledu.todolist.entity.NoteEntity;
import enbledu.todolist.helper.RecyclerItemClickListener;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class MyRecyclerVIewAdapter extends RecyclerView.Adapter<MyRecyclerVIewAdapter.MyViewHolder>{

    private RecyclerItemClickListener.OnItemClickListener mListener;
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

    public void setListener(RecyclerItemClickListener.OnItemClickListener listener) {
        mListener = listener;
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
        final NoteEntity noteInRecycleViewItem = noteDatas.get(position);
        holder.mTextView.setText(noteInRecycleViewItem.getTitle());

        if(noteInRecycleViewItem.isFinished()) {
            holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.text_grey));
            holder.mCheckBox.setChecked(true);
        }
        else {
            holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.mCheckBox.setChecked(false);
        }
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    if(noteInRecycleViewItem.isFinished()==false) {
                        noteInRecycleViewItem.setFinished(true);
                        mDAO.updataNote(noteInRecycleViewItem);
                        holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.text_grey));
                    }
                }else{
                    if(noteInRecycleViewItem.isFinished()==true) {
                        noteInRecycleViewItem.setFinished(false);
                        mDAO.updataNote(noteInRecycleViewItem);
                        Toast.makeText(mContext, "Checked", Toast.LENGTH_SHORT).show();
                        holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.black));
                    }
                }
            }
        });

        final MyViewHolder vh = (MyViewHolder) holder;
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, position);
                }
            }
        });
        vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mListener != null) {
                    mListener.onItemLongClick(v, position);
                }
                return true;
            }
        });

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
