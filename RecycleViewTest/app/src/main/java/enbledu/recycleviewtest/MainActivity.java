package enbledu.recycleviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<String> mDatas;
    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*LinearLayoutManager mliLinearLayoutManager = new LinearLayoutManager(MainActivity.this);*/
        initDatas();

        initView();

        myAdapter = new MyAdapter(MainActivity.this, mDatas);
        mRecyclerView.setAdapter(myAdapter);
        //设置recycleview的布局管理
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);


    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview);
    }

    private void initDatas() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

}
